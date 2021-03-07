package com.CC.app.ds.math.maxlikelihood;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.math.linear.MatrixUtils;
import org.apache.commons.math.linear.RealMatrix;

import java.util.Arrays;

/**
 * 利用最小二乘法进行多元一次多项式拟合的算法
 *
 */
public class LSMLogicFuction {

	/**
	 * 多项式 m 元
	 */
	public static int m;
	
	/**
	 * 多项式最高次 n
	 */
	private static int n;
	
	/**
	 * 多项式项数
	 */
	private static int q;
	
	/**
	 * 获取m元n次多项式的总项数q
	 * @param m m元
	 * @param n n次
	 * @return 总项数
	 */
	private static int getQ(){
		int k = m+1;
		for(int i=m+2;i<=m+n;i++){
			k*=i;
		}
		for(int i=2;i<=n;i++){
			k/=i;
		}
		return k;
	}
	
	/**
	 *  计算v[][J]
	 * @param xj
	 * @return v[0..q-1]
	 */
	private static double[] getVJ(double[] xj){
		double[] v = new double[q];
		double[][] xmj = new double[m][n+11];
		for(int i=0;i<m;i++){
			xmj[i][0] = 1;
			for(int j=1;j<=n+10;j++){
				xmj[i][j] = xmj[i][j-1]*xj[i];
			}
		}
		int[] I = new int[m];
		int[] J = new int[m];
		Arrays.fill(J, n);
		int k=m-1;
		int i = 0;
		while( (k>=0)){
			do{
				System.out.println(ArrayUtils.toString(I));
				v[i] = 1;
				for(int t=0;t<m;t++){
					v[i]*=xmj[t][I[m-1-t]];
				}
				i++;
				I[m-1]++;
			}while(I[m-1]<=J[m-1]);
			I[m-1]--;
			k = m-2;
			while((k>=0)&&(I[k]>=J[k]))k--;
			if(k>=0){
				I[k]=I[k+1];
				for(int t=k+1;t<m;t++)I[t] =0;
				int[] totI = new int[m];
				totI[0] = I[0];
				for(int t=1;t<totI.length;t++){
					totI[t] = totI[t-1]+I[t];
				}
				for(int t=k+1;t<m;t++){
					J[t] = n - totI[t-1];
				}
			}
		}
		return v;
	}
	
	/**
	 * 计算所有的v[i][j]值
	 * @param x 输入数据集合 X[0..R][0..m-1] 
	 * @return v[0..R][q]
	 */
	private static double[][] getV(double[][] x){
		double[][] tmp = new double[x.length][];
		for(int i=0;i<tmp.length;i++){
			tmp[i] = getVJ(x[i]);
		}
		return tmp;
	}
	
	/**
	 * 计算之前初始化整个值.主要是前提获取m,n,然后计算q
	 */
	private static void initialize(){
		q = getQ();
	}
	
	/**
	 * 获取结果
	 * @param x
	 * @param y
	 * @return
	 */
	private static  double[] getRes(double[][] x,double[] y){
		int R = x.length-1;
		double[][] v = getV(x);
		double[][] dataA = new double[q][q];
		double[] dataB = new double[q];
		for(int i=0;i<dataB.length;i++){
			double tmp = 0.0;
			for(int j=0;j<=R;j++){
				tmp+=v[j][i]*y[j];
			}
			dataB[i] = tmp;
		}
		for(int m=0;m<q;m++)
			for(int n=0;n<q;n++){
				double tmp = 0.0;
				for(int j=0;j<=R;j++){
					tmp+=v[j][n]*v[j][m];
				}
				dataA[m][n] = tmp;
			}
		RealMatrix matrixA = MatrixUtils.createRealMatrix(dataA);
		return matrixA.solve(dataB);
	}
	
	/**
	 * 计算
	 * @param m m元1次
	 * @param x
	 * @param y
	 * @return
	 */
	public static double[] computeLSM(int m, double [][] x, double[] y){
		LSMLogicFuction.m = m;
		LSMLogicFuction.n = 1;
		initialize();
		return getRes(x, y);
	}
	
}

