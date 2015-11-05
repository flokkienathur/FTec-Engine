package net.apryx.ftec.graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import javax.imageio.ImageIO;

import net.apryx.graphics.Texture;
import net.apryx.utils.BufferUtils;

public class TextureLoader {
	
	public TextureLoader(){
	}
	
	public static Texture loadTexture(File file){
		try{
			BufferedImage image = ImageIO.read(file);
            
            /*BufferedImage buffer = new BufferedImage(raw.getWidth(),raw.getHeight(),BufferedImage.TYPE_INT_ARGB);
             
            buffer.getGraphics().drawImage(raw, 0, 0, null);
             
            int[] pixels = ((DataBufferInt) buffer.getRaster().getDataBuffer()).getData();
             
            ByteBuffer bb = ByteBuffer.allocateDirect(buffer.getWidth() * buffer.getHeight() * 4);
            bb.order(ByteOrder.nativeOrder());
             
            for(int i = 0; i < pixels.length; i ++){
                int argb = pixels[i];
                 
                byte r = (byte) ((argb >> 16) & 0xFF);
                byte g = (byte) ((argb >> 8) & 0xFF);
                byte b = (byte) ((argb) & 0xFF);
                byte a = (byte) ((argb >> 24) & 0xFF);
                 
                bb.put(r).put(g).put(b).put(a);
            }
             
            bb.flip();*/
			
			ByteBuffer bb = BufferUtils.createByteBuffer(image.getWidth() * image.getHeight() * 4);
			
			for(int y = 0; y < image.getHeight(); y++){
				for(int x = 0; x < image.getWidth(); x++){
					int argb = image.getRGB(x, y);
					
					byte r = (byte) ((argb >> 16) & 0xFF);
	                byte g = (byte) ((argb >> 8) & 0xFF);
	                byte b = (byte) ((argb) & 0xFF);
	                byte a = (byte) ((argb >> 24) & 0xFF);
	                 
	                bb.put(r).put(g).put(b).put(a);
				}
			}
			
			bb.flip();

			Texture texture = new Texture();
			texture.bind();
			texture.texParameter(Texture.WRAP_S, Texture.CLAMP);
			texture.texParameter(Texture.WRAP_T, Texture.CLAMP);
			texture.texParameter(Texture.MIN_FILTER, Texture.LINEAR);
			texture.texParameter(Texture.MAG_FILTER, Texture.LINEAR);
			texture.setData(image.getWidth(), image.getHeight(), bb, Texture.RGBA);
			texture.unbind();
			
			return texture;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static Texture loadTexture(int width, int height, int colorDepth){
		Texture texture = new Texture();
		texture.bind();
		
		texture.texParameter(Texture.WRAP_S, Texture.CLAMP);
		texture.texParameter(Texture.WRAP_T, Texture.CLAMP);
		texture.texParameter(Texture.MIN_FILTER, Texture.NEAREST);
		texture.texParameter(Texture.MAG_FILTER, Texture.NEAREST);
		
		texture.setData(width, height, BufferUtils.createFloatBuffer(width * height * 3), Texture.RGB);

		texture.unbind();
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
