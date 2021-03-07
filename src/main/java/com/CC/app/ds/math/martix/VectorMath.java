package com.CC.app.ds.math.martix;

import org.apache.commons.math.linear.RealMatrixImpl;

/**
 * 数学上的向量
 */

public class VectorMath extends RealMatrixImpl {
	
	/**
	 * 向量的COLUMN,这个肯定是一
	 */
	public static final int COLUMN  = 1;
	private static final int COLUMN_SELF = 0;
	
	/**
	 * 生成一行多列的向量.
	 * 
	 * @param v 各个位的值
	 */
	public VectorMath(double[] v){
		super(v);
	}
	
	public VectorMath(int row){
		super(row, COLUMN);
	}
	
	/**
	 * 生成一个多列向量
	 * @param v 待复制的向量
	 */
	public VectorMath(VectorMath v){
		super(v.getVectors());
	}
	
	public VectorMath(double[][] data) {
		super(data);
	}

	/**
	 * 向量的长度
	 * 向量的内积再开平方
	 * @return 
	 */
	public double lengthMatrix(){
		double tmp = Math.sqrt(innerMultiplySelf());
		return tmp;
	}
	
	/**
	 * 求向量自身的内积
	 * @return 内积结果
	 */
	public double innerMultiplySelf(){
		double tmp = 0;
		double[][] s = this.getDataRef();
		for(int i=0; i< this.size();i++){
			tmp+= s[i][COLUMN_SELF]*s[i][COLUMN_SELF];
		}
		return tmp;
	}
	
	/**
	 * 求向量内积
	 * @param v 将进行内积处理的向量
	 * @return
	 */
	public double innerMultiply(VectorMath v){
		int e = this.size();
		double tmp = 0;
		for(int i=0;i<e;i++){
			tmp+= this.getEntry(i, COLUMN_SELF)*v.getEntry(i,COLUMN_SELF);
		}
		return tmp;
	}
	
	/**
	 * 向量的维度
	 * @return 维度
	 */
	public int size(){
		return this.getRowDimension();
	}
	
	/**
	 * 拷贝向量组
	 * @return
	 */
	public double[] getVectors(){
		double[] tmp = new double[this.size()];
		for(int i=0;i<tmp.length;i++){
			tmp[i] = this.getEntry(i, COLUMN_SELF);
		}
		return tmp;
	}
	
	/**
	 * 两个向量相加.各个维度的数据相加
	 * 注意:自身被修改
	 * @param aVector
	 * @return this+aVector
	 */
	public VectorMath add(VectorMath aVector){
		if(this.size()!=aVector.size()){
			throw new UnsupportedOperationException();
		}
		double[] tmp = aVector.getVectors();
		for(int i=0;i<this.size();i++){
			this.getDataRef()[i][COLUMN_SELF]+= tmp[i];
		}
		return this;
	}
	
	/**
	 * 向量的数值乘法
	 * 注意:要修改自身
	 * @param ad 数值
	 * @return 数值相乘的结果
	 */
	public VectorMath scalarMultiply(double ad){
		for(int i=0;i<this.size();i++){
			this.getDataRef()[i][COLUMN_SELF]*=ad;
		}
		return this;
	}
	
	/**
	 * 自身向量单位化处理
	 * @return
	 */
	public VectorMath toUnitVector(){
		double t = this.lengthMatrix();
		for(int i=0;i<this.size();i++){
			this.getDataRef()[i][COLUMN_SELF]/=t;
		}
		return this;
	}
	
	/**
	 * 获取该向量的单位化向量,但是自身不变化
	 * @return
	 */
	public VectorMath getUnitVector(){
		VectorMath tmp = new VectorMath(this.getVectors());
		tmp.toUnitVector();
		return tmp;
	}
	/**
	 * 提取第i维度数值
	 * @param i 0<=i<p(维度)
	 * @return 数值
	 */
	public double getEntry(int i){
		return this.getDataRef()[i][COLUMN_SELF];
	}
	
	/**
	 * 获取向量的列数,一定是一
	 * @return
	 */
	public int getDimension(){
		return COLUMN;
	}
}
