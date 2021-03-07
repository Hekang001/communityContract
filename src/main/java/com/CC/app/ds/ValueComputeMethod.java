package com.CC.app.ds;

/**
 * 进行数据清洗和数据指标创建的计算方法.
 * 数据清洗主要是针对数据库中的一些数据的某些值是空缺值,为了方便计算和合理的分析需要采用一些方法对数据进行补充.
 * 在这里我们使用的是根据属性的特点分别采用众数法和平均值法.
 * 数据指标体系的创建主要是为了在进行神经网络学习计算的时候,构造合理的输入输出值.
 * @version 0.0.2 补充的一些注释
 * @version 0.0.1
 */
public interface ValueComputeMethod {
	
	/**
	 * 初始化数据清洗方法
	 * @param dataArr 数据集
	 * @param NULL_VALUE 空缺值 
	 * @param label 标号
	 * @return
	 */
	double initializeFillValueMethod(DataElementOperation[] dataArr, double NULL_VALUE, int label);
	
	/**
	 * 建立指标体系时用的数据值转化方法
	 * @param value 原始值
	 * @return 转化值
	 */
	double valueTransferMethod(double value);
	
	/**
	 * 初始化指标体系计算方法
	 * @param dataArr 数据集
	 * @param label 标号
	 */
	void initializeTransferMethod(DataElementOperation[] dataArr, int label);

}

