package org.diverproject.scarlet.logger.abstraction;

import org.diverproject.scarlet.logger.LoggerLevel;

public enum DefaultLoggerLevel implements LoggerLevel
{
	NONE		(""),
	DEBUG		("DEBUG "),
	SYSTEM		("SYSTEM"),
	INFO		("INFO  "),
	NOTICE		("NOTICE"),
	PACKET		("PACKET"),
	WARN		("WARN  "),
	ERROR		("ERROR "),
	FATAL		("FATAL "),
	EXCEPTION	("EXCEPT"),

	;

	private String format;

	private DefaultLoggerLevel(String format)
	{
		this.setFormat(format);
	}

	@Override
	public String getLabel()
	{
		return this.toString();
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

	public void setFormat(String format)
	{
		this.format = format;
	}
}
