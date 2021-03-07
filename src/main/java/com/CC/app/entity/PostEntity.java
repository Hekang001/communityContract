package com.CC.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 发帖
 * 
 * @author zyoung
 * @email yz3204190017@gmail.com
 * @date 2021-02-28 18:08:17
 */
@Data
@TableName("post")
public class PostEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 发贴者账号
	 */
	private Long userid;
	/**
	 * 金额

	 */
	private BigDecimal amount;
	/**
	 * 还款期限
	 */
	private Integer duetime;
	/**
	 * 利息
	 */
	private BigDecimal interest;
	/**
	 * 谁可以见
	 */
	private Integer permission;
	/**
	 * 可否转发
	 */
	private Integer forward;
	/**
	 * 帖子内容

	 */
	private String content;
	/**
	 * 
	 */
	private Integer status;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 
	 */
	private Date updatetime;
	/**
	 * 原帖
	 */
	private Long forwardId;
	/**
	 * 推送 0仅好友 
	 */
	private Integer push;

}
