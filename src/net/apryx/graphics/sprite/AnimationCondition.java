package net.apryx.graphics.sprite;

public class AnimationCondition<T extends Comparable<?>> {

	public static final int EQUAL = 0;
	public static final int GREATER = 1;
	public static final int LESS = -1;

	public static final int LEQUAL = 2;
	public static final int GEQUAL = 3;
	
	private Comparable<T> value;
	private String variableName;
	private int check;
	
	public AnimationCondition(String variableName, Comparable<T> value, int check){
		this.variableName = variableName;
		this.value = value;
		this.check = check;
	}
	
	public int getCheck() {
		return check;
	}
	
	public Comparable<T> getValue() {
		return value;
	}
	
	public String getVariableName() {
		return variableName;
	}
	
	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}
	
	public void setCheck(int check) {
		this.check = check;
	}
	
	public void setValue(Comparable<T> value) {
		this.value = value;
	}
	
	@SuppressWarnings("unchecked")
	public boolean check(AnimationController controller, AnimationState currentState){
		try{
			T t = (T) controller.getValue(variableName);
			//negative because reverse check
			int v = -value.compareTo(t);
			
			switch(check){
			case LEQUAL:
				return v <= 0;
			case GEQUAL:
				return v >= 0;
			default:
				return v == check;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return false;
	}
}
