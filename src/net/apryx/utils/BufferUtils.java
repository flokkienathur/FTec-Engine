package net.apryx.utils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class BufferUtils {
	
	public static FloatBuffer createFloatBuffer(int floatsPerVertex, int vertexCount){
		return createFloatBuffer(floatsPerVertex * vertexCount);
	}
	
	public static FloatBuffer createFloatBuffer(int capacity){
		return ByteBuffer.allocateDirect(capacity * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
	}
	
	public static IntBuffer createIntBuffer(int capacity){
		return ByteBuffer.allocateDirect(capacity * 4).order(ByteOrder.nativeOrder()).asIntBuffer();	
	}
	
	public static FloatBuffer createFloatBuffer(float[] floats){
		FloatBuffer out = createFloatBuffer(floats.length);
		out.put(floats);
		out.flip();
		return out;
	}
}
