package org.diverproject.scarlet.logger;

import java.io.Closeable;

public interface LoggerSubject extends Closeable
{
	public boolean register(LoggerObserver observer);
	public boolean unregister(LoggerObserver observer);
	public void notify(MessageOutput message);
	public int getObserversCount();
	public void feedLine();
}
