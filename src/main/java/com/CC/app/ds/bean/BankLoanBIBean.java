package com.CC.app.ds.bean;

/**
 * 个人贷款评级信息包装类
 */
public class BankLoanBIBean {
	
	/**
	 * 神经网络个人评级结果
	 */
	private Double creditRateValue;
	
	/**
	 * 贷款类型
	 */
	private Double loanTypeValue;
	
	/**
	 * 当年利率和无风险一年期国债之比
	 */
	private Double rateDebtValue;
	
	/**
	 * 贷款年限
	 */
	private Double loanYearValue;
	
	/**
	 * 月还款额和家庭月收入之比
	 */
	private Double revertIncomeValue;

	/**
	 * 个人担保得分
	 */
	private Double guaranteeValue;
	
	/**
	 * 逾期还款次数
	 */
	private Double overDateValue;
	
	/**
	 * 抵押品比率
	 */
	private Double gageRateValue;
	
	/**
	 * 贷款级别
	 */
	private Double group;

	public Double getCreditRateValue() {
		return creditRateValue;
	}

	public void setCreditRateValue(Double creditRateValue) {
		this.creditRateValue = creditRateValue;
	}

	public Double getLoanTypeValue() {
		return loanTypeValue;
	}

	public void setLoanTypeValue(Double loanTypeValue) {
		this.loanTypeValue = loanTypeValue;
	}

	public Double getRateDebtValue() {
		return rateDebtValue;
	}

	public void setRateDebtValue(Double rateDebtValue) {
		this.rateDebtValue = rateDebtValue;
	}

	public Double getLoanYearValue() {
		return loanYearValue;
	}

	public void setLoanYearValue(Double loanYearValue) {
		this.loanYearValue = loanYearValue;
	}

	public Double getRevertIncomeValue() {
		return revertIncomeValue;
	}

	public void setRevertIncomeValue(Double revertIncomeValue) {
		this.revertIncomeValue = revertIncomeValue;
	}

	public Double getGuaranteeValue() {
		return guaranteeValue;
	}

	public void setGuaranteeValue(Double guaranteeValue) {
		this.guaranteeValue = guaranteeValue;
	}

	public Double getOverDateValue() {
		return overDateValue;
	}

	public void setOverDateValue(Double overDateValue) {
		this.overDateValue = overDateValue;
	}

	public Double getGageRateValue() {
		return gageRateValue;
	}

	public void setGageRateValue(Double gageRateValue) {
		this.gageRateValue = gageRateValue;
	}

	public Double getGroup() {
		return group;
	}

	public void setGroup(Double group) {
		this.group = group;
	}
}

