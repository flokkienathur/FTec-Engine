package net.apryx.graphics.sprite;

import java.util.ArrayList;

public class AnimationTransition {
	
	private ArrayList<AnimationCondition<?>> conditions;
	private AnimationState target;
	
	public AnimationTransition(AnimationState target){
		this.setTarget(target);
		this.conditions = new ArrayList<AnimationCondition<?>>();
	}
	
	public boolean check(AnimationController controller, AnimationState currentState){
		boolean check = true;
		
		for(AnimationCondition<?> c : conditions){
			check = check && c.check(controller, currentState);
		}
		
		return check;
	}
	
	public AnimationTransition addCondition(AnimationCondition<?> condition){
		conditions.add(condition);
		return this;
	}
	
	public AnimationTransition removeCondition(AnimationCondition<?> condition){
		conditions.remove(condition);
		return this;
	}

	public AnimationState getTarget() {
		return target;
	}

	public void setTarget(AnimationState target) {
		this.target = target;
	}
}
