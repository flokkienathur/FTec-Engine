package net.apryx.dungeon;

import net.apryx.graphics.SpriteBatch;
import net.apryx.graphics.opengl.Texture;

public class TileWall extends Tile{

	public TileWall(Texture texture) {
		super(texture);
	}
	
	@Override
	public void draw(Dungeon dungeon, SpriteBatch batch, int x, int y, int z) {
		batch.setTexture(this.texture);
		
		float xx = x + 0.5f;
		float yy = y + 0.5f;
		float zz = z + 0.5f;

		if(!dungeon.isSolid(x+1, z))
			batch.drawQuad(texture, xx+0.5f, yy, zz, 1, 0,   1, 1, 0.5f, 0.5f);	

		if(!dungeon.isSolid(x-1, z))
			batch.drawQuad(texture, xx-0.5f, yy, zz, -1, 0,   1, 1, 0.5f, 0.5f);

		if(!dungeon.isSolid(x, z+1))
			batch.drawQuad(texture, xx, yy, zz+0.5f, 0, 1,   1, 1, 0.5f, 0.5f);

		if(!dungeon.isSolid(x, z-1))
			batch.drawQuad(texture, xx, yy, zz-0.5f, 0, -1,   1, 1, 0.5f, 0.5f);
	}
	
	public boolean isSolid(){
		return true;
	}
	
}
