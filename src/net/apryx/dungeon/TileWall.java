package net.apryx.dungeon;

import net.apryx.graphics.SpriteBatch;
import net.apryx.graphics.opengl.Texture;

public class TileWall extends Tile{

	public TileWall(Texture texture) {
		super(texture);
	}
	
	@Override
	public void draw(SpriteBatch batch, float x, float y, float z) {
		batch.setTexture(this.texture);
		
		batch.uv(texture.getTexCoordX(), texture.getTexCoordY());
		batch.vertex( x,z, y);
		batch.uv(texture.getTexCoordX2(), texture.getTexCoordY());
		batch.vertex( x+TILE_SIZE,z, y);
		batch.uv(texture.getTexCoordX2(), texture.getTexCoordY2());
		batch.vertex( x+TILE_SIZE,z+TILE_SIZE, y);

		batch.uv(texture.getTexCoordX(), texture.getTexCoordY());
		batch.vertex( x,z, y);
		batch.uv(texture.getTexCoordX2(), texture.getTexCoordY2());
		batch.vertex( x+TILE_SIZE,z+TILE_SIZE, y);
		batch.uv(texture.getTexCoordX(), texture.getTexCoordY2());
		batch.vertex( x,z+TILE_SIZE, y);
		
		y += 1;
		
		batch.uv(texture.getTexCoordX(), texture.getTexCoordY());
		batch.vertex( x,z, y);
		batch.uv(texture.getTexCoordX2(), texture.getTexCoordY());
		batch.vertex( x+TILE_SIZE,z, y);
		batch.uv(texture.getTexCoordX2(), texture.getTexCoordY2());
		batch.vertex( x+TILE_SIZE,z+TILE_SIZE, y);

		batch.uv(texture.getTexCoordX(), texture.getTexCoordY());
		batch.vertex( x,z, y);
		batch.uv(texture.getTexCoordX2(), texture.getTexCoordY2());
		batch.vertex( x+TILE_SIZE,z+TILE_SIZE, y);
		batch.uv(texture.getTexCoordX(), texture.getTexCoordY2());
		batch.vertex( x,z+TILE_SIZE, y);
		
		y -= 1;
		
		batch.uv(texture.getTexCoordX(), texture.getTexCoordY());
		batch.vertex( x,z, y);
		batch.uv(texture.getTexCoordX2(), texture.getTexCoordY());
		batch.vertex( x,z, y+TILE_SIZE);
		batch.uv(texture.getTexCoordX2(), texture.getTexCoordY2());
		batch.vertex( x,z+TILE_SIZE, y+TILE_SIZE);

		batch.uv(texture.getTexCoordX(), texture.getTexCoordY());
		batch.vertex( x,z, y);
		batch.uv(texture.getTexCoordX2(), texture.getTexCoordY2());
		batch.vertex( x,z+TILE_SIZE, y+TILE_SIZE);
		batch.uv(texture.getTexCoordX(), texture.getTexCoordY2());
		batch.vertex( x,z+TILE_SIZE, y);
		
		x += 1;
		
		batch.uv(texture.getTexCoordX(), texture.getTexCoordY());
		batch.vertex( x,z, y);
		batch.uv(texture.getTexCoordX2(), texture.getTexCoordY());
		batch.vertex( x,z, y+TILE_SIZE);
		batch.uv(texture.getTexCoordX2(), texture.getTexCoordY2());
		batch.vertex( x,z+TILE_SIZE, y+TILE_SIZE);

		batch.uv(texture.getTexCoordX(), texture.getTexCoordY());
		batch.vertex( x,z, y);
		batch.uv(texture.getTexCoordX2(), texture.getTexCoordY2());
		batch.vertex( x,z+TILE_SIZE, y+TILE_SIZE);
		batch.uv(texture.getTexCoordX(), texture.getTexCoordY2());
		batch.vertex( x,z+TILE_SIZE, y);
	}
	
}
