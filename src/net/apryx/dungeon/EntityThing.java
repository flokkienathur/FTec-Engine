package net.apryx.dungeon;

import net.apryx.ftec.level.Entity;
import net.apryx.graphics.SpriteBatch;
import net.apryx.graphics.opengl.Texture;
import net.apryx.input.Input;
import net.apryx.input.Keys;
import net.apryx.math.Mathf;

public class EntityThing extends Entity{
	
	private Texture texture;
	private boolean bb = false;
	EntityPlayer player;
	
	public EntityThing(Texture texture){
		this.texture = texture;
	}
	
	@Override
	public void update() {
		super.update();
		if(player == null)
			player = world.getEntity(EntityPlayer.class);
	}
	
	public void render(SpriteBatch batch){
		float size = 0.25f * Tile.TILE_SIZE * texture.getWidth() / 8f;
		
		batch.setTexture(texture);
		
		bb = Input.isKeyPressed(Keys.SPACE) ? !bb : bb;

		if(bb && (texture == HeavenDungeon.fire || texture == HeavenDungeon.rock)){
			batch.uv(1, 1);
			batch.vertex( x-size,0, y);
			batch.uv(0, 1);
			batch.vertex( x+size,0, y);
			batch.uv(0, 0);
			batch.vertex( x+size,2*size, y);
	
			batch.uv(1, 1);
			batch.vertex( x-size,0, y);
			batch.uv(0, 0);
			batch.vertex( x+size,2*size, y);
			batch.uv(1, 0);
			batch.vertex( x-size,2*size, y);
			
	
			batch.uv(1, 1);
			batch.vertex( x,0, y-size);
			batch.uv(0, 1);
			batch.vertex( x,0, y+size);
			batch.uv(0, 0);
			batch.vertex( x,2*size, y+size);
	
			batch.uv(1, 1);
			batch.vertex( x,0, y-size);
			batch.uv(0, 0);
			batch.vertex( x,2*size, y+size);
			batch.uv(1, 0);
			batch.vertex( x,2*size, y-size);
		}else{

			float dx = x - player.x;
			float dy = y - player.y;
			float length = Mathf.sqrt(dx * dx + dy * dy);
			
			dx /= length / size;
			dy /= length / size;
			
			batch.uv(1, 1);
			batch.vertex( x+dy,0, y-dx);
			batch.uv(0, 1);
			batch.vertex( x-dy,0, y+dx);
			batch.uv(0, 0);
			batch.vertex( x-dy,2*size, y+dx);
			

			batch.uv(1, 1);
			batch.vertex( x+dy,0, y-dx);
			batch.uv(0, 0);
			batch.vertex( x-dy,2*size, y+dx);
			batch.uv(1, 0);
			batch.vertex( x+dy,2*size, y-dx);
		}
		
		
		batch.color(1,1,1);
	}
}
