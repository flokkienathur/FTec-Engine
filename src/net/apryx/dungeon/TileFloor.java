package net.apryx.dungeon;

import net.apryx.graphics.SpriteBatch;
import net.apryx.graphics.opengl.Texture;

public class TileFloor extends Tile{
	
	public TileFloor(Texture texture) {
		super(texture);
	}

	@Override
	public void draw(SpriteBatch batch, float x, float y, float z) {
		batch.setTexture(this.texture);
		
		batch.color(1,1,1);
		
		batch.uv(texture.getTexCoordX(), texture.getTexCoordY());
		batch.vertex( x,z, y);
		batch.uv(texture.getTexCoordX2(), texture.getTexCoordY());
		batch.vertex( x+TILE_SIZE,z, y);
		batch.uv(texture.getTexCoordX2(), texture.getTexCoordY2());
		batch.vertex( x+TILE_SIZE,z, y+TILE_SIZE);

		batch.uv(texture.getTexCoordX(), texture.getTexCoordY());
		batch.vertex( x,z, y);
		batch.uv(texture.getTexCoordX2(), texture.getTexCoordY2());
		batch.vertex( x+TILE_SIZE,z, y+TILE_SIZE);
		batch.uv(texture.getTexCoordX(), texture.getTexCoordY2());
		batch.vertex( x,z, y+TILE_SIZE);
		
		z += 1;
		
		batch.uv(texture.getTexCoordX(), texture.getTexCoordY());
		batch.vertex( x,z, y);
		batch.uv(texture.getTexCoordX2(), texture.getTexCoordY());
		batch.vertex( x+TILE_SIZE,z, y);
		batch.uv(texture.getTexCoordX2(), texture.getTexCoordY2());
		batch.vertex( x+TILE_SIZE,z, y+TILE_SIZE);

		batch.uv(texture.getTexCoordX(), texture.getTexCoordY());
		batch.vertex( x,z, y);
		batch.uv(texture.getTexCoordX2(), texture.getTexCoordY2());
		batch.vertex( x+TILE_SIZE,z, y+TILE_SIZE);
		batch.uv(texture.getTexCoordX(), texture.getTexCoordY2());
		batch.vertex( x,z, y+TILE_SIZE);
		

	}
}
