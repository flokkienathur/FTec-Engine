package net.apryx.graphics.sprite;

import java.util.ArrayList;

public class AnimationState {
	
	private Animation animation;
	private ArrayList<AnimationTransition> transitions;
	
	public AnimationState(Animation animation){
		this.animation = animation;
		this.transitions = new ArrayList<AnimationTransition>();
	}
	
	public void addTransition(AnimationTransition transition){
		this.transitions.add(transition);
	}
	
	public void removeTransition(AnimationTransition transition){
		this.transitions.remove(transition);
	}
	
	public AnimationState update(AnimationController controller){
		animation.update();
		
		for(AnimationTransition transition : transitions){
			if(transition.check(controller, this)){
				return transition.getTarget();
			}
		}
		
		return this;
	}
	
	public Animation getAnimation() {
		return animation;
	}

}
