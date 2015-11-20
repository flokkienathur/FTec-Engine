package net.apryx.graphics.sprite;

public class AnimationTransition {
	
	private AnimationCondition<?> condition;
	private AnimationState target;
	
	public AnimationTransition(AnimationState target, AnimationCondition<?> condition){
		this.setTarget(target);
		this.condition = condition;
	}
	
	public boolean check(AnimationController controller, AnimationState currentState){
		return condition.check(controller, currentState);
	}

	public AnimationState getTarget() {
		return target;
	}

	public void setTarget(AnimationState target) {
		this.target = target;
	}
}
