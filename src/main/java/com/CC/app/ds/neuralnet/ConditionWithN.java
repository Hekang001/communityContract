package com.CC.app.ds.neuralnet;

/**
 * 通过记录学习次数来作为结束条件
 *
 */
public class ConditionWithN implements StudyEndCondition {
	private int n;
	private int Num;

	public ConditionWithN(int n) {
		this.Num = n-1;
	}

	public void beginStudy() {
		n = 0;
	}

	public void endStudy() {
	}

	public boolean isEnd() {
		return (this.n > this.Num);
	}

	public void studying() {
		this.n++;
	}
}
