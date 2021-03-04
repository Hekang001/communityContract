package com.hawki.app.ds.math.maxlikelihood;

/**
 * Logic 模型数据集合
 *
 */

public class LogicElement {
	/**
	 * 输入数据
	 */
	private double[] input;
	/**
	 * 输出数据
	 */
	private double output;
	
	
	public LogicElement(double[] input, double output){
		this.input = input;
		this.output = output;
	}
	
	public double getInputByDim(int index){
		return input[index];
	}
	
	public double getOutput(){
		return this.output;
	}
	
}
