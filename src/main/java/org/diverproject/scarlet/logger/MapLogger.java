package org.diverproject.scarlet.logger;

import java.util.Set;

public interface MapLogger<L extends Logger>
{
	public boolean isInstanceNewLoggers();
	public Class<? extends L> getDefaultClassLogger();
	public boolean add(L logger);
	public L get(String name) throws LoggerRuntimeException;
	public boolean remove(L logger);
	public boolean remove(String name);
	public boolean contains(L logger);
	public boolean contains(String name);
	public boolean hasAvaiableName(String name);
	public Set<String> names();
	public void clear();
	public int size();
}
