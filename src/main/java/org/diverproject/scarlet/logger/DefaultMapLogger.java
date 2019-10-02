package org.diverproject.scarlet.logger;

import static org.diverproject.scarlet.logger.language.LoggerLanguage.DEFAULT_CLASS_LOGGER;
import static org.diverproject.scarlet.logger.language.LoggerLanguage.LOGGER_CONSTRUCTOR;
import static org.diverproject.scarlet.logger.language.LoggerLanguage.LOGGER_NEW_INSTANCE;
import static org.diverproject.scarlet.logger.language.LoggerLanguage.LOGGER_NOT_FOUND;
import static org.diverproject.scarlet.logger.language.LoggerLanguage.SCARLET_LOGGER_CLOSE;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.diverproject.scarlet.util.ArrayUtils;
import org.diverproject.scarlet.util.StringUtils;

public class DefaultMapLogger<L extends Logger> implements MapLogger<L>
{
	public static final String DEFAULT_LOGGER_NAME = "default";

	private Map<String, L> loggers;
	private boolean instanceNewLoggers;
	private Class<? extends L> defaultClassLogger;

	public DefaultMapLogger()
	{
		this.loggers = new TreeMap<>();
		this.setInstanceNewLoggers(false);
	}

	@Override
	public boolean isInstanceNewLoggers()
	{
		return this.instanceNewLoggers;
	}

	public void setInstanceNewLoggers(boolean instanceNewLoggers)
	{
		this.instanceNewLoggers = instanceNewLoggers;
	}

	@Override
	public Class<? extends L> getDefaultClassLogger()
	{
		return this.defaultClassLogger;
	}

	public void setDefaultClassLogger(Class<? extends L> defaultClassLogger)
	{
		this.defaultClassLogger = defaultClassLogger;
	}

	@Override
	public boolean add(L logger)
	{
		synchronized (this.loggers)
		{
			if (!this.hasAvaiableName(logger.getName()))
				return false;

			return this.loggers.put(logger.getName(), logger) != null;
		}
	}

	@Override
	public L get(String name) throws LoggerRuntimeException
	{
		L logger = this.loggers.get(name);

		if (logger == null)
			if (this.isInstanceNewLoggers())
				try {

					Class<? extends L> classz = this.getDefaultClassLogger();

					if (classz == null)
						throw new LoggerRuntimeException(DEFAULT_CLASS_LOGGER);

					Constructor<? extends L> constructor = classz.getDeclaredConstructor(String.class);

					if (constructor == null)
						throw new LoggerRuntimeException(LOGGER_CONSTRUCTOR, this.getDefaultClassLogger().getName());

					logger = constructor.newInstance(name);

				} catch (IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException e) {
					throw new LoggerRuntimeException(e, LOGGER_NEW_INSTANCE, name, this.getDefaultClassLogger().getName());
				}

		if (logger == null)
			throw new LoggerRuntimeException(LOGGER_NOT_FOUND, name);

		return logger;
	}

	@Override
	public boolean remove(Logger logger)
	{
		return this.loggers.remove(logger.getName()) != null;
	}

	@Override
	public boolean remove(String name)
	{
		return this.loggers.remove(name) != null;
	}

	@Override
	public boolean contains(Logger logger)
	{
		return this.loggers.containsValue(logger);
	}

	@Override
	public boolean contains(String name)
	{
		return this.loggers.containsKey(name);
	}

	@Override
	public boolean hasAvaiableName(String name)
	{
		return !this.loggers.containsKey(name);
	}

	@Override
	public Set<String> names()
	{
		return this.loggers.keySet();
	}

	@Override
	public void clear()
	{
		this.loggers.values().forEach(logger -> {
			try {
				logger.close();
			} catch (IOException e) {
				throw new LoggerRuntimeException(e, SCARLET_LOGGER_CLOSE, logger.getName());
			}
		});
		this.loggers.clear();
	}

	@Override
	public int size()
	{
		return this.loggers.size();
	}

	@Override
	public String toString()
	{
		return	StringUtils.objectToString(
			this,
			"instanceNewLoggers", this.isInstanceNewLoggers(),
			"defaultClassLogger", this.getDefaultClassLogger(),
			"loggers", ArrayUtils.join(String.class, this.names().iterator())
		);
	}
}
