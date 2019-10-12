package org.diverproject.scarlet.logger.language;

import org.diverproject.scarlet.language.Language;
import org.diverproject.scarlet.language.LanguageAutoloader;

@LanguageAutoloader
public enum LoggerLanguage implements Language
{
	SCARLET_LOGGER_CLOSE	("failure on clear ScarletLogger (logger: %s)"),

	LOGGER_NOT_FOUND		("logger not found (name: %s)"),
	DEFAULT_CLASS_LOGGER	("none default class logger setted to new instances"),
	LOGGER_CONSTRUCTOR		("logger implementation need a constructor that receive String as logger name (loggerClass: %s)"),
	LOGGER_NEW_INSTANCE		("failure on create a new instance (name: %s, className: %s)"),
	ADD_DEFAULT_LOGGER		("default logger already registered (name: %s)"),
	OFFSET_TRACE			("invalid offset trace (offsetTrace: %d)"),

	OPEN_FILE_CHANNEL		("failure on open a file channel (pathname: %s)"),
	WRITE_FILE_CHANNEL		("failure on write to file channel (pathname: %s)"),
	FEED_LINE_FILE_CHANNEL	("failure on feed a line to file channel (pathname: %s)"),

	NEW_LOGGER_LANGUAGE		("failure on instance a new logger language from '%s'"),

	;

	private String format;

	private LoggerLanguage(String format)
	{
		this.setFormat(format);
	}

	@Override
	public int getCode()
	{
		return this.ordinal();
	}

	@Override
	public String getFormat()
	{
		return this.format;
	}

	@Override
	public void setFormat(String format)
	{
		this.format = format;
	}
}
