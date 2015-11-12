package net.apryx.ftec.level;

import net.apryx.ftec.graphics.Renderer;
import net.apryx.graphics.SpriteBatch;

public class Tilemap extends Entity{
	
	public static int width,height,depth;
	public static int[] tiles;
	
	private SpriteBatch batch;
	
	public Tilemap(Renderer renderer){
		width = 8;
		height = 8;
		depth = 8;
		
		tiles = new int[width * height * depth];
		
		for(int z = 0; z < depth; z++){
			for(int y = 0; y < height; y++){
				for(int x = 0; x < width; x++){
					tiles[x + y * width + z * height * depth] = (int) Math.round(Math.random());
				}
			}
		}
		
		int size = 0;
		
		for(int z = 0; z < depth; z++){
			for(int y = 0; y < height; y++){
				for(int x = 0; x < width; x++){
					if(getTile(x,y,z) != 0){
						
						//front
						if(getTile(x, y, z-1) == 0){
							size += 6;
						}
	
						//back
						if(getTile(x, y, z+1) == 0){
							size += 6;
						}
	
						//right
						if(getTile(x-1, y, z) == 0){
							size += 6;
						}
						
						//left
						if(getTile(x+1, y, z) == 0){
							size += 6;
						}
						
						//top
						if(getTile(x, y+1, z) == 0){
							size += 6;
						}
						
						//bottom
						if(getTile(x, y-1, z) == 0){
							size += 6;
						}
					}
				}
			}
		}
		
		System.out.println(size);

		batch = new SpriteBatch(renderer, size);
		
		batch.begin();
		
		for(int z = 0; z < depth; z++){
			for(int y = 0; y < height; y++){
				for(int x = 0; x < width; x++){
					if(getTile(x,y,z) != 0){
						
						//front
						if(getTile(x, y, z-1) == 0){
							batch.color(1,1,1);
							
							batch.vertex(x, y, z);
							batch.vertex(x+1, y+1, z);
							batch.vertex(x+1, y, z);
		
							batch.vertex(x, y, z);
							batch.vertex(x, y+1, z);
							batch.vertex(x+1, y+1, z);
						}
	
						//back
						if(getTile(x, y, z+1) == 0){
							batch.color(0,1,1);
							batch.vertex(x, y, z+1);
							batch.vertex(x+1, y, z+1);
							batch.vertex(x+1, y+1, z+1);
		
							batch.vertex(x, y, z+1);
							batch.vertex(x+1, y+1, z+1);
							batch.vertex(x, y+1, z+1);
						}
	
						//right
						if(getTile(x-1, y, z) == 0){
							batch.color(1,0,0);
							
							batch.vertex(x, y, z);
							batch.vertex(x, y, z+1);
							batch.vertex(x, y+1, z+1);
		
							batch.vertex(x, y, z);
							batch.vertex(x, y+1, z+1);
							batch.vertex(x, y+1, z);
						}
						
						//left
						if(getTile(x+1, y, z) == 0){
							batch.color(0,1,0);
							
							batch.vertex(x+1, y, z);
							batch.vertex(x+1, y+1, z+1);
							batch.vertex(x+1, y, z+1);
		
							batch.vertex(x+1, y, z);
							batch.vertex(x+1, y+1, z);
							batch.vertex(x+1, y+1, z+1);
						}
						
						//top

						if(getTile(x, y+1, z) == 0){
							batch.color(1,1,0);
							
							batch.vertex(x, y+1, z);
							batch.vertex(x+1, y+1, z+1);
							batch.vertex(x+1, y+1, z);
		
							batch.vertex(x, y+1, z);
							batch.vertex(x, y+1, z+1);
							batch.vertex(x+1, y+1, z+1);
						}
						
						//bottom
						if(getTile(x, y-1, z) == 0){
							batch.color(1,0,1);
							
							batch.vertex(x, y, z);
							batch.vertex(x+1, y, z);
							batch.vertex(x+1, y, z+1);
		
							batch.vertex(x, y, z);
							batch.vertex(x+1, y, z+1);
							batch.vertex(x, y, z+1);
						}
					}
					
				}
			}
		}
		
		batch.end();
	}
	
	public void setTile(int x, int y, int z, int value){
		if(x < 0 || y < 0 || z < 0 || x >= width || y >= height || z >= depth)
			return;
		tiles[x + y * width + z * height * depth] = value;
	}
	
	public int getTile(int x, int y, int z){
		if(x < 0 || y < 0 || z < 0 || x >= width || y >= height || z >= depth)
			return 0;
		return tiles[x + y * width + z * height * depth];
	}
	
	@Override
	public void render(Renderer renderer) {
		renderer.draw(batch, null);
	}
}
