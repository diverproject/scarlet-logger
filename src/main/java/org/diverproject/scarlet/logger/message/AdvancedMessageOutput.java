package org.diverproject.scarlet.logger.message;

import static org.diverproject.scarlet.logger.abstraction.DefaultLoggerLevel.NONE;

import org.diverproject.scarlet.logger.LoggerLevel;
import org.diverproject.scarlet.logger.MessageOutput;
import org.diverproject.scarlet.util.StringUtils;

public class AdvancedMessageOutput implements MessageOutput
{
	private String dateFormatted;
	private LoggerLevel level;
	private String message;
	private StackTraceElement origin;

	public String getDateFormatted()
	{
		return this.dateFormatted;
	}

	public void setDateFormatted(String dateFormatted)
	{
		this.dateFormatted = dateFormatted;
	}

	public LoggerLevel getLevel()
	{
		return this.level;
	}

	public void setLevel(LoggerLevel level)
	{
		this.level = level;
	}

	public String getMessage()
	{
		return this.message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public StackTraceElement getOrigin()
	{
		return this.origin;
	}

	public void setOrigin(StackTraceElement origin)
	{
		this.origin = origin;
	}

	public boolean hasLevel()
	{
		return this.getLevel() != null && this.getLevel() != NONE;
	}

	public boolean hasOrigin()
	{
		return this.getOrigin() != null;
	}

	@Override
	public String getOutput()
	{
		if (this.hasOrigin())
		{
			if (this.hasLevel())
				return String.format("[%s] %s | %s.%s:%d - %s",
						this.getLevel().getFormat(),
						this.getDateFormatted(),
						this.getOrigin().getClassName(),
						this.getOrigin().getMethodName(),
						this.getOrigin().getLineNumber(),
						this.getMessage()
					);

			return String.format("%s | %s.%s:%d - %s",
					this.getDateFormatted(),
					this.getOrigin().getClassName(),
					this.getOrigin().getMethodName(),
					this.getOrigin().getLineNumber(),
					this.getMessage()
				);
		}

		if (this.hasLevel())
			return String.format(
					"[%s] %s | %s",
					this.getLevel().getFormat(),
					this.getDateFormatted(),
					this.getMessage()
				);

		return String.format(
				"%s | %s",
				this.getDateFormatted(),
				this.getMessage()
			);
	}

	@Override
	public String toString()
	{
		return	StringUtils.objectToString(
					this,
					"dateFormatted", this.getDateFormatted(),
					"level", this.getLevel(),
					"message", this.getMessage(),
					"origin", this.getOrigin()
				);
	}
}
