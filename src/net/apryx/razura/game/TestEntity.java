package net.apryx.razura.game;

import net.apryx.ftec.level.Entity;
import net.apryx.graphics.Sprite;
import net.apryx.graphics.SpriteBatch;
import net.apryx.graphics.Texture;
import net.apryx.graphics.TextureRegion;
import net.apryx.math.Mathf;
import net.apryx.timing.Time;

public class TestEntity extends Entity{
	
	private Sprite sprite;
	
	private float x, y;
	private float hspeed, vspeed, rspeed;
	
	public TestEntity(Texture t){
		TextureRegion region = new TextureRegion(t, 0, 0, 32, 32);
		sprite = new Sprite(region);
		sprite.setWidth(16);
		sprite.setHeight(16);

		sprite.setxOffset(8);
		sprite.setyOffset(8);
		
		x = Mathf.random() * 160;
		y = Mathf.random() * 90;
		a = Mathf.random() * 6.28f;
		
		hspeed = 64 * Mathf.random() - 32;
		vspeed = 64 * Mathf.random() - 32;
		rspeed = 4 * Mathf.random() - 2;
	}
	
	float a = 0;
	@Override
	public void update() {
		super.update();
		
		a += rspeed * Time.deltaTime;
		
		if(x < 0 && hspeed < 0){
			hspeed = -hspeed;
		}
		if(y < 0 && vspeed < 0){
			vspeed = -vspeed;
		}
		if(x > level.camera.size.x && hspeed > 0){
			hspeed = -hspeed;
		}
		if(y > level.camera.size.y && vspeed > 0){
			vspeed = -vspeed;
		}
		
		x += hspeed * Time.deltaTime;
		y += vspeed * Time.deltaTime;
	}
	
	@Override
	public void render(SpriteBatch batch) {
		batch.drawSprite(sprite, x, y, 1f, 1f, a);
	}
}
