package net.apryx.graphics;

import net.apryx.graphics.opengl.GL;
import net.apryx.graphics.opengl.Texture;
import net.apryx.graphics.sprite.Sprite;
import net.apryx.math.Mathf;

public final class Graphics {

	
	private SpriteBatch batch;
	
	
	public Graphics(){
		batch = new SpriteBatch(1024);
	}
	
	public void clear(){
		GL.clear(GL.COLOR_BUFFER_BIT | GL.DEPTH_BUFFER_BIT);
	}
	
	public void begin(){
		batch.begin();
	}
	
	public void end(){
		batch.end();
	}
	
	public void drawSprite(Sprite sprite, float x, float y){
		drawSprite(sprite,x,y,1,1);
	}
	
	public void drawSprite(Sprite sprite, float x, float y, float xScale, float yScale){
		drawSprite(sprite,x,y,xScale,yScale,0);
	}
	
	public void drawSprite(Sprite sprite, float x, float y, float rotation){
		drawSprite(sprite,x,y,1,1,rotation);
	}
	
	public void drawSprite(Sprite sprite, float x, float y, float xScale, float yScale, float rotation){
		float s, c, x1, x2, x3, x4, y1, y2, y3, y4, w, h, xo, yo;
		Texture t;
		
		w = sprite.getWidth() * xScale;
		h = sprite.getHeight() * yScale;

		xo = sprite.getxOffset() * xScale;
		yo = sprite.getyOffset() * xScale;
		
		if(rotation == 0){
			//1 --- 2
			//|		|
			//4 --- 3
			
			x1 = x - xo;
			x2 = x1 + w;
			x3 = x2;
			x4 = x1;
			
			y1 = y - yo;
			y2 = y1;
			
			y3 = y1 + h;
			y4 = y3;
			
		}else{
			s = Mathf.sin(rotation);
			c = Mathf.cos(rotation);
			
			//c -s  0
			//s  c  0
			//0  0  1

			x1 = x + ((-xo) * c - (-yo) * s);
			y1 = y + ((-xo) * s + (-yo) * c);

			x2 = x + ((-xo + w) * c - (-yo) * s);
			y2 = y + ((-xo + w) * s + (-yo) * c);

			x3 = x + ((-xo + w) * c - (-yo + h) * s);
			y3 = y + ((-xo + w) * s + (-yo + h) * c);

			x4 = x + ((-xo) * c - (-yo + h) * s);
			y4 = y + ((-xo) * s + (-yo + h) * c);
			
		}
		
		//1 --- 2
		//|		|
		//4 --- 3
		
		t = sprite.getTexture();
		
		batch.setTexture(t);
		
		batch.uv(t.getTexCoordX(),t.getTexCoordY());
		batch.vertex(x1, y1);
		batch.uv(t.getTexCoordX2(),t.getTexCoordY2());
		batch.vertex(x3, y3);
		batch.uv(t.getTexCoordX2(),t.getTexCoordY());
		batch.vertex(x2, y2);

		batch.uv(t.getTexCoordX(),t.getTexCoordY());
		batch.vertex(x1, y1);
		batch.uv(t.getTexCoordX(),t.getTexCoordY2());
		batch.vertex(x4, y4);
		batch.uv(t.getTexCoordX2(),t.getTexCoordY2());
		batch.vertex(x3, y3);

	}
	
	
}
