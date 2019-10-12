package org.diverproject.scarlet.logger;

import org.diverproject.scarlet.util.ArrayUtils;
import org.diverproject.scarlet.util.StringUtils;

import java.lang.reflect.InvocationTargetException;

import static org.diverproject.scarlet.logger.language.LoggerLanguage.NEW_LOGGER_LANGUAGE;

public class ScarletLoggers implements Loggers<LoggerLanguage>
{
	private static final String DEFAULT_LOGGER_NAME = "default";
	private static final ScarletLoggers INSTANCE = new ScarletLoggers();

	private MapLogger<LoggerLanguage> mapLogger;

	private ScarletLoggers()
	{
		this.mapLogger = new DefaultMapLogger<>();
	}

	@Override
	public boolean contains(String name)
	{
		return this.mapLogger.contains(name);
	}

	public LoggerLanguage add(String name, Class<? extends LoggerLanguage> loggerClass)
	{
		try {
			return loggerClass.getConstructor(String.class).newInstance(name);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			throw new LoggerRuntimeException(e, NEW_LOGGER_LANGUAGE, loggerClass.getName());
		}
	}

	@Override
	public LoggerLanguage get(Class<?> classz)
	{
		return this.mapLogger.get(classz.getName());
	}

	@Override
	public LoggerLanguage get(String name)
	{
		return this.mapLogger.get(name);
	}

	@Override
	public LoggerLanguage getDefault()
	{
		return this.mapLogger.get(DEFAULT_LOGGER_NAME);
	}

	@Override
	public String toString()
	{
		return	StringUtils.objectToString(
			this,
			"instanceNewLoggers", this.mapLogger.isInstanceNewLoggers(),
			"defaultClassLogger", this.mapLogger.getDefaultClassLogger(),
			"loggers", ArrayUtils.join(String.class, this.mapLogger.names().iterator())
		);
	}

	public static ScarletLoggers getInstance()
	{
		return INSTANCE;
	}
}
