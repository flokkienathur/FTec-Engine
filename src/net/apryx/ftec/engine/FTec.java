package net.apryx.ftec.engine;

import net.apryx.graphics.Window;
import net.apryx.graphics.opengl.GL;
import net.apryx.timing.Time;

import org.lwjgl.opengl.GL11;

public class FTec {
	
	public static Window window;
	
	//local copy for reasons
	private static float deltaTime;
	private static float runTime;
	
	public static void clear(){
		GL.clear(GL.DEPTH_BUFFER_BIT | GL.COLOR_BUFFER_BIT);
	}

	public static void create(Game game){
		 window = new Window(1024,768, false);
		 
		 window.setVSync(false);
		 window.setVisible(true);
		 
		 window.setCursorHidden(false);
		 
		 GL11.glEnable(GL11.GL_DEPTH_TEST);
		 
		 //Fuck face culling, makes inverting sprites 1000 times less easy, since this engine is mainly 2D, face culling for performance is not useful
		 //GL11.glEnable(GL11.GL_CULL_FACE);
		 
		 GL11.glEnable(GL11.GL_BLEND); 
		 GL11.glBlendFunc (GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		 GL11.glEnable(GL11.GL_ALPHA_TEST);
		 GL11.glAlphaFunc(GL11.GL_GREATER, 0.5f);
		 
		 game.init();
		 
		 long previous = System.nanoTime();
		 long sum = 0;
		 int fps = 0;
		 
		 Time.deltaTime = 0f;
		 
		 while(!game.isCloseRequested()){
			 window.pollEvents();
			 
			 long current = System.nanoTime();
			 long delta = current - previous;
			 previous = current;
			 
			 deltaTime = delta / 1000000000f;
			 runTime += deltaTime;
			 
			 //Time.deltaTime = (Time.deltaTime * 99 + deltaTime) / 100f;
			 Time.deltaTime = deltaTime;
			 Time.runTime = runTime;
			 
			 sum += delta;
			 
			 if(sum > 1000000000){
				 System.out.println("FPS: " + fps);
				 Time.fps = fps;
				 sum -= 1000000000;
				 fps = 0;
			 }
			 
			 
			 game.update();
			 game.render();
			 fps++;
			 
			 window.swap();
			 //window.sleep(4);
		 }
		 
		 game.destroy();
		 
		 window.destroy();
	}
}
