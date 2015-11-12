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
		int vertices = 6 * 6;
			
		batch = new SpriteBatch(renderer, width * height * depth * vertices);
		
		tiles = new int[width * height * depth];
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				
			}
		}
		
		batch.begin();
		
		for(int z = 0; z < depth*2; z+=2){
			for(int y = 0; y < height*2; y+=2){
				for(int x = 0; x < width*2; x+=2){
					//front
					batch.color(1,1,1);
					
					batch.vertex(x, y, z);
					batch.vertex(x+1, y+1, z);
					batch.vertex(x+1, y, z);

					batch.vertex(x, y, z);
					batch.vertex(x, y+1, z);
					batch.vertex(x+1, y+1, z);

					//back
					batch.color(0,1,1);

					batch.vertex(x, y, z+1);
					batch.vertex(x+1, y, z+1);
					batch.vertex(x+1, y+1, z+1);

					batch.vertex(x, y, z+1);
					batch.vertex(x+1, y+1, z+1);
					batch.vertex(x, y+1, z+1);

					//right
					batch.color(1,0,0);
					
					batch.vertex(x, y, z);
					batch.vertex(x, y, z+1);
					batch.vertex(x, y+1, z+1);

					batch.vertex(x, y, z);
					batch.vertex(x, y+1, z+1);
					batch.vertex(x, y+1, z);

					//left
					batch.color(0,1,0);
					
					batch.vertex(x+1, y, z);
					batch.vertex(x+1, y+1, z+1);
					batch.vertex(x+1, y, z+1);

					batch.vertex(x+1, y, z);
					batch.vertex(x+1, y+1, z);
					batch.vertex(x+1, y+1, z+1);
					
					//top
					batch.color(1,1,0);
					
					batch.vertex(x, y+1, z);
					batch.vertex(x+1, y+1, z+1);
					batch.vertex(x+1, y+1, z);

					batch.vertex(x, y+1, z);
					batch.vertex(x, y+1, z+1);
					batch.vertex(x+1, y+1, z+1);	
					
					//bottom
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
		
		batch.end();
	}
	
	@Override
	public void render(Renderer renderer) {
		renderer.draw(batch, null);
	}
}
