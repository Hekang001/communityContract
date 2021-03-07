package com.CC.app.ds.valueCompute;

import com.CC.app.ds.DataElementOperation;
import com.CC.app.ds.ValueComputeMethod;

/**
 * @Author 何慷
 * @create 2021/3/4 上午10:43
 */

/**
 * 利用平均数进行数据填充.
 * 利用正态分布进行指标变换.参数:a = 3, b = 9
 *
 */
public class RatioScaledNormalD3_9 implements ValueComputeMethod {

	private double fillValue ;
	
	private double a,b;
	
	public RatioScaledNormalD3_9(){
		this.fillValue = 0.0;
		a = 3;
		b = 9;
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
	}

	public double valueTransferMethod(double value) {
		return getNormDisribution(value);
	}


	/**
	 * exp(-(x-b)^2/(2a^2))/sqrt(2*PI*a).
	 * a = 6, b= 37
	 * @param x
	 * @return
	 */
	private double getNormDisribution(double x){
		return Math.exp((-( (x-b)*(x-b)/(2.0*a*a))))/ Math.sqrt(2* Math.PI*a);
	}

}

