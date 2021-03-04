package com.hawki.app.ds.valueCompute;

import java.util.Arrays;

/**
 * @Author 何慷
 * @create 2021/3/4
 */

/**
 * 数据清洗工具包
 */
public class FillValueUtil {

	/**
	 * 求数组数据的平均数
	 * 
	 * @param valueList
	 *            数据集
	 * @return
	 */
	public static double getMeanValue(double[] valueList) {
		double tmp = 0.0;
		for (double d : valueList) {
			tmp += d;
		}
		return tmp / valueList.length;
	}

	/**
	 * 求数组数据的众数
	 *
	 * @return
	 */
	public static double getModeValue(double[] array) {
		Arrays.sort(array);
		int count = 1;
		int longest = 0;
		double mode = 0;
		for (int i = 0; i < array.length - 1; i++) {
			if (array[i] == array[i + 1]) {
				count++;
				if (count > longest) {
					mode = array[i];
					longest = count;
				} else
					count = 1;
			}
		}
		return mode;
	}

}
