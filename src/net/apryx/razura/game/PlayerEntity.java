package net.apryx.razura.game;

import net.apryx.ftec.engine.FTec;
import net.apryx.ftec.graphics.Renderer;
import net.apryx.ftec.level.Entity;
import net.apryx.input.Input;
import net.apryx.input.Keys;
import net.apryx.math.Vector3;

public class PlayerEntity extends Entity{
	
	private Vector3 velocity;
	
	public PlayerEntity(){
		velocity = new Vector3();
	}
	
	@Override
	public void update() {
		super.update();
		
		if(Input.isKeyPressed(Keys.SPACE)){
			velocity.y = -300;
		}
		velocity.x = 0;
		if(Input.isKeyDown(Keys.LEFT)){
			velocity.x += 4 * 60;
		}
		if(Input.isKeyDown(Keys.RIGHT)){
			velocity.x -= 4 * 60;
		}
		velocity.y += 1024 * FTec.deltaTime;
		
		transform.position.add(velocity.clone().mul(FTec.deltaTime));
	}
	
	@Override
	public void render(Renderer renderer) {
		super.render(renderer);
	}
}
