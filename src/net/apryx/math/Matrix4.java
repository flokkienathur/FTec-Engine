package net.apryx.math;

import java.nio.FloatBuffer;
import java.util.Arrays;

public class Matrix4 {
	
	private float[] data;
	
	//[ 0, 1, 2, 3]
	//[ 4, 5, 6, 7]
	//[ 8, 9,10,11]
	//[12,13,14,15]

	public Matrix4(float[] data){
		this.data = data;
	}
	
	public Matrix4(){
		this.data = new float[16];
		setIdentity();
	}
	
	public void setIdentity(){
		Arrays.fill(this.data, 0);
		setScale(1,1,1);
	}
	
	public void setTranslate(float x, float y, float z){
		data[3] = x;
		data[7] = y;
		data[11] = z;
	}
	
	public void setScale(float sx, float sy, float sz){
		data[0] = sx;
		data[5] = sy;
		data[10] = sz;
		data[15] = 1;
	}
	
	public static void toFloatBuffer(Matrix4 mat, FloatBuffer outputBuffer){
		outputBuffer.put(mat.data);
	}
}
