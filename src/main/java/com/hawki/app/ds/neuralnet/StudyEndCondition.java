package com.hawki.app.ds.neuralnet;
/**
 * 神经网络学习结束条件接口
 * @version 0.0.1
 */
public interface StudyEndCondition {
	/**
	 * 开始学习时执行
	 */
	void beginStudy();
	/**
	 * 结束学习时执行
	 */
	void endStudy();
	/**
	 * 学习结束条件
	 * @return
	 */
	boolean isEnd();
	/**
	 * 学习执行过程中执行
	 */
	void studying();
}
