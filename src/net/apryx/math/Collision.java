package net.apryx.math;

//TODO implement this
public final class Collision {
	
	private Collision(){
		
	}
	
	public static boolean overlapsAABB(float l1, float r1, float t1, float b1, float l2, float r2, float t2, float b2){
		if(
				l1 >= r2 ||
				l2 >= r1 ||
				
				t1 >= b2 ||
				t2 >= b1
				)
			return false;
		
		return true;
	}
	
}
