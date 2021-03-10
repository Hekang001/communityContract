package com.CC.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
@TableName("group")
public class GroupEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 群号
	 */
	@TableId
	private Long id;
	/**
	 * 群主
	 */
	private Long masterId;
	/**
	 * 群名
	 */
	private String gname;
	/**
	 * 创建日期
	 */
	private Date date;
	/**
	 * 群公告
	 */
	private String notice;
	/**
	 * 群简介
	 */
	private String introduction;
	/**
	 * 
	 */
	private String avatar;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 
	 */
	private Date updatetIme;

}
