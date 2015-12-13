package net.apryx.dungeon;

import net.apryx.ftec.level.Entity;
import net.apryx.graphics.SpriteBatch;
import net.apryx.graphics.opengl.Texture;

public class EntityThing extends Entity{
	
	private Texture texture;
	private EntityPlayer player;
	
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
		float size = 0.5f * Tile.TILE_SIZE * texture.getWidth() / 8f;
		
		batch.setTexture(texture);
		
		batch.drawQuad(texture, x, 0, y, player.front.x, player.front.z, size, size, size/2, 0);
		
	}
}
