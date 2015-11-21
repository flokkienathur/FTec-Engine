package net.apryx.math.collision;

import net.apryx.math.Mathf;

//TODO implement this
public final class CollisionHelper {

	private CollisionHelper() {

	}

	/**
	 * 2D Axis aligned bounding box check
	 * @param l1 box 1 left
	 * @param r1 box 1 right
	 * @param t1 box 1 top
	 * @param b1 box 1 bottom
	 * @param l2 box 2 left
	 * @param r2 box 2 right
	 * @param t2 box 2 top
	 * @param b2 box 2 bottom
	 * @return Whether there is an overlap
	 */
	public static boolean overlapsAABB(float l1, float r1, float t1, float b1,
			float l2, float r2, float t2, float b2) {
		if (Mathf.greaterOrEqual(l1, r2) || Mathf.greaterOrEqual(l2, r1) ||
				Mathf.greaterOrEqual(t1, b2) || Mathf.greaterOrEqual(t2, b1))
			return false;

		return true;
	}
	
	/**
	 * 1D Axis aligned bounding box check
	 * @param l1 box 1 left
	 * @param r1 box 1 right
	 * @param l2 box 2 left
	 * @param r2 box 2 right
	 * @return Whether there is an overlap
	 */
	public static boolean overlapsAABB(float l1, float r1, float l2, float r2) {
		if (Mathf.greaterOrEqual(l1, r2) || Mathf.greaterOrEqual(l2, r1))
			return false;

		return true;
	}

	public static boolean pointInTriangle(float tx1, float ty1, float tx2,
			float ty2, float tx3, float ty3, float px, float py) {
		
		//counter clockwise order
		if(
				pointLineDirection(tx2, ty2, tx1, ty1, px, py) < 0
				&& pointLineDirection(tx3, ty3, tx2, ty2, px, py) < 0
				&& pointLineDirection(tx1, ty1, tx3, ty3, px, py) < 0
				)
			return true;

		return false;
	}
	
	/**
	 * Returns the distance a 1D box can move, given its start collider, and given a collider to test against
	 * @param speed Speed the first box moves with
	 * @param aStart Lowest bound of first box
	 * @param aEnd Highest bound of first box
	 * @param bStart Lowest bound to test against
	 * @param bEnd Highest bound to test against
	 * @return distance to move
	 */
	public static float motionDistance1D(float speed, float aStart, float aEnd, float bStart, float bEnd){
		if(speed == 0)
			return 0;
		
		//TODO find fix for threshhold
		//final float THRESHOLD = 0.001f;
		//final float THRESHOLD = 0.0f;
		
		if(speed > 0){
			if(!overlapsAABB(aStart, aEnd + speed, bStart, bEnd))
				return speed;
			
			return speed - (aEnd + speed - bStart);// - THRESHOLD;
		}
		
		else{
			if(!overlapsAABB(aStart + speed, aEnd, bStart, bEnd))
				return speed;
			
			return speed + (bEnd - (aStart + speed));// + THRESHOLD;
		}
	}

	public static float pointLineDirection(float ax, float ay, float bx,
			float by, float px, float py) {
		return Mathf.sign(pointLineDistanceNormalized(ax, ay, bx, by, px, py));
	}
	
	public static float pointLineDistanceNormalized(float ax, float ay, float bx,
			float by, float px, float py) {
		return (px - ax) * (by - ay) - (py - ay) * (bx - ax);
	}

	public static float pointLineDistance(float ax, float ay, float bx,
			float by, float px, float py) {
		float normalLength = Mathf.sqrt((bx - ax) * (bx - ax) + (by - ay)
				* (by - ay));
		return pointLineDistanceNormalized(ax, ay, bx, by, px, py) / normalLength;
	}

}
