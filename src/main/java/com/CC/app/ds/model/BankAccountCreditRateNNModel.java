package com.CC.app.ds.model;

import com.CC.app.ds.neuralnet.BasicEBP;
import com.CC.app.ds.bean.AttributeInfor;
import com.CC.app.ds.bean.BankAccountBean;
import com.CC.app.ds.bean.BankAccountBeanDB;

import java.util.List;

/**
 * @Author 何慷
 * @create 2021/3/4
 */

/**
 * 采用神经网络的方法对个人用户的个人信用进行评估.
 * 采用信息熵的方法进行指标的筛选.
 * @version 0.0.1
 */
public class BankAccountCreditRateNNModel {

	/**
	 * 基本数据集
	 */
	private BankAccountBeanDB datasBean;
	
	/**
	 * 神经网络输入端点数
	 */
	private int nnInputNode;
	
	/**
	 * 神经网络隐藏层端点数目
	 */
	private int nnHiddenNode;
	
	/**
	 * 输入数据的映射关系组,主要记录提取的部分
	 */
	private List<AttributeInfor> dataAttributeInforsList;
	
	/**
	 * 单组数据集神经网络学习最长时间
	 */
	private long useTime;
	
	private int iteArr;
	
	/**
	 * 记录是否本网络是否已开始学习.
	 */
	private boolean isItselfStudy;
	
	/**
	 * 基本神经网络结构
	 */
	private BasicEBP ebp;
	
	/**
	 * 基本初始化方法,不推荐使用.
	 */
	public BankAccountCreditRateNNModel() {
		super();
		initialize();
	}
	/**
	 * 构造神经网络分析模型.推荐使用.
	 * @param datasBean
	 */
	public BankAccountCreditRateNNModel(BankAccountBeanDB datasBean) {
		super();
		this.datasBean = datasBean;
		initialize();
	}
	
	private void initialize(){
		this.isItselfStudy = false;
	}
	
	/**
	 * 神经网络学习
	 * @param nnInput 神经网络输入层数目
	 * @param nnHidden 神经网络隐藏层数目
	 * @param time 神经网络学习时间
	 */
	public void itselfStudy(int nnInput,int nnHidden,long time){
		this.nnInputNode = nnInput;
		this.nnHiddenNode = nnHidden;
		this.useTime  = time;
		if(!this.datasBean.canUseAllInfo()){
			throw new UnsupportedOperationException("请初始化数据Bean的各项设置.");
		}
		this.itselfStudy();
	}
	
	/**
	 * 神经网络学习.不限定时间.
	 * @param nnInput 神经网络输入层数目
	 * @param nnHidden 神经网络隐藏层数目
	 */
	public void itselfStudy(int nnInput,int nnHidden){
		this.useTime  = Long.MAX_VALUE;
		this.itselfStudy(nnInput, nnHidden, this.useTime);
	}
	
	/**
	 * 返回数据集合
	 * @return
	 */
	public BankAccountBeanDB getDatasBean() {
		return datasBean;
	}
	/**
	 * 进行神经网络学习
	 */
	private void itselfStudy(){
		//分组用数据初始化
		this.iteArr = 0;
		ebp = new BasicEBP(this.nnInputNode,this.nnHiddenNode);
		//学习参数自学习能力控制
		ebp.toOpenSelfSuitLR();
		dataAttributeInforsList = this.datasBean.getSubAttributeInforsOrderByEntropy(this.nnInputNode);
		//从测试中我发现一组数据数据量过大会影响整体效果.进行分组式输入.
		while(this.iteArr*BasicEBP.MODEL_ARR<this.datasBean.datasLength()){
			ebp.study(createInputData(), createResultData(),this.useTime);
			this.iteArr++;
		}
		//标识本数据集已经经过神经网络学习.
		isItselfStudy = true;
	}
	
	/**
	 * 神经网络处理输入数据集合
	 * @param datas 输入数据
	 * @return 结果集合,和输入数据集合顺序一一对应.
	 */
	public double[] neuralNetCompute(BankAccountBeanDB datas){
		if(!isItselfStudy){
			throw new UnsupportedOperationException("请让神经网络进行学习");
		}
		return ebp.work(createInputData(datas,false));
	}
	
	/**
	 * 用对象数据构造神经网络输入数据集合
	 * @return
	 */
	private double[][] createInputData(){
		return createInputData(this.datasBean,true);
	}
	
	/**
	 * 用参数数据构造神经网络输入数据集
	 * @param datasBean 参数数据集合
	 * @param isForStudy 是否用于学习时数据输入.为了更好使用神经网络,在这里采用了分段式的
	 * 数据输入,以减小神经网络的学习误差.
	 * @return
	 */
	private double[][] createInputData(BankAccountBeanDB datasBean, boolean isForStudy){
		double[][] inputData = new double[dataAttributeInforsList.size()][];
		int ite = this.iteArr;
		int b = isForStudy?ite*ebp.MODEL_ARR:0;
		int e = isForStudy? Math.min((ite+1)*ebp.MODEL_ARR, datasBean.datasLength()):datasBean.datasLength();
		BankAccountBean[] bankDatas = datasBean.getSubDatas(b, e);
		for(int i=0;i<inputData.length;i++){
			double[] tmp = new double[bankDatas.length];
			for(int j=0;j<tmp.length;j++){
				tmp[j] = bankDatas[j].getElementOfValue(dataAttributeInforsList.get(i).getLabel());
			}
			inputData[i] = tmp;
		}
		return inputData;
	}
	
	/**
	 * 用对象数据构造输出数据集
	 * @return
	 */
	private double[] createResultData(){
		return createResultData(this.datasBean);
	}
	
	/**
	 * 用参数数据构造输出数据集
	 * @param datasBean 参数数据
	 * @return
	 */
	private double[] createResultData(BankAccountBeanDB datasBean){
		int ite = this.iteArr;
		BankAccountBean[] bankDatas = datasBean.getSubDatas(ite*ebp.MODEL_ARR,
				Math.min((ite+1)*ebp.MODEL_ARR, datasBean.datasLength()));
		double[] resultData = new double[bankDatas.length];
		for(int i=0;i<resultData.length;i++){
			resultData[i] = bankDatas[i].getRate();
		}
		return resultData;
	}
	
}

