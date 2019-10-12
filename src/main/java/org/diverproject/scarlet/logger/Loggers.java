package org.diverproject.scarlet.logger;

public interface Loggers<L extends Logger>
{
	public boolean contains(String name);
	public L get(Class<?> targetClass);
	public L get(String name);
	public L getDefault();
}
