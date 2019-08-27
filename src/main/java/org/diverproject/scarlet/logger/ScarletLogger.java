package org.diverproject.scarlet.logger;

import static org.diverproject.scarlet.logger.language.LoggerLanguage.ADD_DEFAULT_LOGGER;
import static org.diverproject.scarlet.logger.language.LoggerLanguage.LOGGER_NEW_INSTANCE;
import static org.diverproject.scarlet.logger.language.LoggerLanguage.LOGGER_NOT_FOUND;
import static org.diverproject.scarlet.logger.language.LoggerLanguage.SCARLET_LOGGER_CLOSE;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.diverproject.scarlet.ScarletRuntimeException;
import org.diverproject.scarlet.logger.abstraction.DefaultLogger;
import org.diverproject.scarlet.util.ArrayUtils;
import org.diverproject.scarlet.util.StringUtils;

public class ScarletLogger
{
	private static final ScarletLogger INSTANCE = new ScarletLogger();
	public static final String DEFAULT_LOGGER_NAME = "default";

	private Map<String, Logger> loggers;
	private boolean instanceNewLoggers;
	private Class<? extends Logger> defaultClassLogger;

	private ScarletLogger()
	{
		this.loggers = new TreeMap<>();
		this.setInstanceNewLoggers(false);
		this.setDefaultClassLogger(DefaultLogger.class);
	}

	public boolean isInstanceNewLoggers()
	{
		return this.instanceNewLoggers;
	}

	public void setInstanceNewLoggers(boolean instanceNewLoggers)
	{
		this.instanceNewLoggers = instanceNewLoggers;
	}

	public Class<? extends Logger> getDefaultClassLogger()
	{
		return this.defaultClassLogger;
	}

	public void setDefaultClassLogger(Class<? extends Logger> defaultClassLogger)
	{
		this.defaultClassLogger = defaultClassLogger;
	}

	@SuppressWarnings("resource")
	public void addDefaultLogger()
	{
		if (!this.add(new DefaultLogger(DEFAULT_LOGGER_NAME)))
			throw new ScarletRuntimeException(ADD_DEFAULT_LOGGER, DEFAULT_LOGGER_NAME);
	}

	public boolean add(Logger logger)
	{
		synchronized (this.loggers)
		{
			if (!this.hasAvaiableName(logger.getName()))
				return false;

			return this.loggers.put(logger.getName(), logger) != null;
		}
	}

	public Logger get(String name) throws LoggerRuntimeException
	{
		Logger logger = this.loggers.get(name);

		if (logger == null)
			if (this.isInstanceNewLoggers())
			{
				try {
					logger = this.getDefaultClassLogger().getDeclaredConstructor(String.class).newInstance(name);
				} catch (IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException e) {
					throw new LoggerRuntimeException(e, LOGGER_NEW_INSTANCE, name, this.getDefaultClassLogger().getName());
				}
			}

		if (logger == null)
			throw new LoggerRuntimeException(LOGGER_NOT_FOUND, name);

		return logger;
	}

	public boolean remove(Logger logger)
	{
		return this.loggers.remove(logger.getName()) != null;
	}

	public boolean remove(String name)
	{
		return this.loggers.remove(name) != null;
	}

	public boolean contains(Logger logger)
	{
		return this.loggers.containsValue(logger);
	}

	public boolean containsKey(String name)
	{
		return this.loggers.containsKey(name);
	}

	public boolean hasAvaiableName(String name)
	{
		return !this.loggers.containsKey(name);
	}

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

	public int size()
	{
		return this.loggers.size();
	}

	public static ScarletLogger getInstance()
	{
		return INSTANCE;
	}

	public Set<String> getLoggerNames()
	{
		return this.loggers.keySet();
	}

	@Override
	public String toString()
	{
		return	StringUtils.objectToString(
					this,
					"instanceNewLoggers", this.isInstanceNewLoggers(),
					"defaultClassLogger", this.getDefaultClassLogger(),
					"loggers", ArrayUtils.join(String.class, this.getLoggerNames().iterator())
				);
	}
}
