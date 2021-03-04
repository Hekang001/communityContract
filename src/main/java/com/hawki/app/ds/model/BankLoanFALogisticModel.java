package com.hawki.app.ds.model;

import com.hawki.app.ds.DataElementOperation;
import com.hawki.app.ds.bean.AttributeInfor;
import com.hawki.app.ds.bean.BankLoanBeanDB;
import com.hawki.app.ds.math.martix.BasicMatrix;
import com.hawki.app.ds.math.martix.VectorMath;
import com.hawki.app.ds.math.martix.VectorMathUtil;
import com.hawki.app.ds.math.maxlikelihood.BasicLogicFuction;
import com.hawki.app.ds.math.maxlikelihood.LogicElement;
import org.apache.commons.math.linear.MatrixUtils;
import org.apache.commons.math.linear.RealMatrix;
import org.apache.commons.math.linear.RealMatrixImpl;

import java.util.List;

/**
 * 进行因子分析和logic分析的模型
 *
 */
public class BankLoanFALogisticModel {
	
	/**
	 * 基本数据集
	 */
	private BankLoanBeanDB datasBean;
	
	/**
	 * 输入数据的映射关系组,主要记录提取的部分
	 */
	private List<AttributeInfor> dataAttributeInforsList;
	
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
	
	/**
	 * 构造一个基础的FA logic分析模型
	 * @param datasBean
	 */
	public BankLoanFALogisticModel(BankLoanBeanDB datasBean) {
		super();
		this.datasBean = datasBean;
		this.dataAttributeInforsList = this.datasBean.getAttrInfoList();
	}

	/**
	 * 因子分析
	 */
	public void factorAnalyzer(){
		//构造基本样本向量集合
		RealMatrix[] realMatrixX = new RealMatrixImpl[this.datasBean.datasLength()];
		DataElementOperation[] bankDatas = this.datasBean.getDatas();
		for(int i=0;i<realMatrixX.length;i++){
			double[] tmp = new double[dataAttributeInforsList.size()];
			for(int j=0;j<dataAttributeInforsList.size();j++){
				tmp[j] = bankDatas[i].getElementOfValue(dataAttributeInforsList.get(j).getLabel());
			}
			realMatrixX[i] = MatrixUtils.createColumnRealMatrix(tmp);
		}
		//构造平均值向量
		RealMatrix realMatrixXMean;
		int i = 0;
		realMatrixXMean = realMatrixX[i];
		while(++i<realMatrixX.length){
			realMatrixXMean = realMatrixXMean.add(realMatrixX[i]);
		}
		//平均值向量取负
		realMatrixXMean = realMatrixXMean.scalarMultiply(-1.0/((double)i));
		i = 0;
		RealMatrix tmp = realMatrixX[i].add(realMatrixXMean);
		tmp = tmp.multiply(tmp.transpose());
		//协方差矩阵
		RealMatrix S =  tmp;
		while(++i<realMatrixX.length){
			tmp = realMatrixX[i].add(realMatrixXMean);
			tmp = tmp.multiply(tmp.transpose());
			S = S.add(tmp);
		}
		S = S.scalarMultiply(1.0/((double)i-1));
		BasicMatrix matrix = new BasicMatrix(S.getData());
		matrix.calCharacterValuesAndVectors();
		
		M = matrix.getMContributionNumber();
		//TODO 测试用M
		M = 2;
		//获取协方差矩阵S的特征向量和特征值
		VectorMath[] vectors = new VectorMath[M];
		double[] cValues = new double[M];
		for(i=0;i<M;i++){
			vectors[i] = new VectorMath(matrix.getCharacterVector(i));
			cValues[i] = matrix.getCharacterValue(i);
		}
		//特征向量取单位正交
		//正交化
		vectors = VectorMathUtil.orthogonalizateVectors(vectors);
		//单位化
		vectors = VectorMathUtil.unitVectors(vectors);
		for(i=0;i<vectors.length;i++){
			//在这里没有取sqrt
			vectors[i] = vectors[i].scalarMultiply((cValues[i]));
		}
		//构造因子载荷矩阵
		double[][] Ad = new double[S.getColumnDimension()][M];
		for(i=0;i<Ad.length;i++){
			for(int j=0;j<Ad[i].length;j++){
				Ad[i][j] = vectors[j].getEntry(i);
			}
		}
		//参数估计后的因子模型结果(因子载荷矩阵)
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
		DataElementOperation[] bankDatas = this.datasBean.getDatas();
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
	
	/**
	 * 获取最终结果
	 * @return
	 */
	public String getResult(){
		StringBuffer s = new StringBuffer("("+this.results[0]+")");
		int i;
		for(i=1;i<results.length;i++){
			s.append("+("+results[i]+"*"+dataAttributeInforsList.get(i-1).getName()+")");
		}
		return s.toString();
	}	
	
}

