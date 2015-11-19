package net.apryx.graphics;

import net.apryx.graphics.opengl.Texture;

//TODO implement class
public class Sprite {
	
	private float width, height, xOffset, yOffset;
	private Texture texture;
	
	public Sprite(Texture texture){
		this(texture, texture.getWidth(), texture.getHeight());
	}
	
	public Sprite(Texture texture, float width, float height){
		this(texture, width, height, 0, 0);
	}
	
	public Sprite(Texture texture, float width, float height, float xOffset, float yOffset){
		this.width = width;
		this.height = height;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.setTexture(texture);
	}
	
	public void center(){
		xOffset = getWidth() / 2;
		yOffset = getHeight() / 2;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getxOffset() {
		return xOffset;
	}

	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}

	public float getyOffset() {
		return yOffset;
	}

	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}
}
