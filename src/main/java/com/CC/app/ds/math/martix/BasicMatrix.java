package com.CC.app.ds.math.martix;

import org.apache.commons.math.linear.RealMatrix;
import org.apache.commons.math.linear.RealMatrixImpl;

/**
 * 具有计算特征值的矩阵实体类
 * 注意:仅支持求实对称矩阵的特征值及特征向量
 *
 */
public class BasicMatrix extends RealMatrixImpl implements RealMatrix{
	
	/**
	 * 求实对称矩阵的特征值及特征向量的雅格比法的控制精度
	 */
	public static double ACCURARY = 1E-4;
	/**
	 * 求实对称矩阵的特征值及特征向量的雅格比法的最大迭代次数
	 */
	public static int TIMES = 200000;
	/**
	 * 学习参数
	 */
	public static double CONTRIBUTION_RATIO = 0.7;
	
	
	/**
	 * 求实对称矩阵的特征值及特征向量的雅格比法
	 * 	
	 * 利用雅格比(Jacobi)方法求实对称矩阵的全部特征值及特征向量
	 * 返回值小于0表示超过迭代jt次仍未达到精度要求
	 * 返回值大于0表示正常返回
	 * a-长度为n*n的数组，存放实对称矩阵，返回时对角线存放n个特征值
	 * n-矩阵的阶数
	 * v-长度为n*n的数组，返回特征向量(按列存储)
	 * eps-控制精度要求
	 * jt-整型变量，控制最大迭代次数
	 */
	private static int getCharacteristicMartix(double a[], int n, double v[], double eps, int jt) {
	    int i, j, p=0, q=0, u, w, t, s, l;
	    double fm, cn, sn, omega, x, y, d;
	    l = 1;
	    for (i = 0; i <= n - 1; i++) {
	        v[i * n + i] = 1.0;
	        for (j = 0; j <= n - 1; j++) {
	            if (i != j) {
	                v[i * n + j] = 0.0;
	            }
	        }
	    }
	    while (1 == 1) {
	        fm = 0.0;
	        for (i = 0; i <= n - 1; i++) {
	            for (j = 0; j <= n - 1; j++) {
	                d = fabs(a[i * n + j]);
	                if ((i != j) && (d > fm)) {
	                    fm = d;
	                    p = i;
	                    q = j;
	                }
	            }
	        }
	        if (fm < eps) {
	            return (1);
	        }
	        if (l > jt) {
	            return (-1);
	        }
	        l = l + 1;
	        u = p * n + q;
	        w = p * n + p;
	        t = q * n + p;
	        s = q * n + q;
	        x = -a[u];
	        y = (a[s] - a[w]) / 2.0;
	        omega = x / sqrt(x * x + y * y);
	        if (y < 0.0) {
	            omega = -omega;
	        }
	        sn = 1.0 + sqrt(1.0 - omega * omega);
	        sn = omega / sqrt(2.0 * sn);
	        cn = sqrt(1.0 - sn * sn);
	        fm = a[w];
	        a[w] = fm * cn * cn + a[s] * sn * sn + a[u] * omega;
	        a[s] = fm * sn * sn + a[s] * cn * cn - a[u] * omega;
	        a[u] = 0.0;
	        a[t] = 0.0;
	        for (j = 0; j <= n - 1; j++) {
	            if ((j != p) && (j != q)) {
	                u = p * n + j;
	                w = q * n + j;
	                fm = a[u];
	                a[u] = fm * cn + a[w] * sn;
	                a[w] = -fm * sn + a[w] * cn;
	            }
	        }
	        for (i = 0; i <= n - 1; i++) {
	            if ((i != p) && (i != q)) {
	                u = i * n + p;
	                w = i * n + q;
	                fm = a[u];
	                a[u] = fm * cn + a[w] * sn;
	                a[w] = -fm * sn + a[w] * cn;
	            }
	        }
	        for (i = 0; i <= n - 1; i++) {
	            u = i * n + p;
	            w = i * n + q;
	            fm = v[u];
	            v[u] = fm * cn + v[w] * sn;
	            v[w] = -fm * sn + v[w] * cn;
	        }
	    }
	}
	
	/**
	 * 浮点数的绝对值
	 * @param a 浮点数
	 * @return 绝对值
	 */
	private static double fabs(double a){
		return Math.abs(a);
	}
	
	/**
	 * 浮点数平方根
	 * @param a 浮点数
	 * @return 平方根
	 */
	private static double sqrt(double a){
		return Math.sqrt(a);
	}
	
	/**
	 * 记录矩阵的特征值
	 */
	private double[] characterValues;
	
	/**
	 * 记录矩阵的特征向量
	 */
	private double[] characterVector[];
	
	
	public BasicMatrix() {
		super();
		initialize();
	}
	
	public BasicMatrix(double[] v) {
		super(v);
		initialize();
	}

	public BasicMatrix(double[][] d) {
		super(d);
		initialize();
	}

	public BasicMatrix(int rowDimension, int columnDimension) {
		super(rowDimension, columnDimension);
		initialize();
	}
	
	/**
	 * 获取该矩阵的特征值
	 * @return
	 */
	public double[] getCharacterValues() {
		
		if (characterValues==null){
			calCharacterValuesAndVectors();
		}
		return characterValues;
	}

	/**
	 * 获取该矩阵的特征向量
	 * @return
	 */
	public double[][] getCharacterVector() {
		if (characterVector==null){
			calCharacterValuesAndVectors();
		}
		return characterVector;
	}
	
	/**
	 * 获取矩阵的一个特征值
	 * @param i
	 * @return
	 */
	public double getCharacterValue(int i){
		if(characterValues==null){
			calCharacterValuesAndVectors();
		}
		return characterValues[i];
	}
	
	/**
	 * 获取该矩阵的一个特征向量
	 * @param i
	 * @return
	 */
	public double[] getCharacterVector(int i){
		if(characterVector==null){
			calCharacterValuesAndVectors();
		}
		return characterVector[i];
	}
	
	/**
	 * 计算矩阵的特征值和特征向量
	 */
	public void calCharacterValuesAndVectors(){
		double[][] martix = this.getData();
		int n = martix.length;
		characterValues = new double[n];
		characterVector = new double[n][n];
		
		double[] a = new double[n*n];
		double[] v = new double[n*n];
		for(int i=0;i<n*n;i++){
			a[i] = martix[i/n][i%n];
		}
		//计算
		getCharacteristicMartix(a, n, v, ACCURARY, TIMES);
		
		for(int i=0;i<n;i++){
			characterValues[i] = a[i+i*n];
		}
		
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				characterVector[i][j] = v[j*n+i];
			}
		}
		sortDecCharacteristicByValues(characterValues, characterVector);
	}
	
	/**
	 * 按特征值逆序排列
	 * @param values 特征值数组
	 * @param vectors 特征向量数组
	 */
	private static void sortDecCharacteristicByValues(double[] values,double[] vectors[]){
		for(int j=0;j<values.length-1;j++){
			int index	=	j;
			for(int i=j+1;i<values.length;i++){
				if(values[i]>values[index]){
					index	=	i;
				}
			}
			double temp		=	values[j];
			values[j]		=	values[index];
			values[index]	=	temp;
			double[] tv = vectors[j];
			vectors[j] = vectors[index];
			vectors[index] = tv;
		}
	}
	
	/**
	 * 一个较小的因子数目M,可以使贡献比率达到一个较高的百分比
	 * @return
	 */
	public int getMContributionNumber(){
		double tot = 0;
		for(double k:characterValues){
			tot+=k;
		}
		int m = 0;
		double all = 0;
		while(all/tot<CONTRIBUTION_RATIO){
			all+=characterValues[m];
			m++;
		}
		return m;
		
	}

	/**
	 * 初始化
	 */
	private void initialize(){
		characterValues = null;
		characterVector = null;
	}

	/**
	 * 设置矩阵中的一个值
	 * @param r
	 * @param c
	 * @param value
	 */
	public void setEntry(int r, int c, double value){
		this.getDataRef()[r][c] = value;
		this.getDataRef();
	}

	/**
	 * 数学矩阵意义上的维度.
	 * @return
	 */
	public int getDimension(){
		return this.getRowDimension();
	}
}

