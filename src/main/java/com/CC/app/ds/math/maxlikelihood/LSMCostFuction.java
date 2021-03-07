package com.CC.app.ds.math.maxlikelihood;

import org.apache.commons.math.linear.MatrixUtils;
import org.apache.commons.math.linear.RealMatrix;


public class LSMCostFuction  {

	private RealMatrix matrixT;
	private RealMatrix matrixY;
	
	public LSMCostFuction(RealMatrix matrixT, RealMatrix matrixY) {
		super();
		this.matrixT = matrixT;
		this.matrixY = matrixY;
	}

	public double cost(double[] x) {
		RealMatrix matrixX  = MatrixUtils.createColumnRealMatrix(x);
		RealMatrix tmpTX = matrixT.multiply(matrixX);
		tmpTX = tmpTX.add(matrixY.scalarMultiply(-1));
		return tmpTX.getNorm();
	}

}
