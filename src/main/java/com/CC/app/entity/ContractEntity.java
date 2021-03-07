package com.CC.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author zyoung
 * @email yz3204190017@gmail.com
 * @date 2021-02-28 18:08:18
 */
@Data
@TableName("contract")
public class ContractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 贷方id
	 */
	private Long creditId;
	/**
	 * 借方id
	 */
	private Long debtId;
	/**
	 * 贷方确认
	 */
	private Integer creditConfirm;
	/**
	 * 借方确认
	 */
	private Integer debtConfirm;
	/**
	 * 金额
	 */
	private BigDecimal amount;
	/**
	 * 创建日期
	 */
	private Date date;
	/**
	 * 预计还款日期
	 */
	private Date dueDate;
	/**
	 * 年利率
	 */
	private BigDecimal annualRate;
	/**
	 * 逾期利率
	 */
	private BigDecimal overdue;
	/**
	 * 用途
	 */
	private String purpose;
	/**
	 * 佣金
	 */
	private BigDecimal commission;
	/**
	 * 状态
	 */
	private Integer status;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 
	 */
	private Date updateTime;

}
