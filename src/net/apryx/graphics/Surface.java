package net.apryx.graphics;

import java.nio.FloatBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

import net.apryx.ftec.engine.FTec;
import net.apryx.graphics.opengl.Texture;

public class Surface{
	
	protected Texture texture;
	protected int frameBufferId;
	protected int width, height;
	
	public Surface(int width, int height){
		super();
		this.width = width;
		this.height = height;
		//set the data for the frame buffer
		
		texture = new Texture();
		texture.bind();
		texture.texParameter(Texture.WRAP_S, Texture.CLAMP);
		texture.texParameter(Texture.WRAP_T, Texture.CLAMP);
		texture.texParameter(Texture.MIN_FILTER, Texture.NEAREST);
		texture.texParameter(Texture.MAG_FILTER, Texture.NEAREST);
		texture.setData(width, height, (FloatBuffer) null, Texture.RGBA);
		texture.unbind();
		
		//generate framebuffer
		frameBufferId = GL30.glGenFramebuffers();
		
		bind();
		//attach this image to the frame buffer
		GL30.glFramebufferTexture2D(GL30.GL_FRAMEBUFFER, GL30.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, texture.getId(), 0);
		
		unbind();
	}
	
	public void bind(){
		GL11.glViewport(0, 0, width, height);
		GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, frameBufferId);
	}
	
	public void unbind(){
		GL11.glViewport(0, 0, FTec.window.getWidth(), FTec.window.getHeight());
		GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, 0);
	}
	
	public void clear(){
		bind();
		FTec.clear();
		unbind();
	}

	public Texture getTexture() {
		return texture;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getId() {
		return frameBufferId;
	}
	
	public void dispose(){
		texture.dispose();
		GL30.glDeleteFramebuffers(frameBufferId);
	}
	
}
