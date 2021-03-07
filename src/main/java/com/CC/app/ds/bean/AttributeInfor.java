package com.CC.app.ds.bean;

import com.CC.app.ds.DataElementOperation;
import com.CC.app.ds.ValueComputeMethod;
import com.CC.app.ds.normfilter.GainAttribute;

/**
 * 数据属性信息类.
 * 主要用于记录单个属性的数据清洗方法和指标转换方法.
 * 数据清洗方法主要就两类(名义尺度变量和比例尺度变量)数据进行空缺值填充,
 * 指标转换主要是为了方便进行神经网络计算时使用
 * @version 0.0.1

 */
public class AttributeInfor implements Comparable<AttributeInfor> {
	
	/**
	 * 用于计算的相关方法
	 */
	protected ValueComputeMethod computeM;
	
	/**
	 * 信息熵
	 */
	protected double entropy;
	
	/**
	 * 用于数据清洗的结果数据
	 */
	protected double fillValue;
	
	/**
	 * 记录是否已经计算数据清洗的结果数据
	 */
	protected boolean isComputeFillValue = false;
	
	/**
	 * 用于标记变量,建议和数据集一一对应
	 */
	protected int label;
	
	/**
	 * 调用方法时的名称
	 */
	protected String name;
	
	/**
	 * 用于标记某个属性的值是空缺值的特殊数据值
	 */
	protected double NULL_VALUE;

	/**
	 * 初始化类
	 * @param label 所属类别
	 * @param null_value 空值
	 * @param name 调用名称
	 * @param computeM 计算方法
	 */
	public AttributeInfor(int label, double null_value, String name,
			ValueComputeMethod computeM) {
		super();
		this.label = label;
		NULL_VALUE = null_value;
		this.name = name;
		this.computeM = computeM;
	}
	
	/**
	 * 计算信息熵
	 * @param dataArr
	 */
	public void computeEntropy(DataElementOperation[] dataArr){
		int l = dataArr.length;
		double[] valueArr = new double[l];
		double[] groupArr = new double[l];
		for(int i=0;i<l;i++){
			valueArr[i] = dataArr[i].getElementOfValue(this.label);
			groupArr[i] = dataArr[i].belongToGroup();
		}
		GainAttribute g = new GainAttribute(valueArr,groupArr);
		g.initializeBeforeComputeGain();
		g.computeGain();
		this.entropy = g.getGain();
	}
	
	/**
	 * 进行数据清洗
	 * @param dataArr
	 */
	public void fillValueForElement(DataElementOperation[] dataArr){
		if(!this.isComputeFillValue){
			this.fillValue = computeM.initializeFillValueMethod(dataArr,this.NULL_VALUE,this.label);
		}
		for(int i=0;i<dataArr.length;i++){
			if(dataArr[i].getElementOfValue(this.label)  ==this.NULL_VALUE){
				dataArr[i].setElementOfValue(this.label, this.fillValue);
			}
		}
	}
	
	/**
	 * 获取信息熵
	 * @return
	 */
	public double getEntropy(){
		return this.entropy;
	}


	public int getLabel() {
		return label;
	}


	public String getName() {
		return name;
	}


	public double getNULL_VALUE() {
		return NULL_VALUE;
	}


	/**
	 * 转换数据以符合指标体系
	 * @param dataArr
	 */
	public void transferValueForElement(DataElementOperation[] dataArr){
		computeM.initializeTransferMethod(dataArr,this.label);
		for(DataElementOperation d:dataArr){
			d.setElementOfValue(this.label, computeM.valueTransferMethod(d.getElementOfValue(this.label)));
		}
	}
	
	public void computeAll(DataElementOperation[] dataArr){
		this.fillValueForElement(dataArr);
		this.transferValueForElement(dataArr);
		this.computeEntropy(dataArr);
	}

	public int compareTo(AttributeInfor o) {
		double tmp = this.entropy-o.getEntropy();
		if(tmp<0){
			return -1;
		}else if(tmp==0){
			return 0;
		}else {
			return 1;
		}
	}

}

