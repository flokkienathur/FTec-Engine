package net.apryx.math;

public class Transform {
	
	public Bounds bounds;
	public Vector3 position;
	
	public Transform(float width, float height){
		this(width, height, 1);
	}
	
	public Transform(float width, float height, float depth){
		this(0,0,0,width,height,depth);
	}
	
	public Transform(float x, float y, float width, float height){
		this(x,y,0,width,height,1);
	}
	
	public Transform(float x, float y, float z, float width, float height, float depth){
		this(new Vector3(x,y,z), new Bounds(width,height,depth));
	}
	
	public Transform(Vector3 vector, Bounds bounds){
		this.position = vector;
		this.bounds = bounds;
	}
	
	public float getXMin(){
		return this.position.x;
	}
	
	public float getXMax(){
		return this.position.x + bounds.width;
	}

	public float getYMin(){
		return this.position.y;
	}

	public float getYMax(){
		return this.position.y + bounds.height;
	}
	
	public float getZMin(){
		return this.position.z;
	}
	
	public float getZMax(){
		return this.position.z + bounds.depth;
	}
	
	public static boolean overlaps(Transform a, Transform b){
		if(
				a.getXMin() >= b.getXMax() ||
				b.getXMin() >= a.getXMax() ||
				
				a.getYMin() >= b.getYMax() ||
				b.getYMin() >= a.getYMax() ||
				
				a.getZMin() >= b.getZMax() ||
				b.getZMin() >= a.getZMax()
				)
			return false;
		
		return true;
	}
}
