
package com.CC.app.ds.neuralnet;

import java.util.Random;

/**
 * The all function class
 * 基本的BP算法,没有任何优化.
 * 误差比较大.
 * 
 */
public class BasicBP {

	/**
	 * 输入层节点个数
	 */
	private long m;
	/**
	 * 隐含层节点(神经细胞)个数
	 */
	private long s;
	/**
	 * 输出层节点(神经细胞)个数
	 */
	private long n;

	/**
	 * 中间各层的结果,
	 * 其中:
	 * a ---- 输入值
	 * b ---- 输入层计算的输出结果
	 * g = f(b)
	 * g ---- 隐藏层的输入值
	 * h ---- 隐藏层的输出值
	 */
	private double[] a, b, g, h;
	/**
	 * 输出层的结果,需要记录以前的期望值和实际值
	 * c ---- 期望输出值
	 * o = f(h)
	 * o ---- 实际输出值
	 */
	private double[] c[], o[];

	/**
	 * 中间的权值
	 */
	private double w[][], v[][];

	/**
	 * 阈值
	 */
	private double[] st, gm;

	/**
	 * 反向计算使用的校正误差
	 */
	private double[] d, e;

	/**
	 * 学习参数
	 */
	private double arf, bd;

	/**
	 * 训练精度
	 */
	private double epq;

	/**
	 * 记录已经参加训练的样本数目(相对于一组)
	 */
	private int k;

	/**
	 * 每组样本规模
	 */
	private int K;

	/**
	 * 学习次数
	 */
	private int studyTime;
	/**
	 * 预定学习总次数
	 */
	private int initStudyTime;

	/**
	 * 随机数字生成器
	 */
	private Random random;

	/**
	 * 神经网络构造类
	 * 
	 * @param m
	 *            输入节点个数
	 * @param n
	 *            输出节点个数
	 * @param k
	 *            每组学习样本的容量
	 * @param initStudyTime
	 *            预计的学习次数
	 */
	public BasicBP(long m, long n, int k, int initStudyTime) {
		super();
		this.m = m;
		this.n = n;
		random = new Random();
		calSnumber();
		K = k;
		this.initStudyTime = initStudyTime;
		initMem();
		initVal();
	}

	public BasicBP(long m, long s, long n, int k, int initStudyTime) {
		super();
		this.m = m;
		this.s = s;
		this.n = n;
		random = new Random();
		K = k;
		this.initStudyTime = initStudyTime;
		initMem();
		initVal();
	}

	/**
	 * 初始化内存
	 */
	private void initMem() {
		a = null;
		b = new double[(int) s];
		g = new double[(int) s];
		h = new double[(int) n];
		c = new double[(int) K][];
		o = new double[(int) K][];
		w = new double[(int) m][(int) s];
		v = new double[(int) s][(int) n];
		gm = new double[(int) n];
		st = new double[(int) s];
		d = new double[(int) n];
		e = new double[(int) s];
	}

	/**
	 * 初始化数据
	 */
	private void initVal() {
		studyTime = 0;
		arf = bd = 0.7;// 0.7;
		epq = 0.0001;// 0.000001;
		initWeightThreshold();
	}

	/**
	 * 初始化权值和阈值
	 */
	private void initWeightThreshold() {
		for (int i = 0; i < n; i++) {
			gm[i] =  getInitWeight();
		}
		for (int i = 0; i < s; i++) {
			st[i] =  getInitWeight();
		}
		for (int i = 0; i < m; i++)
			for (int j = 0; j < s; j++)
				w[i][j] = getInitWeight();
		for (int j = 0; j < s; j++)
			for (int t = 0; t < n; t++)
				v[j][t] = getInitWeight();
	}

	/**
	 * 设置隐含层的节点个数
	 */
	private void calSnumber() {
		s = Math.round(Math.sqrt(m + n)) + random.nextInt(10);
		if ((s < (m + n) / 20 + 1) || (s > (m + n) * 3 / 4)) {
			s = (m + n) / 2;
		}
	}

	/**
	 * Sigmoid 计算函数
	 * 
	 * @param x
	 * @return
	 */
	private double calSigmoid(double x) {
		double tmp = 1.0 + Math.exp(-x);
		return 1.0 / tmp;
	}

	/**
	 * 初始权值计算函数
	 * 
	 * @return 随机选取[-1,1]上的一个小数
	 */
	private double getInitWeight() {
		// return (random.nextDouble() + random.nextDouble() - 1)/2;//
		// return ((random.nextDouble()/ Short.MAX_VALUE) * 2 - 1);
		//return ((random.nextDouble()) * 2 - 1) / 2;
		return random.nextDouble()*2-1;
	}

	/**
	 * 均方误差函数 MSE(Mean Square Error)
	 * @return
	 */
	private double meanSquareError() {
		double e = 0.0;
		for (int k = 0; k < K; k++) {
			for (int t = 0; t < n; t++) {
				double tmp = o[k][t] - c[k][t];
				e += tmp * tmp / 2.0;
			}
		}
		e = e / (2 * K);
		return e;
	}

	/**
	 * 正向传播过程
	 */
	private double[] calPos(double[] o) {
		for (int j = 0; j < s; j++) {
			double tmp = 0;
			for (int i = 0; i < m; i++) {
				tmp += w[i][j] * a[i];
			}
			b[j] = tmp + st[j];
			g[j] = calSigmoid(b[j]);
		}

		for (int t = 0; t < n; t++) {
			double tmp = 0;
			for (int j = 0; j < s; j++) {
				tmp += v[j][t] * g[j];
			}
			tmp = tmp + gm[t];
			h[t] = tmp;
			o[t] = calSigmoid(h[t]);
		}
		return o;
	}

	/**
	 * 反向学习过程
	 */
	private void calRev() {
		for (int t = 0; t < n; t++) {
			d[t] = (c[k][t] - o[k][t]) * o[k][t] * (1 - o[k][t]);
		}
		for (int j = 0; j < s; j++) {
			double tmp = 0;
			for (int t = 0; t < n; t++) {
				tmp += d[t] * v[j][t];
			}
			e[j] = tmp * g[j] * (1 - g[j]);
		}
		for (int j = 0; j < s; j++)
			for (int t = 0; t < n; t++) {
				v[j][t] += arf * d[t] * g[j];
			}
		// for (int t = 0; t < n; t++) {
		// gm[t] = gm[t] + arf * d[t];
		// }
		for (int i = 0; i < m; i++)
			for (int j = 0; j < s; j++) {
				w[i][j] += bd * e[j] * a[i];
			}
		// for (int j = 0; j < s; j++) {
		// st[j] = st[j] + bd * e[j];
		// }

	}

	/**
	 * 开始新一组的训练
	 */
	public void resetStudyArray() {
		k = 0;
		studyTime++;
	}

	/**
	 * 一组样本学习结束
	 * 
	 * @return
	 */
	public boolean studyArrayFinish() {
		return k >= K;
	}

	/**
	 * 学习结束
	 * 
	 * @return
	 */
	public boolean studyFinish() {
		boolean f = ((meanSquareError()) < epq) || (studyTime > initStudyTime);
		// boolean f = (studyTime >initStudyTime);
		// boolean f = ((meanSquareError()) < epq);
		return f;
	}

	public boolean study(double[] a, double[] c) {
		if (a.length != m || c.length != n) {
			return false;
		} else {
			this.a = a;
			this.c[k] = c;
			o[k] = new double[c.length];
			calPos(o[k]);
			calRev();
			k++;
			return true;
		}
	}

	public double[] work(double[] a) {
		if (a.length != m) {
			return null;
		} else {
			this.a = a;
			return work();
		}
	}

	private double[] work() {
		double[] tmp = new double[(int) n];
		return calPos(tmp);
	}

	public long getSnumber() {
		return this.s;
	}

	@Override
	public String toString() {
		String str = "";
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < s; j++) {
				str = str + " W[" + i + "][" + j + "]=" + w[i][j];
			}
			str += "\n";
		}

		for (int i = 0; i < s; i++) {
			for (int j = 0; j < n; j++) {
				str = str + " V[" + i + "][" + j + "]=" + v[i][j];
			}
			str += "\n";
		}
		str += "INPUT Num:"+this.m+"\n";
		str += "IMPLY Num:"+this.s+"\n";
		str += "OUTPUT Num:"+this.n+"\n";
		str += "K Num:"+this.K+"\n";
		str += "Study time Num:"+this.initStudyTime+"\n";
//		System.out.println();
		return str;
	}

}
