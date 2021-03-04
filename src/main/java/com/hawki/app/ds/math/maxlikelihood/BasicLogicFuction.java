package com.hawki.app.ds.math.maxlikelihood;

import org.apache.commons.math.ConvergenceException;
import org.apache.commons.math.linear.MatrixUtils;
import org.apache.commons.math.linear.RealMatrix;
import org.apache.commons.math.linear.RealMatrixImpl;
import org.apache.commons.math.random.RandomVectorGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * @Author 何慷
 * @create 2021/3/3
 */

/**
 * 基本的logic模型算法
 *
 */
public class BasicLogicFuction {

	/**
	 * 输入数据集合
	 */
	private List<LogicElement> elementsList;
	
	/**
	 * 输入数据维度
	 */
	private int dim;
	
	/**
	 * 结果集
	 */
	private double[] results;
	
	/**
	 * 最小二乘法的最小消耗
	 */
	private double minCost;
	
	/**
	 * 随机构造输入数据组组数
	 */
	private int vectorSize;
	
	/**
	 * 消耗阈值
	 */
	private double DEFAULT_COST;	
	
	/**
	 * 构造
	 * @param d 数据维度
	 */
	public BasicLogicFuction(int d){
		this.dim = d;
		elementsList = new ArrayList<LogicElement>();
		vectorSize = 10000;
		DEFAULT_COST = 0.0001;
		minCost = Double.NaN;
	}
	
	/**
	 * 添加新的数据组
	 * @param e 数据组
	 */
	public void add(LogicElement e){
		elementsList.add(e);
	}
	
	/**
	 * 获取拟合结果
	 * @return 多项式前的系数
	 * @throws ConvergenceException
	 */
	public double[] getResult() throws ConvergenceException{
		//computeWithAPI();
		computeWithLSMLogicFuction();
		return this.results;
	}
	
	/**
	 * 通过已有数据组构造输入数据集
	 * @return 输入数据集
	 */
	private double[][] createInputDM(){
		double[][] dm = new double[elementsList.size()][dim+1];
		for(int i=0;i<dm.length;i++){
			dm[i][0] = 1;
			LogicElement e = elementsList.get(i);
			for(int j=1;j<dim+1;j++){
				dm[i][j] = e.getInputByDim(j-1);
			}
		}
		return dm;
	}
	
	/**
	 * 通过已有数据组构造输入数据的期望输出集合
	 * @return 期望输出集合
	 */
	private double[] createInputRes(){
		double[] res = new double[elementsList.size()];
		for(int i=0;i<res.length;i++){
			LogicElement e = elementsList.get(i);
			res[i] = e.getOutput();
		}
		return res;
	}
	
	/**
	 * 利用最小二乘法计算
	 */
	private void computeWithLSMLogicFuction(){
		double[][] input = createInputDM();
		double[] res = createInputRes();
		this.results = LSMLogicFuction.computeLSM(this.dim, input, res);
	}
	
	/**
	 * 通过Api计算
	 * @throws ConvergenceException
	 */
	private void computeWithAPI() throws  ConvergenceException{
		double[][] dm = createInputDM();
		double[] res = createInputRes();
		RealMatrixImpl matrixInput = new RealMatrixImpl(dm);
		RealMatrix matrixOutput = MatrixUtils.createColumnRealMatrix(res);
		//CostFunction cf = new LSMCostFuction(matrixInput,matrixOutput);
		RandomVectorGenerator generator = new LSMRVGenerator(matrixInput,matrixOutput,dim+1);
		results = generator.nextVector();
		//minCost = cf.cost(results);
		double[][] rVectors = new double[vectorSize][];
		boolean f = false;
		for(int i=0;i<vectorSize;i++){
			double[] tmp = generator.nextVector();
			rVectors[i] = tmp;
			//double tmpCost= cf.cost(tmp);
//			if(tmpCost<minCost){
//				minCost = tmpCost;
//				results = tmp;
//			}
			if(minCost<DEFAULT_COST){
				f = true;
				break;
			}
		}
		if(!f){
			double[] tmp = new double[results.length];
			double[] tmpV = new double[vectorSize];
			for(int i=0;i<tmp.length;i++){
				for(int j=0;j<tmpV.length;j++){
					tmpV[j] = rVectors[j][i];
				}
				Arrays.sort(tmpV);
				tmp[i] = tmpV[vectorSize>>1];
			}
//			if(cf.cost(tmp)<minCost){
//				results = tmp;
//			}
		}
	}
	
	
	/**
	 * 返回默认最优化最阈值
	 * @return
	 */
	public double getDEFAULT_COST() {
		return DEFAULT_COST;
	}

	/**
	 * 设定最优化阈值
	 * @param default_cost 阈值
	 */
	public void setDEFAULT_COST(double default_cost) {
		DEFAULT_COST = default_cost;
	}

	/**
	 * 获取最优化方程的代价
	 * @return
	 */
	public double getMinCost() {
		return minCost;
	}

	/**
	 * 获取随机生成的输入数据组组数
	 * @return
	 */
	public int getVectorSize() {
		return vectorSize;
	}

	/**
	 * 获取输入数据的要求维度
	 * @return
	 */
	public int getDim(){
		return this.dim;
	}

}
