package com.hawki.app.ds;


/**
 * 一条数据集合.
 * 其中包含一条有效记录的所有数据项.
 *
 */
public interface DataElementOperation {

	/**
	 * 在有效记录中提取一个数据项
	 * @param label 一般与属性信息中的label相互对应
	 * @return
	 */
	double getElementOfValue(int label);
	
	/**
	 * 在有效记录中设定一个数据项的值
	 * @param label 一般与属性信息中的label相互对应
	 * @param value 新值
	 */
	void setElementOfValue(int label, double value);
	
	/**
	 * 获取所属的类别
	 * @return
	 */
	double belongToGroup();
	
}

