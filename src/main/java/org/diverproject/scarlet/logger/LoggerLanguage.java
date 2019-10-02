package org.diverproject.scarlet.logger;

import org.diverproject.scarlet.language.Language;

public interface LoggerLanguage extends Logger
{
	public void log(LoggerLevel level, Language language, Object... args);
	public void log(Language language);
	public void log(Language language, Object... args);
	public void debug(Language language);
	public void debug(Language language, Object... args);
	public void system(Language language);
	public void system(Language language, Object... args);
	public void info(Language language);
	public void info(Language language, Object... args);
	public void notice(Language language);
	public void notice(Language language, Object... args);
	public void packet(Language language);
	public void packet(Language language, Object... args);
	public void warn(Language language);
	public void warn(Language language, Object... args);
	public void error(Language language);
	public void error(Language language, Object... args);
	public void fatal(Language language, Object... args);
	public void exception(Language language);
	public void exception(Language language, Object... args);
	public void exception(Exception e, Language language);
	public void exception(Exception e, Language language, Object... args);
}
