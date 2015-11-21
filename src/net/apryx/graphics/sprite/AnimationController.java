package net.apryx.graphics.sprite;

import java.util.HashMap;

//TODO add triggers for imidiate transitions (par examplar: jump can be an interuption)
public class AnimationController {
	
	private AnimationState state;
	private HashMap<String, Object> variables;
	private HashMap<String, AnimationState> triggers;
	
	public AnimationController(){
		this(null);
	}
	
	public AnimationController(AnimationState defaultState){
		this.state = defaultState;
		variables = new HashMap<String, Object>();
		triggers = new HashMap<String, AnimationState>();
	}
	
	public void update(){
		AnimationState current = state;

		current = state.update(this);
		int t = 0;
		while(current != state){
			state = current;
			current = state.update(this);
			
			t++;
			if(t > 6)
				break;
		}
	}
	
	public AnimationController addTrigger(String trigger, AnimationState value){
		triggers.put(trigger, value);
		return this;
	}
	
	public AnimationController trigger(String trigger){
		this.state = triggers.get(trigger);
		return this;
	}
	
	public void setState(AnimationState state) {
		this.state = state;
	}
	
	public AnimationState getState() {
		return state;
	}
	
	public void setValue(String variable, Object value){
		variables.put(variable, value);
	}
	
	public Object getValue(String variable){
		return variables.get(variable);
	}
	
	public Sprite getCurrentSprite(){
		return state.getAnimation().getCurrentSprite();
	}
}
