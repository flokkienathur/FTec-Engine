package net.apryx.graphics;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public class GL {
	
	public static final int COLOR_BUFFER_BIT = GL11.GL_COLOR_BUFFER_BIT;
	public static final int DEPTH_BUFFER_BIT = GL11.GL_DEPTH_BUFFER_BIT;
	public static final int STENCIL_BUFFER_BIT = GL11.GL_STENCIL_BUFFER_BIT;

	public static final int TRIANGLES = GL11.GL_TRIANGLES;
	public static final int TRIANGLE_FAN = GL11.GL_TRIANGLE_FAN;
	public static final int LINES = GL11.GL_LINES;
	public static final int QUADS = GL11.GL_QUADS;
	
	public static int hans = 0;
	
	public static void clearColor(float r, float g, float b, float a){
		GL11.glClearColor(r,g,b,a);
	}
	
	public static void clear(int flags){
		GL11.glClear(flags);
	}
	
	public static void drawArrays(int mode, int first, int count){
		GL11.glDrawArrays(mode, first, count);
	}
	
	public static void enableVertexAttribArray(int index){
		GL20.glEnableVertexAttribArray(index);
	}
	
	public static void drawElements(int mode, int first, int count){
		GL11.glDrawElements(mode, count, GL11.GL_UNSIGNED_INT, first);
	}
	
	public static boolean checkError(){
		boolean error = false;
		
		int code = GL11.GL_NO_ERROR;
		while((code = GL11.glGetError()) != GL11.GL_NO_ERROR){
			StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
			System.err.println("OpenGL error " + code);
			
			for(StackTraceElement element : stackTrace){
				System.err.println(element);				
			}
			error = true;
		}
		
		return error;
	}
	
}
