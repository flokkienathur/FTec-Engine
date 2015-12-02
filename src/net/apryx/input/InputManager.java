package net.apryx.input;

import java.util.ArrayList;

import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Keyboard;
import net.java.games.input.Mouse;

public class InputManager {

	private ArrayList<Mouse> mice;
	private ArrayList<Keyboard> keyboards;
	private ArrayList<Controller> controllers;
	
	private float[] x;
	private float[] y;
	
	private boolean[] left;
	private boolean[] right;
	
	public InputManager(){
		mice = new ArrayList<Mouse>();
		keyboards = new ArrayList<Keyboard>();
		controllers = new ArrayList<Controller>();
		
		ControllerEnvironment ec = ControllerEnvironment.getDefaultEnvironment();
		
		for(Controller c : ec.getControllers()){
			if(c.getType() == Controller.Type.MOUSE){
				mice.add((Mouse)c);
			}else if(c.getType() == Controller.Type.KEYBOARD){
				keyboards.add((Keyboard)c);
			}else{
				controllers.add(c);
			}
		}
		
		x = new float[getMouseCount()];
		y = new float[getMouseCount()];
		left = new boolean[getMouseCount()];
		right = new boolean[getMouseCount()];
	}
	
	public int getMouseCount(){
		return mice.size();
	}
	
	public void poll(){
		for(int i = 0; i < getMouseCount(); i++){
			mice.get(i).poll();
			x[i] = mice.get(i).getX().getPollData();
			y[i] = mice.get(i).getY().getPollData();
			left[i] = mice.get(i).getLeft().getPollData() > 0.5f;
			right[i] = mice.get(i).getRight().getPollData() > 0.5f;
		}
		for(int i = 0; i < keyboards.size(); i++){
			keyboards.get(i).poll();
		}
		for(int i = 0; i < controllers.size(); i++){
			controllers.get(i).poll();
		}
	}
	
	public float getMouseX(int mouse){
		return x[mouse];
	}
	
	public float getMouseY(int mouse){
		return y[mouse];
	}

	public boolean getLeftMouseButtonDown(int index){
		return left[index];
	}
	
	public boolean getRightMouseButtonDown(int index){
		return right[index];
	}
	
	public ArrayList<Mouse> getMice() {
		return mice;
	}
	
	public ArrayList<Keyboard> getKeyboards() {
		return keyboards;
	}
	
	public ArrayList<Controller> getControllers() {
		return controllers;
	}
	
}
