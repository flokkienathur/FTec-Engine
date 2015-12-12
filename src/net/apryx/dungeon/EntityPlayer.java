package net.apryx.dungeon;

import net.apryx.ftec.level.Entity;
import net.apryx.input.Input;
import net.apryx.input.Keys;
import net.apryx.math.Mathf;
import net.apryx.math.Vector2;
import net.apryx.math.Vector3;
import net.apryx.timing.Time;

public class EntityPlayer extends Entity{
	
	public Vector3 front;
	
	public EntityPlayer(){
		front = new Vector3();
	}
	
	public void update(){
		
		float dx = Input.getMouseDX();
		float dy = Input.getMouseDY();

		world.camera.rotation.y += dx * 0.002f;
		world.camera.rotation.x += dy * 0.002f;
		
		if(world.camera.rotation.x > Mathf.PI / 2)
			world.camera.rotation.x = Mathf.PI / 2;
		
		if(world.camera.rotation.x < -Mathf.PI / 2)
			world.camera.rotation.x = -Mathf.PI / 2;
		
		Vector2 motion = new Vector2(0,0);
		
		if(Input.isKeyDown(Keys.W))
			motion.y -= 1;
		if(Input.isKeyDown(Keys.S))
			motion.y += 1;
		if(Input.isKeyDown(Keys.A))
			motion.x -= 1;
		if(Input.isKeyDown(Keys.D))
			motion.x += 1;
		
		float s = Mathf.sin(world.camera.rotation.y);
		float c = Mathf.cos(world.camera.rotation.y);
		
		//set the front and shit
		front.x = -s;
		front.z =  c;
		
		if(motion.sqrLength() > 0){
			motion.normalize();
			
			float a = 2;
			
			x += (motion.x * c + motion.y * -s) * a * Time.deltaTime;
			y += (motion.x * s + motion.y  * c) * a * Time.deltaTime;

		}
		
		world.camera.position.x = x;
		world.camera.position.z = y;
	}
	
}
