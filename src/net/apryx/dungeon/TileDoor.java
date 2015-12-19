package net.apryx.dungeon;

import net.apryx.graphics.SpriteBatch;
import net.apryx.graphics.opengl.Texture;

public class TileDoor extends Tile{
	
	public TileDoor(Texture texture) {
		super(texture);
	}
	
	@Override
	public void draw(Dungeon dungeon, SpriteBatch batch, int x, int y, int z) {
		
		float xx = x + 0.5f;
		float yy = y + 0.5f;
		float zz = z + 0.5f;
		
		batch.setTexture(this.texture);
		
		
		Tile.wood.draw(dungeon, batch, x, y, z);

		batch.drawQuad(texture, xx, yy, zz + 0.1f, 0, 1, 1, 1, 0.5f, 0.5f);
		batch.drawQuad(texture, xx, yy, zz - 0.1f, 0, -1, 1, 1, 0.5f, 0.5f);
		
	}
}
