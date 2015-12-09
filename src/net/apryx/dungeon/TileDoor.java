package net.apryx.dungeon;

import net.apryx.graphics.SpriteBatch;
import net.apryx.graphics.opengl.Texture;

public class TileDoor extends Tile{
	
	public TileDoor(Texture texture) {
		super(texture);
	}
	
	@Override
	public void draw(SpriteBatch batch, float x, float y, float z) {
		batch.setTexture(this.texture);
		Tile.wood.draw(batch, x, y, z);
		
		y += 0.4f;
		
		batch.uv(texture.getTexCoordX(), texture.getTexCoordY2());
		batch.vertex( x,z, y);
		batch.uv(texture.getTexCoordX2(), texture.getTexCoordY2());
		batch.vertex( x+TILE_SIZE,z, y);
		batch.uv(texture.getTexCoordX2(), texture.getTexCoordY());
		batch.vertex( x+TILE_SIZE,z+TILE_SIZE, y);

		batch.uv(texture.getTexCoordX(), texture.getTexCoordY2());
		batch.vertex( x,z, y);
		batch.uv(texture.getTexCoordX2(), texture.getTexCoordY());
		batch.vertex( x+TILE_SIZE,z+TILE_SIZE, y);
		batch.uv(texture.getTexCoordX(), texture.getTexCoordY());
		batch.vertex( x,z+TILE_SIZE, y);
		
		y += 0.2f;
		
		batch.uv(texture.getTexCoordX(), texture.getTexCoordY2());
		batch.vertex( x,z, y);
		batch.uv(texture.getTexCoordX2(), texture.getTexCoordY2());
		batch.vertex( x+TILE_SIZE,z, y);
		batch.uv(texture.getTexCoordX2(), texture.getTexCoordY());
		batch.vertex( x+TILE_SIZE,z+TILE_SIZE, y);

		batch.uv(texture.getTexCoordX(), texture.getTexCoordY2());
		batch.vertex( x,z, y);
		batch.uv(texture.getTexCoordX2(), texture.getTexCoordY());
		batch.vertex( x+TILE_SIZE,z+TILE_SIZE, y);
		batch.uv(texture.getTexCoordX(), texture.getTexCoordY());
		batch.vertex( x,z+TILE_SIZE, y);
		
		y -= 0.6f;
	}
}
