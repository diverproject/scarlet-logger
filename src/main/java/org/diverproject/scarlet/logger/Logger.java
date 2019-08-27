package org.diverproject.scarlet.logger;

import java.io.Closeable;

public interface Logger extends Closeable
{
	String getName();

	public void feedLine();

	public void log(String message);
	public void log(String format, Object... args);
	public void log(LoggerLevel level, String message);
	public void log(LoggerLevel level, String format, Object... args);

	public void debug(String message);
	public void debug(String format, Object... args);
	public void system(String message);
	public void system(String format, Object... args);
	public void info(String message);
	public void info(String format, Object... args);
	public void notice(String message);
	public void notice(String format, Object... args);
	public void packet(String message);
	public void packet(String format, Object... args);
	public void warn(String message);
	public void warn(String format, Object... args);
	public void error(String message);
	public void error(String format, Object... args);
	public void fatal(String message);
	public void fatal(String format, Object... args);
	public void exception(String message);
	public void exception(String format, Object... args);
	public void exception(Exception e);
	public void exception(Exception e, String message);
	public void exception(Exception e, String format, Object... args);
	public void trace(Exception e);
}
