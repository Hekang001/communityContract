package com.hawki.app.ds.neuralnet;

public class ConditionWithStudyTime implements StudyEndCondition{
	private long runTime;
	private long time;
	public ConditionWithStudyTime(long n){
		this.runTime = n;
	}
	public void beginStudy() {
		this.time = System.currentTimeMillis();
	}

	public void endStudy() {
	}

	public boolean isEnd() {
		return (System.currentTimeMillis()-time)>this.runTime;
	}

	public void studying() {
	}
}