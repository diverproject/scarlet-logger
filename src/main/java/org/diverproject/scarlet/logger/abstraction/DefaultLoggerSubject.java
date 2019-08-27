package org.diverproject.scarlet.logger.abstraction;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import org.diverproject.scarlet.logger.LoggerObserver;
import org.diverproject.scarlet.logger.LoggerSubject;
import org.diverproject.scarlet.logger.MessageOutput;

public class DefaultLoggerSubject implements LoggerSubject
{
	private Set<LoggerObserver> observers;

	public DefaultLoggerSubject()
	{
		this.observers = new LinkedHashSet<LoggerObserver>();
		this.register(DefaultLoggerObserver.getInstance());
	}

	@Override
	public boolean register(LoggerObserver observer)
	{
		return this.observers.add(observer);
	}

	@Override
	public boolean unregister(LoggerObserver observer)
	{
		return this.observers.remove(observer);
	}

	@Override
	public void notify(MessageOutput message)
	{
		this.observers.forEach(output -> {
			output.onMessage(this, message);
		});
	}

	@Override
	public int getObserversCount()
	{
		return this.observers.size();
	}

	@Override
	public void feedLine()
	{
		this.observers.forEach(observer -> {
			observer.onMessage("\n");
		});
	}

	@Override
	public void close() throws IOException
	{
		this.observers.clear();
	}
}
