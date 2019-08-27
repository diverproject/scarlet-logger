package org.diverproject.scarlet.logger;

public interface LoggerObserver
{
	public void onMessage(String message);
	public void onMessage(LoggerSubject subject, MessageOutput message);
}
