package com.CC.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
@TableName("group_member")
public class GroupMemberEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@TableId
	private Long id;

	private Long userid;

	private Long groupid;
	/**
	 * 同意待定拒绝踢出
	 */
	private Integer status;
	/**
	 * 成员昵称
	 */
	private String nickname;
	/**
	 * 成员身份
	 */
	private String identity;
	/**
	 * 
	 */
	private Date createtime;
	/**
	 * 
	 */
	private Date updatetime;

}
