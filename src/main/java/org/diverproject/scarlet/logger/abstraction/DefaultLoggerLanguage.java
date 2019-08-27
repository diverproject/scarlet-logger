package org.diverproject.scarlet.logger.abstraction;

import static org.diverproject.scarlet.logger.abstraction.DefaultLoggerLevel.NONE;

import org.diverproject.scarlet.language.Language;
import org.diverproject.scarlet.logger.LoggerLevel;
import org.diverproject.scarlet.logger.LoggerSubject;

public class DefaultLoggerLanguage extends AbstractLogger
{
	public DefaultLoggerLanguage(String name)
	{
		this(name, (LoggerSubject) null);
	}

	public DefaultLoggerLanguage(String name, String pathname)
	{
		this(name, new DefaultFileLoggerSubject(pathname));
	}

	public DefaultLoggerLanguage(String name, LoggerSubject loggerOutputSubject)
	{
		super(name, loggerOutputSubject);
	}

	public void log(LoggerLevel level, Language language, Object... args)
	{
		this.upperStackTrace(1);
		this.log(level, language.getFormat(), args);
	}

	public void log(Language language)
	{
		this.upperStackTrace(1);
		this.log(NONE, language, new Object[0]);
	}

	public void log(Language language, Object... args)
	{
		this.upperStackTrace(1);
		this.log(NONE, language, args);
	}

	public void debug(Language language)
	{
		this.upperStackTrace(1);
		this.debug(language.getFormat());
	}

	public void debug(Language language, Object... args)
	{
		this.upperStackTrace(1);
		this.debug(language.getFormat(), args);
	}

	public void system(Language language)
	{
		this.upperStackTrace(1);
		this.system(language.getFormat());
	}

	public void system(Language language, Object... args)
	{
		this.upperStackTrace(1);
		this.system(language.getFormat(), args);
	}

	public void info(Language language)
	{
		this.upperStackTrace(1);
		this.info(language.getFormat());
	}

	public void info(Language language, Object... args)
	{
		this.upperStackTrace(1);
		this.info(language.getFormat(), args);
	}

	public void notice(Language language)
	{
		this.upperStackTrace(1);
		this.notice(language.getFormat());
	}

	public void notice(Language language, Object... args)
	{
		this.upperStackTrace(1);
		this.notice(language.getFormat(), args);
	}

	public void packet(Language language)
	{
		this.upperStackTrace(1);
		this.packet(language.getFormat());
	}

	public void packet(Language language, Object... args)
	{
		this.upperStackTrace(1);
		this.packet(language.getFormat(), args);
	}

	public void warn(Language language)
	{
		this.upperStackTrace(1);
		this.warn(language.getFormat());
	}

	public void warn(Language language, Object... args)
	{
		this.upperStackTrace(1);
		this.warn(language.getFormat(), args);
	}

	public void error(Language language)
	{
		this.upperStackTrace(1);
		this.error(language.getFormat());
	}

	public void error(Language language, Object... args)
	{
		this.upperStackTrace(1);
		this.error(language.getFormat(), args);
	}

	public void fatal(Language language)
	{
		this.upperStackTrace(1);
		this.fatal(language.getFormat());
	}

	public void fatal(Language language, Object... args)
	{
		this.upperStackTrace(1);
		this.fatal(language.getFormat(), args);
	}

	public void exception(Language language)
	{
		this.upperStackTrace(1);
		this.exception(language.getFormat());
	}

	public void exception(Language language, Object... args)
	{
		this.upperStackTrace(1);
		this.exception(language.getFormat(), args);
	}

	public void exception(Exception e, Language language)
	{
		this.upperStackTrace(1);
		this.exception(e, language.getFormat());
	}

	public void exception(Exception e, Language language, Object... args)
	{
		this.upperStackTrace(1);
		this.exception(e, language.getFormat(), args);
	}
}
