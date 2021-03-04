
package com.hawki.app.ds.bean;

import com.hawki.app.ds.ValueComputeMethod;
import org.apache.commons.lang.ArrayUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 数据集.本数据集用来处理和保存银行账户的基本信息.
 * 主要进行神经网络的计算.
 * 
 */
public class BankAccountBeanDB {

	/**
	 * 用于记录各个属性列信息
	 */
	private List<AttributeInfor> attrInfoList = new LinkedList<AttributeInfor>();

	/**
	 * 所属类别名称,用于方法调用.
	 */
	private String groupName;

	/**
	 * 用来描述输入数据列的各个性质.本xml文件用于描述银行账户的基本信息
	 */
	private String bankAccountDSXML="bankAccountDS" +
			".xml";
	
	/**
	 * 为了进行合适的路径绑定,特给出一个定位用的class
	 */
	private Class classForXML;
	
	/**
	 * 比率
	 */
	private String rate;

	/**
	 * 数据是否已经初始化
	 */
	private boolean isComputeAttrInforList; 
	
	/**
	 * 数据集合
	 */
	private BankAccountBean[] datas;

	
	public BankAccountBeanDB(){
		this.isComputeAttrInforList = false;
	}
	
	/**
	 * 用xml配置文件构造
	 * @param bankAccountDSXML xml文件名称(只要名称)
	 * @param classForXML 和xml文件在同一个包的class
	 */
	public BankAccountBeanDB(String bankAccountDSXML, Class classForXML) {
		super();
		this.bankAccountDSXML = bankAccountDSXML;
		this.classForXML = classForXML;
	}


	/**
	 * 属性列信息
	 * 
	 * @return
	 */
	public List<AttributeInfor> getAttrInfoList() {
		return attrInfoList;
	}

	/**
	 * 获取数据集
	 * 
	 * @return
	 */
	public BankAccountBean[] getDatas() {
		return datas;
	}
	
	/**
	 * 提起数据集子序列 [b,e)
	 * @param b 开始序号(包含)
	 * @param e 结束序号(不包含)
	 * @return
	 */
	public BankAccountBean[] getSubDatas(int b,int e){
		return (BankAccountBean[]) ArrayUtils.subarray(datas, b, e);
	}

	/**
	 * 获取类别名称
	 * 
	 * @return
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * 初始化属性列信息
	 */
	public void initAttrInfoList() {
		try {
			if(classForXML==null){
				classForXML = BankAccountBeanDB.class;
			}
			readXMLFile(classForXML.getResource(bankAccountDSXML));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 数据集赋值
	 * 
	 * @param oArr
	 */
	public void initDatas(Object[] oArr) {
		this.datas = new BankAccountBean[oArr.length];
		Class c = oArr[0].getClass();
		for (int i = 0; i < oArr.length; i++) {
			datas[i] = new BankAccountBean();
			try {
				Method method;
				for (AttributeInfor a : this.attrInfoList) {
					method = c.getMethod("get" + a.getName(), null);
					datas[i].setElementOfValue(a.getLabel(), (Double) method
							.invoke(oArr[i], null));
				}
				method = c.getMethod("get" + this.groupName, null);
				datas[i].setGroup((Double) method.invoke(oArr[i], null));
				method = c.getMethod("get" + this.rate, null);
				datas[i].setRate((Double) method.invoke(oArr[i], null));
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 解析xml文件
	 * 
	 * @param url
	 * @throws Exception
	 */
	private void readXMLFile(URL url) throws Exception {
		File f = new File(url.getPath());
		SAXReader reader = new SAXReader();
		Document doc = reader.read(f);
		Element root = doc.getRootElement();
		Element foo;
		Iterator<Element> i = root.elementIterator("attribute");
		this.groupName = root.elementText("groupName");
		this.rate = root.elementText("rate");
		while (i.hasNext()) {
			foo = i.next();
			String name = foo.elementText("name");
			int label = Integer.parseInt(foo.elementText("label"));
			double nullValue = Double.parseDouble(foo.elementText("nullValue"));
			String operationName = foo.elementText("operate");
			ValueComputeMethod method = (ValueComputeMethod) Class.forName(
					operationName).newInstance();
			AttributeInfor att = new AttributeInfor(label, nullValue, name,
					method);
			this.attrInfoList.add(att);
		}
	}

	/**
	 * 设置属性列信息
	 * 
	 * @param attrInfoList
	 */
	public void setAttrInfoList(List<AttributeInfor> attrInfoList) {
		this.attrInfoList = attrInfoList;
	}

	/**
	 * 设置属性列类别信息名称
	 * 
	 * @param groupName
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/**
	 * 计算各项属性的各个内部值
	 */
	public void computeAttrInfoList() {
		for (AttributeInfor a : this.attrInfoList) {
			a.computeAll(datas);
		}
		this.isComputeAttrInforList = true;
	}

	/**
	 * 获取子序列
	 * @param tot 子序列长度
	 * @return
	 */
	public List<AttributeInfor> getSubAttributeInforsOrderByEntropy(int tot) {
		AttributeInfor[] aiArr = attrInfoList.toArray(new AttributeInfor[0]);
		Arrays.sort(aiArr);
		List<AttributeInfor> list = new LinkedList<AttributeInfor>();
		int l = aiArr.length - 1;
		for (int i = 0; i < Math.min(tot,l+1); i++) {
			list.add(aiArr[l - i]);
		}
		return list;
	}
	
	/**
	 * 数据集是否经过初始化处理.并可以使用
	 * @return
	 */
	public boolean canUseAllInfo(){
		return this.isComputeAttrInforList;
	}
	
	public int datasLength(){
		return this.datas.length;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

}
