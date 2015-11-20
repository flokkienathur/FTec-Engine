package net.apryx.graphics.sprite;

//TODO add variables for transitions
//TODO add triggers for imidiate transitions (par examplar: jump can be an interuption)
public class AnimationController {
	
	private AnimationState state;
	
	public AnimationController(){
		this(null);
	}
	
	public AnimationController(AnimationState defaultState){
		this.state = defaultState;
	}
	
	public void update(){
		state = state.update(this);
	}
	
	public void setState(AnimationState state) {
		this.state = state;
	}
	
	public AnimationState getState() {
		return state;
	}
	
	public Sprite getCurrentSprite(){
		return state.getAnimation().getCurrentSprite();
	}
}
