package net.apryx.graphics;

import java.nio.FloatBuffer;

import org.lwjgl.opengl.GL11;

public class Texture {

	public static final int WRAP_S = GL11.GL_TEXTURE_WRAP_S;
	public static final int WRAP_T = GL11.GL_TEXTURE_WRAP_T;
	
	public static final int MIN_FILTER = GL11.GL_TEXTURE_MIN_FILTER;
	public static final int MAG_FILTER = GL11.GL_TEXTURE_MAG_FILTER;

	public static final int CLAMP = GL11.GL_CLAMP;
	public static final int REPEAT = GL11.GL_REPEAT;

	public static final int NEAREST = GL11.GL_NEAREST;
	public static final int LINEAR = GL11.GL_LINEAR;

	public static final int RGB = GL11.GL_RGB;
	public static final int RGBA = GL11.GL_RGBA;
	
	int id;
	
	public Texture(){
		id = GL11.glGenTextures();
	}
	
	public void bind(){
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, id);
	}
	
	public void unbind(){
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
	}
	
	public void texParameter(int param, int value){
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, param, value);
	}
	
	public void setData(FloatBuffer data, int colorDepth){
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, colorDepth, 2, 2, 0, colorDepth, GL11.GL_FLOAT, data);
		
	}
	
}
