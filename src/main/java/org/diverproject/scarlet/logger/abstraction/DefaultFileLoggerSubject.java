package org.diverproject.scarlet.logger.abstraction;

import static org.diverproject.scarlet.logger.language.LoggerLanguage.FEED_LINE_FILE_CHANNEL;
import static org.diverproject.scarlet.logger.language.LoggerLanguage.OPEN_FILE_CHANNEL;
import static org.diverproject.scarlet.logger.language.LoggerLanguage.WRITE_FILE_CHANNEL;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import org.diverproject.scarlet.logger.LoggerRuntimeException;
import org.diverproject.scarlet.logger.MessageOutput;
import org.diverproject.scarlet.util.StringUtils;

public class DefaultFileLoggerSubject extends DefaultLoggerSubject
{
	private File file;
	private FileOutputStream fileOutputStream;
	private long oldFileLength;
	private long writedBytes;

	public DefaultFileLoggerSubject(String pathname)
	{
		this(new File(pathname));
	}

	public DefaultFileLoggerSubject(File file)
	{
		try {

			this.oldFileLength = file.length();
			this.fileOutputStream = new FileOutputStream(file, true);
			this.file = file;
			this.register(DefaultLoggerObserver.getInstance());

		} catch (IOException e) {
			throw new LoggerRuntimeException(e, OPEN_FILE_CHANNEL, this.file.getAbsolutePath());
		}
	}

	@Override
	public void notify(MessageOutput message)
	{
		super.notify(message);

		try {

			String output = message.getOutput();
			ByteBuffer byteBuffer = ByteBuffer.allocate(output.length());
			byteBuffer.put(output.getBytes());
			byteBuffer.flip();
			{
				this.writedBytes = byteBuffer.position();
				this.fileOutputStream.getChannel().write(byteBuffer);
			}
			byteBuffer = null;

		} catch (IOException e) {
			throw new LoggerRuntimeException(e, WRITE_FILE_CHANNEL, this.file.getAbsolutePath());
		}
	}

	@Override
	public void feedLine()
	{
		super.feedLine();

		try {

			ByteBuffer byteBuffer = ByteBuffer.allocate(1);
			byteBuffer.put((byte) '\n');
			byteBuffer.flip();
			{
				this.writedBytes = byteBuffer.position();
				this.fileOutputStream.getChannel().write(byteBuffer);
			}
			byteBuffer = null;

		} catch (IOException e) {
			throw new LoggerRuntimeException(e, FEED_LINE_FILE_CHANNEL, this.file.getAbsolutePath());
		}
	}

	@Override
	public void close() throws IOException
	{
		super.close();

		this.fileOutputStream.close();
	}

	private File getFile()
	{
		return this.file;
	}

	public long getOldFileLength()
	{
		return this.oldFileLength;
	}

	public long getWritedBytes()
	{
		return this.writedBytes;
	}

	@Override
	public String toString()
	{
		return	StringUtils.objectToString(
					this,
					"file", this.getFile().getAbsolutePath(),
					"oldFileLength", this.getOldFileLength(),
					"writedBytes", this.getWritedBytes()
				);
	}
}
