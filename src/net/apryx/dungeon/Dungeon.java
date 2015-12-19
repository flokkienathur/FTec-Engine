package net.apryx.dungeon;

import net.apryx.graphics.SpriteBatch;

public class Dungeon {
	
	int width = 10;
	int height = 10;
	
	private Tile[] tiles;
	
	public Dungeon(){
		tiles = new Tile[10*10];
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				tiles[i + j * 10] = Tile.wood;
			}
		}
		for(int i = 0; i < 10; i++){
			tiles[i + 0 * 10] = Tile.stone;
			tiles[i + 9 * 10] = Tile.stone;
		}
		for(int j = 0; j < 10; j++){
			tiles[0 + j * 10] = Tile.stone;
			tiles[9 + j * 10] = Tile.stone;
		}
		
		tiles[0 + 0 * 10] = Tile.wood;
		
		tiles[6 + 3 * 10] = Tile.stone;

		tiles[8 + 5 * 10] = Tile.stone;
		tiles[7 + 5 * 10] = Tile.stone;
		tiles[6 + 5 * 10] = Tile.stone;

		tiles[5 + 5 * 10] = Tile.door;
		
		tiles[4 + 5 * 10] = Tile.stone;
		tiles[3 + 5 * 10] = Tile.stone;
		tiles[2 + 5 * 10] = Tile.stone;
		tiles[1 + 5 * 10] = Tile.stone;
		
	}
	
	public boolean isSolid(int x, int y){
		return x < 0 || x >= width || y < 0 || y >= height || tiles[x + y * width] == null || tiles[x + y * width].isSolid();
	}
	
	
	public void draw(SpriteBatch batch){
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				if(tiles[i + j * 10] != null){
					tiles[i + j * 10].draw(this, batch, i, 0, j);
				}
			}
		}
	}
	
}
