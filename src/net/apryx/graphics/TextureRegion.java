package net.apryx.graphics;

import net.apryx.graphics.opengl.Texture;

public class TextureRegion extends Texture{
	
	protected float x1, x2, y1, y2;
	
	public TextureRegion(Texture texture, int x, int y, int width, int height){
		this.id = texture.getId();
		this.width = width;
		this.height = height;

		x1 = x / (float) texture.getWidth();
		x2 = (x + width) / (float) texture.getWidth();
		y1 = y / (float) texture.getHeight();
		y2 = (y + height) / (float) texture.getHeight();
	}
	
	@Override
	public float getTexCoordX() {
		return x1;
	}
	@Override
	public float getTexCoordX2() {
		return x2;
	}
	@Override
	public float getTexCoordY() {
		return y1;
	}
	@Override
	public float getTexCoordY2() {
		return y2;
	}
}
