package net.apryx.razura.game;

import net.apryx.ftec.level.Entity;
import net.apryx.graphics.SpriteBatch;
import net.apryx.math.collision.Layer;

public class EntityWall extends Entity{
	
	public EntityWall(float w, float h){
		layer = Layer.SOLID;
		
		this.sprite.setWidth(w);
		this.sprite.setHeight(h);
	}
	
	@Override
	public void render(SpriteBatch batch) {
		super.render(batch);
		
		batch.setTexture(null);
		
		batch.color(1, 1, 1);
		
		batch.vertex(getLeft(), getTop());
		batch.vertex(getRight(), getBottom());
		batch.vertex(getRight(), getTop());

		batch.vertex(getLeft(), getTop());
		batch.vertex(getLeft(), getBottom());
		batch.vertex(getRight(), getBottom());
		
	}
}
