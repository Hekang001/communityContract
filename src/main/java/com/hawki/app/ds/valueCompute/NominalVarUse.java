package com.hawki.app.ds.valueCompute;

/**
 * @Author 何慷
 * @create 2021/3/4 上午10:45
 */

import com.hawki.app.ds.DataElementOperation;
import com.hawki.app.ds.ValueComputeMethod;

/**
 * 利用众数进行数据填充. 指标直接引用,不用转换.
 */
public class NominalVarUse implements ValueComputeMethod {

    private double fillValue;

    public NominalVarUse() {
        this.fillValue = 0.0;
    }

    public double initializeFillValueMethod(DataElementOperation[] dataArr,
                                            double NULL_VALUE, int label) {
        int tot = 0;
        for (DataElementOperation d : dataArr) {
            if (d.getElementOfValue(label) != NULL_VALUE) {
                tot++;
            }
        }
        double[] valueList = new double[tot];
        int i = 0;
        for (DataElementOperation d : dataArr) {
            if (d.getElementOfValue(label) != NULL_VALUE) {
                valueList[i++] = d.getElementOfValue(label);
            }
        }
        this.fillValue = FillValueUtil.getModeValue(valueList);
        return this.fillValue;
    }

    public void initializeTransferMethod(DataElementOperation[] dataArr,
                                         int label) {
    }

    public double valueTransferMethod(double value) {
        return value;
    }

}
