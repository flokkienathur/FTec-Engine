package net.apryx.utils;

public class MemoryUtils {
	public static final boolean testFlag(int value, int containing){
		return (value & containing) != 0;
	}
}
