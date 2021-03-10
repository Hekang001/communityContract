package com.CC.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 加好友
 *
 */
@Data
@TableName("add_friend")
public class AddFriendEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@TableId
	private Long id;
	/**
	 * 我的账号
	 */
	private Long userid;
	/**
	 * 好友账号
	 */
	private Long frid;
	/**
	 * 申请理由
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
	private Date updateTime;

}
