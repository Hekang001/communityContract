package com.CC.app.ds.bean;

/**
 * 用于个人信用评级的指标BEAN
 *
 */
public class BankAccountBIBean {
	
	/**
	 * ID
	 */
	private String id;
	
	/**
	 * 用户姓名
	 */
	private String name;
	
	/**
	 * 性别
	 */
	private Double genderValue;
	/**
	 * 健康状况
	 */
	private Double healthValue;
	/**
	 * 年龄
	 */
	private Double ageValue;
	/**
	 * 学历
	 */
	private Double schoolRecordValue;
	/**
	 * 婚姻状况
	 */
	private Double marryValue;
	/**
	 * 住宅性质
	 */
	private Double homeValue;
	/**
	 * 家庭年收入
	 */
	private Double homeYearIncomeValue;
	/**
	 * 负担状况
	 */
	private Double burdenValue;
	/**
	 * 银行账户
	 */
	private Double bankAccountValue;
	/**
	 * 保险
	 */
	private Double insureValue;
	/**
	 * 公司企业规模
	 */
	private Double companySizeValue;
	/**
	 * 职业
	 */
	private Double jobProfessionValue;
	/**
	 * 连续工作年限
	 */
	private Double workYearValue;
	/**
	 * 单位性质
	 */
	private Double jobPropertyValue;
	
	/**
	 * 职位
	 */
	private Double officerValue;
	/**
	 * 本人月收入(本人年收入/12)
	 */
	private Double personMonthIncomeValue;
	
	/**
	 * 家庭月收入(家庭年收入/12)
	 */
	private Double homeMonthIncomeValue;
	
	/**
	 * 连续逾期还款次数
	 */
	private Double overDateValue;
	
	/**
	 * 用于存储个人信用评级分数
	 */
	private Double creditRate;

	public Double getCreditRate() {
		return creditRate;
	}

	public void setCreditRate(Double creditRate) {
		this.creditRate = creditRate;
	}

	public Double getGenderValue() {
		return genderValue;
	}
	
	public void setGenderValue(Double genderValue) {
		this.genderValue = genderValue;
	}

	public Double getHealthValue() {
		return healthValue;
	}

	public void setHealthValue(Double healthValue) {
		this.healthValue = healthValue;
	}

	public Double getAgeValue() {
		return ageValue;
	}

	public void setAgeValue(Double ageValue) {
		this.ageValue = ageValue;
	}

	public Double getSchoolRecordValue() {
		return schoolRecordValue;
	}

	public void setSchoolRecordValue(Double schoolRecordValue) {
		this.schoolRecordValue = schoolRecordValue;
	}

	public Double getMarryValue() {
		return marryValue;
	}

	public void setMarryValue(Double marryValue) {
		this.marryValue = marryValue;
	}

	public Double getHomeValue() {
		return homeValue;
	}

	public void setHomeValue(Double homeValue) {
		this.homeValue = homeValue;
	}

	public Double getHomeYearIncomeValue() {
		return homeYearIncomeValue;
	}

	public void setHomeYearIncomeValue(Double homeYearIncomeValue) {
		this.homeYearIncomeValue = homeYearIncomeValue;
	}

	public Double getBurdenValue() {
		return burdenValue;
	}

	public void setBurdenValue(Double burdenValue) {
		this.burdenValue = burdenValue;
	}

	public Double getBankAccountValue() {
		return bankAccountValue;
	}

	public void setBankAccountValue(Double bankAccountValue) {
		this.bankAccountValue = bankAccountValue;
	}

	public Double getInsureValue() {
		return insureValue;
	}

	public void setInsureValue(Double insureValue) {
		this.insureValue = insureValue;
	}

	public Double getCompanySizeValue() {
		return companySizeValue;
	}

	public void setCompanySizeValue(Double companySizeValue) {
		this.companySizeValue = companySizeValue;
	}

	public Double getJobProfessionValue() {
		return jobProfessionValue;
	}

	public void setJobProfessionValue(Double jobProfessionValue) {
		this.jobProfessionValue = jobProfessionValue;
	}

	public Double getWorkYearValue() {
		return workYearValue;
	}

	public void setWorkYearValue(Double workYearValue) {
		this.workYearValue = workYearValue;
	}

	public Double getJobPropertyValue() {
		return jobPropertyValue;
	}

	public void setJobPropertyValue(Double jobPropertyValue) {
		this.jobPropertyValue = jobPropertyValue;
	}

	public Double getOfficerValue() {
		return officerValue;
	}

	public void setOfficerValue(Double officerValue) {
		this.officerValue = officerValue;
	}

	public Double getPersonMonthIncomeValue() {
		return personMonthIncomeValue;
	}

	public void setPersonMonthIncomeValue(Double personMonthIncomeValue) {
		this.personMonthIncomeValue = personMonthIncomeValue;
	}

	public Double getHomeMonthIncomeValue() {
		return homeMonthIncomeValue;
	}

	public void setHomeMonthIncomeValue(Double homeMonthIncomeValue) {
		this.homeMonthIncomeValue = homeMonthIncomeValue;
	}

	public Double getOverDateValue() {
		return overDateValue;
	}

	public void setOverDateValue(Double overDateValue) {
		this.overDateValue = overDateValue;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

