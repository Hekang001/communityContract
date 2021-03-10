package com.CC.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
@TableName("wallet")
public class WalletEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 卡号
	 */
	private String cid;

	private Long userId;
	/**
	 * 银行
	 */
	private String bank;
	/**
	 * 是否默认
	 */
	private Integer isDefault;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 
	 */
	private Date updateTime;
	/**
	 * 资产
	 */
	private BigDecimal property;

}
