package org.diverproject.scarlet.logger;

import static org.diverproject.scarlet.logger.language.LoggerLanguage.NEW_LOGGER_LANGUAGE;

import java.lang.reflect.InvocationTargetException;

import org.diverproject.scarlet.util.ArrayUtils;
import org.diverproject.scarlet.util.StringUtils;

public class ScarletLoggers implements Loggers<LoggerLanguage>
{
	private static final String DEFAULT_LOGGER_NAME = "default";
	private static final ScarletLoggers INSTANCE = new ScarletLoggers();

	private MapLogger<LoggerLanguage> mapLogger;

	private ScarletLoggers()
	{
		this.mapLogger = new DefaultMapLogger<>();
	}

	public LoggerLanguage add(Class<? extends LoggerLanguage> loggerClass)
	{
		try {
			LoggerLanguage loggerLanguage = loggerClass.getConstructor().newInstance();
			return loggerLanguage;
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
