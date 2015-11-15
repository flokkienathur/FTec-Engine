package net.apryx.math;

public class FastTable {
	
	float[] cos = new float[361];
	float[] sin = new float[361];
	
	private static FastTable instance = new FastTable();

	private FastTable() {
		for (int i = 0; i <= 360; i++) {
			cos[i] = Mathf.cos(Mathf.toRadians(i));
			sin[i] = Mathf.sin(Mathf.toRadians(i));
		}
	}

	/**
	 * @param angle in degrees
	 * @return
	 */
	public float sin(int angle) {
		int angleCircle = angle % 360;
		return sin[angleCircle];
	}

	/**
	 * 
	 * @param angle in degrees
	 * @return
	 */
	public float sin(float angle){
		return sin((int) angle);
	}

	/**
	 * 
	 * @param angle in degrees
	 * @return
	 */
	public float cos(float angle){
		return cos((int) angle);
	}

	/**
	 * 
	 * @param angle in degrees
	 * @return
	 */
	public float cos(int angle) {
		int angleCircle = angle % 360;
		return cos[angleCircle];
	}

	public static FastTable getInstance() {
		return instance;
	}
}