package net.apryx.input;

import net.apryx.ftec.engine.FTec;

public class Input {
	
	public static float getMouseX(){
		return FTec.window.getMouseX();
	}
	
	public static float getMouseY(){
		return FTec.window.getMouseY();
	}
	
	public static float getMouseDX(){
		return FTec.window.getMouseDX();
	}
	
	public static float getMouseDY(){
		return FTec.window.getMouseDY();
	}

	public static boolean isKeyPressed(int keycode){
		return FTec.window.isKeyPressed(keycode);
	}
	
	public static boolean isKeyDown(int keycode){
		return FTec.window.isKeyDown(keycode);
	}
	
	public static boolean isKeyReleased(int keycode){
		return FTec.window.isKeyReleased(keycode);
	}

	public static boolean isMouseButtonPressed(int keycode){
		return FTec.window.isMouseButtonPressed(keycode);
	}
	
	public static boolean isMouseButtonDown(int keycode){
		return FTec.window.isMouseButtonDown(keycode);
	}
	
	public static boolean isMouseButtonReleased(int keycode){
		return FTec.window.isMouseButtonReleased(keycode);
	}
}
