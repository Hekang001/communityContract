package com.CC.app.ds.normfilter;

/**
 * @Author 何慷
 * @create 2021/3/4 上午10:32
 */
/**
 * 信息增益方式计算变量的熵
 *
 */
public class GainAttribute implements Comparable<GainAttribute>{

    private AttributeListInfo<Double, Double> list;
    private int[] sc;
    private int[] sa;
    private int[][] sac;
    private double[] pc;
    private double[][] pac;
    private double is;
    private double[] isj;
    private double e;
    private double gain;
    private boolean isCompute = false;

    public GainAttribute (){
        list = new AttributeListInfo<Double, Double>();
    }

    /**
     * 信息熵计算初始化.两个数组是一一对应关系
     * @param valueArr 信息值
     * @param groupArr 信息属于的类别
     */
    public GainAttribute(double[] valueArr, double[] groupArr){
        list = new AttributeListInfo<Double, Double>();
        for(int i=0;i<valueArr.length;i++){
            AttributeInfo<Double, Double> a = new AttributeInfo<Double, Double>();
            a.setGroup(groupArr[i]);
            a.setValue(valueArr[i]);
            list.add(a);
        }
    }

    /**
     * 计算熵值
     */
    public void computeGain(){
        if(list==null||list.attrListLength()==0){
            throw new UnsupportedOperationException();
        }else{
            isCompute = true;
            computeBasicValues();
            computeIS();
            computeISJ();
            computeE();
            this.gain = is - e;
        }
    }

    /**
     * 计算样本中属于各个类别的样本数目
     */
    private void computeSC(){
        sc = new int[list.groupLength()];
        for(AttributeInfo<Double, Double> a: list.getAttrList()){
            sc[a.belongToGroup()]++;
        }
    }

    private void computeSA(){
        sa = new int[list.attrListLength()];
        for(AttributeInfo<Double, Double> a: list.getAttrList()){
            sa[a.belongToValue()]++;
        }
    }

    private void computeSAC(){
        sac = new int[list.valueLength()][list.groupLength()];
        for(AttributeInfo<Double, Double> a: list.getAttrList()){
            sac[a.belongToValue()][a.belongToGroup()]++;
        }
    }

    private void computePC(){
        pc = new double[list.groupLength()];
        int s = list.attrListLength();
        for(int i=0;i<pc.length;i++){
            pc[i] = ((double)sc[i])/((double)s);
        }
    }

    private void computePAC(){
        pac = new double[list.valueLength()][list.groupLength()];
        for(int i=0;i<list.valueLength();i++){
            for(int j=0;j<list.groupLength();j++){
                pac[i][j] = ((double)sac[i][j])/((double)sa[i]);
            }
        }
    }

    private void computeBasicValues(){
        computeSC();
        computeSA();
        computeSAC();
        computePC();
        computePAC();
    }

    private void computeIS(){
        is = 0.0;
        for(int i=0;i<pc.length;i++){
            is+=funLog(pc[i]);
        }
        is=-is;
    }

    private void computeISJ(){
        isj = new double[list.valueLength()];
        for(int j=0;j<isj.length;j++){
            for(int i=0;i<list.groupLength();i++){
                isj[j]+=funLog(pac[j][i]);
            }
            isj[j]=-isj[j];
        }
    }

    private void computeE(){
        e = 0.0;
        int s = list.attrListLength();
        for(int j=0;j<list.valueLength();j++){
            e+=sa[j]/s*isj[j];
        }
    }

    private double funLog(double e){
        if(e==0.0){
            return 0;
        }
        return e*Math.log(e)/Math.log(2);
    }

    /**
     * 获取熵值
     * @return
     */
    public double getGain(){
        if(!isCompute){
            computeGain();
        }
        return this.gain;
    }

    public int compareTo(GainAttribute o) {
        double tmp = this.getGain()-o.getGain();
        if(tmp<0.0){
            return -11;
        }else if(tmp>0.0){
            return 1;
        }
        return 0;
    }

    public void initializeBeforeComputeGain(){
        list.finishAdd();
    }
}
