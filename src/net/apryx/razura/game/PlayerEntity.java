package net.apryx.razura.game;

import net.apryx.ftec.engine.FTec;
import net.apryx.ftec.level.Entity;
import net.apryx.input.Input;
import net.apryx.input.Keys;
import net.apryx.math.Mathf;

public class PlayerEntity extends Entity{
	
	private float speed = 4;
	
	public PlayerEntity(){
		super();
	}
	
	@Override
	public void update() {
		level.camera.rotation.y += Input.getMouseDX() / 1000f;
		level.camera.rotation.x += Input.getMouseDY() / 1000f;
		
		if(Input.isKeyDown(Keys.SPACE)){
			level.camera.position.y += speed * FTec.deltaTime;
		}
		if(Input.isKeyDown(Keys.LEFT_SHIFT)){
			level.camera.position.y -= speed * FTec.deltaTime;
		}
		if(Input.isKeyDown(Keys.W)){
			float angle = level.camera.rotation.y - Mathf.PI / 2;
			float s = Mathf.sin(angle);
			float c = Mathf.cos(angle);

			level.camera.position.x += c * FTec.deltaTime * speed;
			level.camera.position.z += s * FTec.deltaTime * speed;
		}
		if(Input.isKeyDown(Keys.S)){
			float angle = level.camera.rotation.y - Mathf.PI / 2;
			
			float s = Mathf.sin(angle);
			float c = Mathf.cos(angle);
			

			level.camera.position.x -= c * FTec.deltaTime * speed;
			level.camera.position.z -= s * FTec.deltaTime * speed;
		}
		if(Input.isKeyDown(Keys.A)){
			float angle = level.camera.rotation.y;
			
			float s = Mathf.sin(angle);
			float c = Mathf.cos(angle);
			

			level.camera.position.x -= c * FTec.deltaTime * speed;
			level.camera.position.z -= s * FTec.deltaTime * speed;
		}
		if(Input.isKeyDown(Keys.D)){
			float angle = level.camera.rotation.y;
			
			float s = Mathf.sin(angle);
			float c = Mathf.cos(angle);
			

			level.camera.position.x += c * FTec.deltaTime * speed;
			level.camera.position.z += s * FTec.deltaTime * speed;
		}
		
		Input.setMouseGrabbed(true); 
	}
	
	
}
