package com.CC.app.ds.math.maxlikelihood;

import org.apache.commons.math.linear.InvalidMatrixException;
import org.apache.commons.math.linear.MatrixUtils;
import org.apache.commons.math.linear.RealMatrix;
import org.apache.commons.math.random.RandomVectorGenerator;

import java.util.Random;

public class LSMRVGenerator implements RandomVectorGenerator {
	
	private RealMatrix matrixT;
	private RealMatrix matrixY;
	private int dim;
	private int[] arr;
	
	private Random random = new Random();
	
	public LSMRVGenerator(RealMatrix matrixT,
			RealMatrix matrixY,int dim) {
		super();
		this.matrixT = matrixT;
		this.matrixY = matrixY;
		this.dim = dim;
		this.arr = new int[dim];
	}

	private void nextArr(){
		for(int i=0;i<arr.length;i++){
			arr[i] = random.nextInt(matrixT.getRowDimension());
		}
		boolean f = true;
		for(int i=0;i<arr.length-1;i++){
			for(int j=i+1;j<arr.length;j++){
				if(arr[i]==arr[j]){
					f = false;
					break;
				}
			}
		}
		if(!f){
			nextArr();
		}
	}

	public double[] nextVector() {
		nextArr();
		double[][] k = new double[dim][];
		double[] r = new double[dim];
		for(int i=0;i<dim;i++){
			k[i] = matrixT.getRow(arr[i]);
			r[i] = matrixY.getEntry(arr[i], 0);
		}

		RealMatrix m1 = MatrixUtils.createRealMatrix(k);
		double[] tmp;
		try{
			tmp = m1.solve(r);
			return tmp;
		}catch(InvalidMatrixException e){
			return nextVector();
		}
	}

}
