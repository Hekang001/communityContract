package com.hawki.app.ds.model;

import com.hawki.app.ds.bean.BankAccountBIBean;
import com.hawki.app.ds.bean.BankAccountBeanDB;
import com.hawki.app.ds.bean.BankLoanBIBean;
import com.hawki.app.ds.bean.BankLoanBeanDB;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 用于银行个人用户业务的模型方法类
 * @version 0.0.1
 */
public class BankAccountXXXModelUtil {
	
	/**
	 * 构建已学习完毕的个人信用评级模型类,方便用户直接进行预测分析.
	 * 默认的单个数据组(10个数据为一组)学习时间为10000ms.
	 * @param beans 基本信息
	 * @param iNode 输入层节点数目
	 * @return
	 */
	public static BankAccountCreditRateNNModel
		createBankAccountCreditRateNNModelWithHavingStudied(BankAccountBIBean[] beans, int iNode){
		BankAccountCreditRateNNModel model;
		BankAccountBeanDB db = createBankAccountBeanDBWithHavingInit(beans);
		model = new BankAccountCreditRateNNModel(db);
		//对iNode进行处理,防止越界
		iNode = Math.min(iNode, db.getAttrInfoList().size());
		//用iNode个指标和6个中间节点进行神经网络学习
		model.itselfStudy(iNode, 6, 10000);
		return model;
	}
	
	/**
	 * 构造已经初始化好的个人基本信息数据bean.
	 * @param beans
	 * @return
	 */
	public static BankAccountBeanDB 
		createBankAccountBeanDBWithHavingInit(BankAccountBIBean[] beans){
		BankAccountBeanDB db = new BankAccountBeanDB("BankAccountBIBean.xml",BankAccountBIBean.class);
		db.initAttrInfoList();
		db.initDatas(beans);
		db.computeAttrInfoList();
		return db;
	}
	
	/**
	 * 构建因子分析模型和logic回归分析模型,并直接返回计算结果.
	 * @param beans 用于因子分析和logic回归分析的数据集合
	 * @return 结果表达式
	 */
	public static String createBankLoanFALogisticModelAndGetResult(BankLoanBIBean[] beans){
		BankLoanBeanDB db;
		db = new BankLoanBeanDB("BankLoanBIBean.xml",BankLoanBIBean.class);
		db.initAttrInfoList();
		db.initDatas(beans);
		db.computeAttrInfoList();
		BankLoanFALogisticModel model = new BankLoanFALogisticModel(db);
		model.factorAnalyzer();
		model.logisticCompute();
		return model.getResult();
	}
	
	/**
	 * 利用字符串函数和bean数据集进行计算,利用java中的脚本计算方法达到效果
	 * @param function 函数
	 * @param bean 数据集合,利用RTTI机制提取所有字段的数值,并完全替代function中的字符串.
	 * @return
	 */
	public static double computeFunctionWithBean(String function, Object bean){
		Class c = bean.getClass();
		Field[] fields = c.getDeclaredFields();
		Method method;
		double d=0.0;
		for(Field f:fields){
			String s = f.getName().charAt(0)+"";
			s =s.toUpperCase();
			s += f.getName().substring(1);
			try {
				method = c.getMethod("get"+s, null);
				d = (Double)method.invoke(bean, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
			function = s+"="+d+";"+function;//function.replaceAll(s, Double.toString(d));
		}
		ScriptEngineManager factory = new ScriptEngineManager();
	    ScriptEngine engine = factory.getEngineByName("JavaScript");
	    try {
			d = (Double)engine.eval(function);
		} catch (ScriptException e) {
			e.printStackTrace();
		}
		return d;
	}

}

