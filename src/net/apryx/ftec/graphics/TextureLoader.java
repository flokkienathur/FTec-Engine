package net.apryx.ftec.graphics;

import java.nio.FloatBuffer;

import net.apryx.graphics.Texture;
import net.apryx.utils.BufferUtils;

public class TextureLoader {
	
	public TextureLoader(){
	}
	
	public static Texture loadTexture(int width, int height, int colorDepth){
		Texture texture = new Texture();
		
		texture.texParameter(Texture.WRAP_S, Texture.CLAMP);
		texture.texParameter(Texture.WRAP_T, Texture.CLAMP);
		texture.texParameter(Texture.MIN_FILTER, Texture.NEAREST);
		texture.texParameter(Texture.MAG_FILTER, Texture.NEAREST);
		
		texture.setData(width, height, BufferUtils.createFloatBuffer(width * height * 3), Texture.RGB);
		
		return texture;
	}
	
	public static Texture loadTexture(int width, int height, FloatBuffer colors, int colorDepth){
		Texture texture = new Texture();
		texture.bind();
		
		texture.texParameter(Texture.WRAP_S, Texture.CLAMP);
		texture.texParameter(Texture.WRAP_T, Texture.CLAMP);
		texture.texParameter(Texture.MIN_FILTER, Texture.NEAREST);
		texture.texParameter(Texture.MAG_FILTER, Texture.NEAREST);
		
		texture.setData(width, height, colors, Texture.RGB);
		texture.unbind();
		
		return texture;
	}
	
}
