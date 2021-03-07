package com.CC.app.ds.valueCompute;

/**
 * @Author 何慷
 * @create 2021/3/4 上午10:50
 */

import com.CC.app.ds.DataElementOperation;
import com.CC.app.ds.ValueComputeMethod;

/**
 * 利用平均数进行数据填充.
 * 利用 (v-min(V))/(max(V)-min(V)) 进行指标变换
 *
 */
public class RatioScaledVar implements ValueComputeMethod {
			 
	private double fillValue ;
	private double min;
	private double max;
	
	
	public RatioScaledVar(){
		fillValue = 0.0;
	}
	
	public double initializeFillValueMethod(DataElementOperation[] dataArr,
											double NULL_VALUE, int label) {
		int tot=  0;
		for(DataElementOperation d:dataArr){
			if(d.getElementOfValue(label)!=NULL_VALUE){
				tot++;
			}
		}
		double[] valueList = new double[tot];
		int i = 0;
		for(DataElementOperation d:dataArr){
			if(d.getElementOfValue(label)!=NULL_VALUE){
				valueList[i++] = d.getElementOfValue(label);
			}
		}
		this.fillValue = FillValueUtil.getMeanValue(valueList);
		return this.fillValue;
	}

	public void initializeTransferMethod(DataElementOperation[] dataArr,
			int label) {
		int i=0;
		min = dataArr[i].getElementOfValue(label);
		max = min;
		while(++i<dataArr.length){
			min = Math.min(min, dataArr[i].getElementOfValue(label));
			max = Math.max(max, dataArr[i].getElementOfValue(label));
		}
		
	}

	public double valueTransferMethod(double value) {
		return (value-min)/(max-min);
	}

}

