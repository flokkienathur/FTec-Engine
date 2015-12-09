package net.apryx.dungeon;

import net.apryx.graphics.SpriteBatch;
import net.apryx.graphics.TextureRegion;
import net.apryx.graphics.opengl.Texture;

public class Tile {
	
	public static final float TILE_SIZE = 1;

	public static Tile stone;
	public static Tile wood;
	public static Tile door;
	
	protected Texture texture;
	
	public Tile(Texture texture){
		this.texture = texture;
	}
	
	public void draw(SpriteBatch batch, float x, float y, float z){
		batch.setTexture(this.texture);

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
	
	static{
		int s = 16;
		stone = new TileWall(new TextureRegion(HeavenDungeon.tile, 0, 0, s, s));
		wood = new TileFloor(new TextureRegion(HeavenDungeon.tile, 0, s, s, s));
		door = new TileDoor(new TextureRegion(HeavenDungeon.tile, s, 0, s, s));
	}
}
