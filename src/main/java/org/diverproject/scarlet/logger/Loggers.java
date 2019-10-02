package org.diverproject.scarlet.logger;

public interface Loggers<L extends Logger>
{
	public L get(Class<?> classz);
	public L get(String name);
	public L getDefault();
}
