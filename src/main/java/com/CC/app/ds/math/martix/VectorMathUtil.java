package com.CC.app.ds.math.martix;

import org.apache.commons.math.linear.MatrixUtils;

/**
 * 数学向量组的处理
 * 
 */
public class VectorMathUtil {
	
	/**
	 * 旋转中的阈值
	 */
	public static final double EPQ = 1E-10;
	
	/**
	 * 旋转时间上限
	 */
	public static final long EPQ_TIME = 10000;
	
	/**
	 * 使用施密特正交化向量算法 注意:传入的参数将被改变
	 * 
	 * @param vectors
	 *            待正交化的向量组
	 * @return 正交化后的向量组
	 */
	public static VectorMath[] orthogonalizateVectors(VectorMath[] vectors) {

		for (int i = 1; i < vectors.length; i++) {
			VectorMath tmpV = new VectorMath(vectors[i].getVectors());
			for (int j = 0; j < i; j++) {
				VectorMath tmp = new VectorMath(vectors[j].getVectors());
				tmp
						.scalarMultiply((-(vectors[i].innerMultiply(vectors[j])) / (vectors[j]
								.innerMultiplySelf())));
				tmpV.add(tmp);
			}
			vectors[i] = tmpV;
		}
		return vectors;
	}
	
	/**
	 * 单位化向量组. 
	 * vectors[i] = vectors[i]/ ||vectors[i]||
	 * @param vectors
	 * @return
	 */
	public static VectorMath[] unitVectors(VectorMath[] vectors){
		VectorMath[] tmp = new VectorMath[vectors.length];
		for(int i=0;i<tmp.length;i++){
			tmp[i] = vectors[i].scalarMultiply(1/vectors[i].lengthMatrix());
		}
		return tmp;
	}
	
	/**
	 * 在应用多元分析中的因子分析部分,一个用于计算因子旋转角度的方法
	 * 
	 * @param matrix
	 *            因子载荷矩阵
	 * @param h2
	 *            共性方差向量
	 * @param l
	 *            第l列 0<=l<p(因子载荷矩阵的维度)
	 * @param k
	 *            第k列 0<=k<p(因子载荷矩阵的维度)
	 * @return 角度
	 */
	public static double getAngleInFactorAnalyze(BasicMatrix matrix,
			VectorMath h2, int l, int k) {
		int p = matrix.getDimension();
		double[] u = new double[p];
		double[] v = new double[p];
		for (int i = 0; i < p; i++) {
			u[i] = (sqr(matrix.getEntry(i, l)) - sqr(matrix.getEntry(i, k)))
					/ (h2.getEntry(i));
			v[i] = 2.0 * matrix.getEntry(i, l) * matrix.getEntry(i, k)
					/ h2.getEntry(i);
		}
		double c1 = 0, c2 = 0, c3 = 0, c4 = 0;
		for (int i = 0; i < p; i++) {
			c1 += u[i];
			c2 += v[i];
			c3 += (sqr(u[i]) - sqr(v[i]));
			c4 += u[i] * v[i];
		}
		c4 *= 2.0;
		double st;
		double tmp = (c4 - (2.0 * c1 * c2) / p)
				/ (c3 - (sqr(c1) - sqr(c2)) / p);
		st = Math.atan(tmp) / 4.0;
		return st;
	}

	/**
	 * 生成一个用于因子旋转的正交变换矩阵
	 * 
	 * @param l
	 *            第l列 0<=l<p(因子载荷矩阵的维度)
	 * @param k
	 *            第k列 0<=k<p(因子载荷矩阵的维度)
	 * @param st
	 *            角度
	 * @param p
	 *            因子载荷矩阵的维度
	 * @return
	 */
	public static BasicMatrix getRotateOrthogonalTransformateMatrix(int l,
			int k, double st, int p) {
		BasicMatrix matrix = new BasicMatrix(MatrixUtils
				.createRealIdentityMatrix(p).getData());
		double cos = Math.cos(st);
		double sin = Math.sin(st);
		matrix.setEntry(l, l, cos);
		matrix.setEntry(l, k, sin);
		matrix.setEntry(k, l, -sin);
		matrix.setEntry(k, k, cos);
		return matrix;
	}

	/**
	 * 将传入的矩阵进行旋转
	 * TODO 可以考虑是否用时间做个卡时,虽然不会出现无法收敛的情况,但是耗时很长
	 * @param matrix
	 *            因子载荷矩阵
	 * @param h2
	 *            共性方差
	 * @return
	 */
	public static BasicMatrix toRotateMatrix(BasicMatrix matrix, VectorMath h2) {
		int p = matrix.getColumnDimension();
		double save = getRelativeVariance(matrix, h2);
		long time = System.currentTimeMillis();
		while((System.currentTimeMillis()-time)<EPQ_TIME){
			double savel = getRelativeVariance(matrix, h2);
			for (int l = 0; l < p - 1; l++)
				for (int k = l + 1; k < p; k++) {
					double st = getAngleInFactorAnalyze(matrix, h2, l, k);
					BasicMatrix rotateM = getRotateOrthogonalTransformateMatrix(
							l, k, st, p);
					matrix = new BasicMatrix(matrix.multiply(rotateM).getData());
				}
			double now = getRelativeVariance(matrix, h2);
			if ((now>=save)&&(Math.abs(now-savel)<EPQ)){
				break;
			}
		}
		return matrix;
	}

	/**
	 * 计算m列元素平方的相对方差之和
	 * 
	 * @param matrix
	 *            矩阵
	 * @param h2
	 *            共性方差向量
	 * @return 和
	 */
	public static double getRelativeVariance(BasicMatrix matrix, VectorMath h2) {
		int ip = matrix.getColumnDimension();
		int p = matrix.getRowDimension();
		double[][] d2 = new double[p][ip];
		double[] dp = new double[ip];
		double v = 0;
		for (int j = 0; j < ip; j++) {
			for (int i = 0; i < p; i++) {
				d2[i][j] = sqr(matrix.getEntry(i, j)) / h2.getEntry(i);
				dp[j] += d2[i][j];
			}
			dp[j] /= p;
			double tmp = 0;
			for (int i = 0; i < p; i++) {
				tmp += sqr(d2[i][j] - dp[j]);
			}
			v += tmp / p;
		}
		return v;
	}

	/**
	 * 求共性方差向量
	 * 
	 * @param matrix
	 *            因子载荷矩阵
	 * @return 向量
	 */
	public static VectorMath getCommonVarianceVector(BasicMatrix matrix) {
		double[] h2 = new double[matrix.getRowDimension()];
		for (int i = 0; i < h2.length; i++) {
			double tmp = 0.0;
			for (int j = 0; j < matrix.getColumnDimension(); j++) {
				tmp += sqr(matrix.getEntry(i, j));
			}
			h2[i] = tmp;
		}
		return new VectorMath(h2);
	}

	/**
	 * 求平方
	 * 
	 * @param x
	 * @return
	 */
	public static double sqr(double x) {
		return x * x;
	}
}
