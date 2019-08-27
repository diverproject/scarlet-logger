package org.diverproject.scarlet.logger.message;

import org.diverproject.scarlet.logger.MessageOutput;
import org.diverproject.scarlet.util.StringUtils;

public class SimpleMessage implements MessageOutput
{
	private String output;

	public SimpleMessage()
	{
		this("");
	}

	public SimpleMessage(String output)
	{
		this.setOutput(output);
	}

	@Override
	public String getOutput()
	{
		return this.output;
	}

	public void setOutput(String output)
	{
		this.output = output;
	}

	@Override
	public String toString()
	{
		return	StringUtils.objectToString(
					this,
					"output", this.getOutput()
				);
	}
}
