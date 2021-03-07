package com.CC.app.ds.bean;

import com.CC.app.ds.DataElementOperation;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 银行使用者信息BEAN
 *
 */
public class BankAccountBean implements DataElementOperation {

	/**
	 * 数据集
	 */
	private Map<Integer, Double> map = new HashMap<Integer, Double>();
	
	/**
	 * 所属类别
	 */
	private double group;
	
	/**
	 * 信用度
	 */
	private double rate;
	
	/**
	 * 属于的类别
	 */
	public double belongToGroup() {
		return this.group;
	}

	/**
	 * 获取某一列属性
	 * @param label 属性标签
	 */
	public double getElementOfValue(int label) {
		return map.get(label);
	}

	/**
	 * 设置类别
	 * @param group
	 */
	public void setGroup(double group) {
		this.group = group;
	}

	/**
	 * 设置某个值
	 * @param label 属性标签
	 * @param value 属性值
	 */
	public void setElementOfValue(int label, double value) {
		map.put(label, value);
	}
	
	/**
	 * 属性标签迭代
	 * @return
	 */
	public Iterator<Integer> labelIterator(){
		return map.keySet().iterator();
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}
}

