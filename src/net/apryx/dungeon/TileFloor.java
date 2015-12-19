package net.apryx.dungeon;

import net.apryx.graphics.SpriteBatch;
import net.apryx.graphics.opengl.Texture;

public class TileFloor extends Tile{
	
	public TileFloor(Texture texture) {
		super(texture);
	}

	@Override
	public void draw(Dungeon dungeon, SpriteBatch batch, int x, int y, int z) {
		batch.setTexture(this.texture);
		
		float xx = x + 0.5f;
		float yy = y + 0.5f;
		float zz = z + 0.5f;
		
		batch.drawPlane(texture, xx, yy - 0.5f, zz,  1,  1, 1, 0.5f, 0.5f);		
		batch.drawPlane(texture, xx, yy + 0.5f, zz,  -1,  1, 1, 0.5f, 0.5f);
		
	}
}
