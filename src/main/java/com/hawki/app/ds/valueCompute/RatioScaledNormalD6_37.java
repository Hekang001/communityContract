package com.hawki.app.ds.valueCompute;

import com.hawki.app.ds.DataElementOperation;
import com.hawki.app.ds.ValueComputeMethod;
/**
 * @Author 何慷
 * @create 2021/3/4 上午10:48
 */
/**
 * 利用平均数进行数据填充.
 * 利用正态分布进行指标变换.参数:a = 6, b = 37
 *
 */
public class RatioScaledNormalD6_37 implements ValueComputeMethod {

	private double fillValue ;
	
	private double a,b;
	
	public RatioScaledNormalD6_37(){
		this.fillValue = 0.0;
		a = 6;
		b = 37;
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

