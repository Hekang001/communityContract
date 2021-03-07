package com.CC.app.ds.model;

import com.CC.app.ds.math.martix.VectorMath;
import com.CC.app.ds.math.martix.VectorMathUtil;
import com.CC.app.ds.neuralnet.BasicEBP;
import com.CC.app.ds.bean.AttributeInfor;
import com.CC.app.ds.bean.BankAccountBean;
import com.CC.app.ds.bean.BankAccountBeanDB;
import com.CC.app.ds.math.martix.BasicMatrix;
import com.CC.app.ds.math.maxlikelihood.BasicLogicFuction;
import com.CC.app.ds.math.maxlikelihood.LogicElement;
import org.apache.commons.math.linear.MatrixUtils;
import org.apache.commons.math.linear.RealMatrix;
import org.apache.commons.math.linear.RealMatrixImpl;

import java.util.List;

/**
 * 通过神经网络,因子分析和logistic建立模型对象
 *
 */
public class CreateNNFALogistic {

	/**
	 * 基本数据集
	 */
	private BankAccountBeanDB datasBean;
	
	/**
	 * 神经网络计算结果集
	 */
	private double[] rateArr;

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
	 * 神经网络学习最长时间
	 */
	private long useTime;
	
	/**
	 * 因子得分矩阵
	 */
	private RealMatrix valueMatrix;
	
	/**
	 * 因子得分常数矩阵
	 */
	private RealMatrix constValueM;
	
	/**
	 * 用于记录参数计算中的跨越值
	 */
	private int M;
	
	/**
	 * 用于logistic计算的数据向量集合
	 */
	private VectorMath[] valueVectors;
	
	/**
	 * logistic 计算结果值
	 */
	private double[] results;
	
	public CreateNNFALogistic(){
		datasBean = new BankAccountBeanDB();
	}
	
	public CreateNNFALogistic(BankAccountBeanDB datasBean){
		this.datasBean = datasBean;
		rateArr = new double[this.datasBean.datasLength()];
	}
	
	/**
	 * 神经网络计算
	 * @param nnInput 神经网络输入层数目
	 * @param nnHidden 神经网络隐藏层数目
	 * @param time 神经网络计算时间
	 */
	public void computeNeuralNet(int nnInput,int nnHidden,long time){
		this.nnInputNode = nnInput;
		this.nnHiddenNode = nnHidden;
		this.useTime  = time;
		if(!this.datasBean.canUseAllInfo()){
			throw new UnsupportedOperationException("请初始化数据Bean的各项设置.");
		}
		this.computeNeuralNet();
	}
	
	/**
	 * 神经网络计算
	 * @param nnInput 神经网络输入层数目
	 * @param nnHidden 神经网络隐藏层数目
	 */
	public void computeNeuralNet(int nnInput,int nnHidden){
		this.nnInputNode = nnInput;
		this.nnHiddenNode = nnHidden;
		this.useTime  = Long.MAX_VALUE;
		if(!this.datasBean.canUseAllInfo()){
			throw new UnsupportedOperationException("请初始化数据Bean的各项设置.");
		}
		this.computeNeuralNet();
	}
	
	/**
	 * 进行神经网络学习
	 */
	private void computeNeuralNet(){
		BasicEBP ebp = new BasicEBP(this.nnInputNode,this.nnHiddenNode);
		//学习参数自学习能力控制
		ebp.toOpenSelfSuitLR();
		dataAttributeInforsList = this.datasBean.getSubAttributeInforsOrderByEntropy(this.nnInputNode);
		double[][] inputData = new double[dataAttributeInforsList.size()][];
		BankAccountBean[] bankDatas = this.datasBean.getDatas();
		double[] resultData = new double[bankDatas.length];
		for(int i=0;i<inputData.length;i++){
			double[] tmp = new double[this.datasBean.datasLength()];
			for(int j=0;j<tmp.length;j++){
				tmp[j] = bankDatas[j].getElementOfValue(dataAttributeInforsList.get(i).getLabel());
			}
			inputData[i] = tmp;
		}
		for(int i=0;i<resultData.length;i++){
			resultData[i] = bankDatas[i].getRate();
		}
		ebp.study(inputData, resultData,this.useTime);
		this.rateArr =  ebp.work(inputData);
	}
	
	/**
	 * 因子分析
	 */
	public void factorAnalyzer(){
		//构造基本样本向量集合
		RealMatrix[] realMatrixX = new RealMatrixImpl[this.datasBean.datasLength()];
		BankAccountBean[] bankDatas = this.datasBean.getDatas();
		for(int i=0;i<realMatrixX.length;i++){
			double[] tmp = new double[this.nnInputNode+1];
			for(int j=0;j<this.nnInputNode;j++){
				tmp[j] = bankDatas[i].getElementOfValue(dataAttributeInforsList.get(j).getLabel());
			}
			tmp[this.nnInputNode] = this.rateArr[i];
			realMatrixX[i] = MatrixUtils.createColumnRealMatrix(tmp);
		}
		//构造平均值向量
		RealMatrix realMatrixXMean;
		int i = 0;
		realMatrixXMean = realMatrixX[i];
		while(++i<realMatrixX.length){
			realMatrixXMean = realMatrixXMean.add(realMatrixX[i]);
		}
		realMatrixXMean = realMatrixXMean.scalarMultiply(-1.0/((double)i));
		i = 0;
		RealMatrix tmp = realMatrixX[i].add(realMatrixXMean);
		tmp = tmp.multiply(tmp.transpose());
		RealMatrix S =  tmp;
		while(++i<realMatrixX.length){
			tmp = realMatrixX[i].add(realMatrixXMean);
			tmp = tmp.multiply(tmp.transpose());
			S.add(tmp);
		}
		S = S.scalarMultiply(1.0/((double)i-1));
		BasicMatrix matrix = new BasicMatrix(S.getData());
		matrix.calCharacterValuesAndVectors();
		M = matrix.getMContributionNumber();
		VectorMath[] vectors = new VectorMath[M];
		double[] cValues = new double[M];
		for(i=0;i<M;i++){
			vectors[i] = new VectorMath(matrix.getCharacterVector(i));
			cValues[i] = matrix.getCharacterValue(i);
		}
		vectors = VectorMathUtil.orthogonalizateVectors(vectors);
		for(i=0;i<vectors.length;i++){
			vectors[i].scalarMultiply(Math.sqrt(cValues[i]));
		}
		double[][] Ad = new double[S.getColumnDimension()][M];
		for(i=0;i<Ad.length;i++){
			for(int j=0;j<Ad[i].length;j++){
				Ad[i][j] = vectors[j].getEntry(i);
			}
		}
		//参数估计后的因子模型结果
		BasicMatrix  A= new BasicMatrix(Ad);
		//获取因子模型的共性方差
		VectorMath h2 = VectorMathUtil.getCommonVarianceVector(A);
		//因子旋转
		A = VectorMathUtil.toRotateMatrix(A, h2);
		h2 = VectorMathUtil.getCommonVarianceVector(A);
		double[] d = h2.getVectors();
		for(i=0;i<d.length;i++){
			d[i] = S.getEntry(i, i)-d[i];
			if(d[i]==0.0){
				d[i] = 1E-20;
			}
		}
		double[][] data = new double[d.length][d.length];
		for(i=0;i<d.length;i++){
			data[i][i] =d[i];
			
		}
		//因子得分矩阵
		//特殊方差矩阵的逆矩阵
		RealMatrix DI= MatrixUtils.createRealMatrix(data).inverse();
		this.valueMatrix = A.transpose().multiply(DI).multiply(A).inverse().multiply(A.transpose()).multiply(DI);
		this.constValueM = this.valueMatrix.multiply(realMatrixXMean);
		
		//计算已有状况的数据集合
		this.valueVectors = new VectorMath[realMatrixX.length];
		for(i=0;i<this.valueVectors.length;i++){
			this.valueVectors[i] = new VectorMath(this.valueMatrix.multiply(realMatrixX[i].add(realMatrixXMean)).getData());
		}
	}
	
	/**
	 * logistic计算
	 */
	public void logisticCompute(){
		BasicLogicFuction function = new BasicLogicFuction(this.M);
		BankAccountBean[] bankDatas = this.datasBean.getDatas();
		int i = 0;
		for(VectorMath v:this.valueVectors){
			LogicElement e = new LogicElement(v.getVectors(),bankDatas[i].belongToGroup());
			function.add(e);
			i++;
		}
		try {
			this.results = function.getResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		double[] tmp = new double[results.length-1];
		for(i=0;i<tmp.length;i++){
			tmp[i]=this.results[i+1];
		}
		double tt = results[0]+MatrixUtils.createRowRealMatrix(tmp).multiply(this.constValueM).getDeterminant();
		tmp = (new VectorMath(MatrixUtils.createRowRealMatrix(tmp).multiply(this.valueMatrix).transpose().getData())).getVectors();
		this.results = new double[tmp.length+1];
		this.results[0]=tt;//+=MatrixUtils.createRowRealMatrix(tmp).multiply(this.constValueM).getDeterminant();
		for(i=0;i<tmp.length;i++){
			this.results[i+1] = tmp[i];
		}
	}
	public String getResult(){
		StringBuffer s = new StringBuffer("("+this.results[0]+")");
		int i;
		for(i=1;i<results.length-1;i++){
			s.append("+("+results[i]+"*"+dataAttributeInforsList.get(i-1).getName()+")");
		}
		s.append("+("+results[i]+"*"+datasBean.getRate()+")");
		return s.toString();
	}
}

