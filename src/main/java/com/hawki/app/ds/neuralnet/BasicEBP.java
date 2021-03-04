package com.hawki.app.ds.neuralnet;

import java.util.Random;

/**
 * 神经网络-误差反向传播学习算法 输出层暂时只有一个输出节点. 并对基本的EBP算法做了一些改进:集中权值调整,可调节自适应学习参数.
 *
 * @version 1
 * @version 2 增加了可调节自适应学习参数功能,另外增加了学习参数的可变范围.
 */
public class BasicEBP {

	/**
	 * 前一次平方误差初始值
	 */
	public static final double DEFAULT_INIT_E_LAST = -1;

	/**
	 * 学习参数默认值
	 */
	public static final double DEFAULT_LR = 0.75;

	/**
	 * 学习参数最大值
	 */
	public static final double MAX_LR = 3.0;

	/**
	 * 学习参数最小值
	 */
	public static final double MIN_LR = 0.5;
	
	public static final int MODEL_ARR = 10;

	/**
	 * 随机数生成器
	 */
	private static Random random = new Random();

	/**
	 * 随机数[-1,1]
	 * 
	 * @return
	 */
	private static double randNumber() {
		return random.nextDouble() * 2.0 - 1.00;
	}

	/**
	 * Sigmoid 计算函数
	 * 神经网络的学习函数
	 * @param x
	 * @return
	 */
	public static double sigmoidFunction(double x) {
		return 1.00 / (1.00 + Math.exp(-x));
	}

	/**
	 * 输入层各神经元输出
	 */
	private double[][] a1;

	/**
	 * 输出层各神经元输出
	 */
	private double[] a2;

	/**
	 * 输入层至隐藏层阈值
	 */
	private double[] b;
	
	/**
	 * 每组输入模式对应的期望输出
	 */
	private double[] d;
	
	/**
	 * 输入层至隐藏层阈值调整量
	 */
	private double[] db;
	
	/**
	 * 隐藏层至输出层权值阈值调整量
	 */
	private double[] dr;
	
	/**
	 * 隐藏层至输出层权值调整量
	 */
	private double[][] dv;
	
	/**
	 * 输入层至隐藏层权值调整量
	 */
	private double[][] dw;

	/**
	 * 输出误差
	 */
	private double[] e;
	
	/**
	 * 总平方误差(前一次运算)
	 */
	private double E_LAST;

	/**
	 * 总平方误差(本次运算)
	 */
	private double E_NOW;
	
	/**
	 * 隐藏层至输入层反向误差
	 */
	private double[][] e1;
	
	/**
	 * 输出层至隐藏层反向误差
	 */
	private double[] e2;

	/**
	 * 误差指标
	 */
	public final double EE = 1E-6;

	/**
	 * 中间过程量
	 */
	private double h;

	/**
	 * 隐藏层神经元数目
	 */
	private int HN = 2;
	
	/**
	 * 中间计算辅助值,
	 * 统一管理临时变量
	 */
	private int i, j, k, t, n;

	/**
	 * 输入层神经元数目
	 */
	private int IN = 2;
	
	/**
	 * 学习速率
	 */
	public double lr = DEFAULT_LR;
	
	/**
	 * 学习速率自适应变化控制器
	 */
	private boolean lrSelfSuit = false;
	
	/**
	 * 一组的样本规模
	 */
	private int MODEL = MODEL_ARR;
	
	/**
	 * 输出层神经元数目
	 */
	private int ON = 1;
	
	/**
	 * 输入模式(即平常所说的输入值集合)
	 */
	private double[][] p;

	/**
	 * 隐藏层至输出层权值阈值
	 */
	private double[] r;

	/**
	 * 隐藏层至输出层权值
	 */
	private double[][] v;

	/**
	 * 输入层至隐藏层权值
	 */
	private double[][] w;

	/**
	 * 构造一个神经网络单元
	 * 
	 * @param in
	 *            输入层节点个数
	 * @param hn
	 *            隐藏层节点个数
	 */
	public BasicEBP(int in, int hn) {
		super();
		IN = in;
		HN = hn;
		initNet();
	}

	/**
	 * 计算反向误差e1,e2
	 */
	private void bTolerate() {
		for (k = 0; k < MODEL; k++) {
			e2[k] = a2[k] * (1 - a2[k]) * e[k];
		}
		for (t = 0; t < ON; t++)
			for (j = 0; j < HN; j++)
				for (k = 0; k < MODEL; k++) {
					e1[j][k] = a1[j][k] * (1.00 - a1[j][k]) * e2[k] * v[j][t];
				}
	}

	/**
	 * 调整权值和阈值
	 */
	private void change() {
		for (j = 0; j < HN; j++)
			for (k = 0; k < MODEL; k++) {
				e1[j][k] = lr * e1[j][k];
			}
		for (i = 0; i < IN; i++)
			for (j = 0; j < HN; j++) {
				dw[i][j] = 0.00;
				for (k = 0; k < MODEL; k++) {
					dw[i][j] += e1[j][k] * p[i][k];
				}
				w[i][j] += dw[i][j];
			}
		for (j = 0; j < HN; j++) {
			db[j] = 0.00;
			for (k = 0; k < MODEL; k++) {
				db[j] += e1[j][k];
			}
			b[j] += db[j];
		}
		for (k = 0; k < MODEL; k++) {
			e2[k] = lr * e2[k];
		}
		for (t = 0; t < ON; t++)
			for (j = 0; j < HN; j++) {
				dv[j][t] = 0.0;
				for (k = 0; k < MODEL; k++) {
					dv[j][t] += e2[k] * a1[j][k];
				}
				v[j][t] += dv[j][t];
			}
		for (t = 0; t < ON; t++) {
			dr[t] = 0.0;
			for (k = 0; k < MODEL; k++) {
				dr[t] += e2[k];
			}
			r[t] += dr[t];
		}
		lrSelfSuitMethod();
	}

	/**
	 * 神经网络学习参数
	 */
	public double getLr() {
		return this.lr;
	}

	/**
	 * 计算训练后的输出结果
	 */
	public double[] getOut() {
		for (j = 0; j < HN; j++)
			for (k = 0; k < MODEL; k++) {
				h = 0.00;
				for (i = 0; i < IN; i++) {
					h += w[i][j] * p[i][k];
				}
				a1[j][k] = sigmoidFunction(h + b[j]);
			}
		for (t = 0; t < ON; t++)
			for (k = 0; k < MODEL; k++) {
				h = 0.00;
				for (j = 0; j < HN; j++) {
					h += v[j][t] * a1[j][k];
				}
				a2[k] = sigmoidFunction(h + r[t]);
			}
		return a2;
	}

	/**
	 * 获取训练次数
	 * 
	 * @return
	 */
	public int getStudyTimes() {
		return this.n;
	}

	/**
	 * 计算各个输入层神经元的输出值
	 */
	private void hOut() {
		for (j = 0; j < HN; j++)
			for (k = 0; k < MODEL; k++) {
				h = 0.00;
				for (i = 0; i < IN; i++) {
					h += w[i][j] * p[i][k];
				}
				a1[j][k] = sigmoidFunction(h + b[j]);
			}
	}

	/**
	 * 初始化内存空间和权值
	 */
	private void initNet() {
		w = new double[IN][HN];
		dw = new double[IN][HN];
		b = new double[HN];
		db = new double[HN];
		v = new double[HN][ON];
		dv = new double[HN][ON];
		r = new double[ON];
		dr = new double[ON];

		for (i = 0; i < IN; i++)
			for (j = 0; j < HN; j++) {
				w[i][j] = randNumber();
			}
		for (j = 0; j < HN; j++)
			for (t = 0; t < ON; t++) {
				v[j][t] = randNumber();
			}
		for (j = 0; j < HN; j++) {
			b[j] = randNumber();
		}
		for (t = 0; t < ON; t++) {
			r[t] = randNumber();
		}
	}

	/**
	 * 初始化学习过程
	 */
	private void initStudy() {
		initWork();
		//a1 = new double[IN][MODEL];
		//a2 = new double[MODEL];
		e2 = new double[MODEL];
		e1 = new double[IN][MODEL];
		//e = new double[MODEL];
		E_LAST = DEFAULT_INIT_E_LAST;
	}
	
	/**
	 * 初始化神经网络计算过程
	 */
	private void initWork(){
		a1 = new double[IN][MODEL];
		a2 = new double[MODEL];
		e = new double[MODEL];
	}

	/**
	 * 自适应调整学习速率
	 */
	private boolean lrSelfSuitMethod() {
		if (lrSelfSuit) {
			double a = 1.05;
			double b = 0.7;
			double rp = 1.04;
			if (E_LAST != DEFAULT_INIT_E_LAST) {
				if (E_NOW < E_LAST) {
					lr *= a;
				} else if (E_NOW >= rp * E_LAST) {
					lr *= b;
				}
			}
			E_LAST = E_NOW;
			if (lr < MIN_LR) {
				lr *= a;
			} else if (lr > MAX_LR) {
				lr *= b;
			}
		}
		return lrSelfSuit;
	}

	/**
	 * 训练结果后期望值与网络实际输出值之间的误差 一般用于带有期望值的输出
	 */
	public double[] nOutTolerate() {
		for (k = 0; k < MODEL; k++) {
			e[k] = d[k] - a2[k];
		}
		return e;
	}

	/**
	 * 输出层各个神经元的输出
	 */
	private void oOut() {
		for (t = 0; t < ON; t++)
			for (k = 0; k < MODEL; k++) {
				h = 0.00;
				for (j = 0; j < HN; j++) {
					h += v[j][t] * a1[j][k];
				}
				a2[k] = sigmoidFunction(h + r[t]);
			}
	}

	/**
	 * 神经网络学习,缺省的最大训练次数2^31-1
	 * 
	 * @param p
	 *            输入模式组
	 * @param d
	 *            输入模式期望结果
	 */
	public void study(double[][] p, double[] d) {
		int n = Integer.MAX_VALUE;
		study(p, d, n);
	}

	/**
	 * 神经网络学习
	 * 
	 * @param p
	 *            输入模式组
	 * @param d
	 *            输入模式期望结果
	 * @param N
	 *            最大训练次数
	 */
	public void study(double[][] p, double[] d, int N) {
		ConditionWithN c = new ConditionWithN(N);
		studyWithCondition(p, d, c);
	}

	/**
	 * 神经网络学习
	 * 
	 * @param p
	 *            输入模式组
	 * @param d
	 *            期望输出
	 * @param runTime
	 *            允许执行时间,单位是毫秒
	 */
	public void study(double[][] p, double[] d, long runTime) {
		ConditionWithStudyTime c = new ConditionWithStudyTime(runTime);
		studyWithCondition(p, d, c);
	}

	/**
	 * 神经网络学习
	 * 
	 * @param p
	 *            输入模式组
	 * @param d
	 *            期望结果
	 * @param condition
	 *            结束条件
	 */
	private void studyWithCondition(double[][] p, double[] d,
			StudyEndCondition condition) {
		condition.beginStudy();
		this.p = p;
		this.d = d;
		this.MODEL = d.length;
		initStudy();
		this.n = 0;
		do {
			hOut();
			oOut();
			tolerate();
			bTolerate();
			change();
			condition.studying();
			n++;
		} while ((E_NOW > EE) && (!condition.isEnd()));
		condition.endStudy();
	}

	/**
	 * 关闭学习速率自适应变化
	 * 
	 * @return
	 */
	public boolean toCloseSelfSuitLR() {
		return this.lrSelfSuit = false;
	}

	/**
	 * 计算输出误差e
	 */
	private void tolerate() {
		E_NOW = 0;
		for (k = 0; k < MODEL; k++) {
			e[k] = d[k] - a2[k];
			E_NOW += e[k] * e[k] / 2.0;
		}
	}

	/**
	 * 开启学习速率自适应变化
	 * 
	 * @return
	 */
	public boolean toOpenSelfSuitLR() {
		return this.lrSelfSuit = true;
	}

	@Override
	public String toString() {
		String s = "";
		s += "输入层至隐藏层权值w：\n";
		for (i = 0; i < IN; i++) {
			for (j = 0; j < HN; j++) {
				s += "w[" + i + "][" + j + "]=" + w[i][j] + '\t';
			}
			s += '\n';
		}
		s += "隐藏层阈值b:\n";
		for (j = 0; j < HN; j++) {
			s += "b[" + j + "]=" + b[j] + '\t';
		}
		s += '\n';
		s += "隐藏层至输出层权值v:\n";
		for (j = 0; j < HN; j++)
			for (t = 0; t < ON; t++) {
				s += "v[" + j + "][" + t + "]=" + v[j][t] + '\t';
			}
		s += '\n';
		s += "输出层阈值t:\n";
		for (t = 0; t < ON; t++) {
			s += "r[" + t + "]=" + r[t] + '\t';
		}
		s += '\n';
		return s;
	}

	/**
	 * 对新的输入进行神经网络处理,并返回结果.
	 * 
	 * @param p
	 *            输入模式组
	 * @return 输出结果组
	 */
	public double[] work(double[][] p) {
		this.p = p;
		this.MODEL = p[0].length;
		initWork();
		hOut();
		oOut();
		return this.a2;
	}

	/**
	 * 对新的输入进行神经网络处理,并返回结果. 多用于评估误差
	 * 
	 * @param p
	 *            输入模式对
	 * @param d
	 *            期望输出模式组
	 * @return
	 */
	public double[] work(double[][] p, double[] d) {
		 work(p);
		 this.d = d;
		 tolerate();
		 return this.a2;
	}

}
