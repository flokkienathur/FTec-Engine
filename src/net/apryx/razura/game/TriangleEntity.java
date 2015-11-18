package net.apryx.razura.game;

import net.apryx.ftec.engine.FTec;
import net.apryx.ftec.level.Entity;
import net.apryx.graphics.SpriteBatch;
import net.apryx.input.Input;
import net.apryx.math.Mathf;
import net.apryx.math.Triangle;

public class TriangleEntity extends Entity{
	
	Triangle triangle;
	boolean hover = false;
	
	public TriangleEntity(){
		float size = 128;
		float xOffset = Mathf.random() * FTec.window.getWidth();
		float yOffset = Mathf.random() * FTec.window.getHeight();

		triangle = new Triangle(
				Mathf.random() * size + xOffset, Mathf.random() * size + yOffset,
				Mathf.random() * size + xOffset, Mathf.random() * size + yOffset,
				Mathf.random() * size + xOffset, Mathf.random() * size + yOffset
				);
	}
	
	@Override
	public void update() {
		hover = triangle.contains(Input.getMouseX(), Input.getMouseY());
	}
	
	@Override
	public void render(SpriteBatch batch) {
		if(hover)
			batch.color(1, 0, 0);
		else
			batch.color(1, 0, 1);
		
		triangle.draw(batch);
	}
}
