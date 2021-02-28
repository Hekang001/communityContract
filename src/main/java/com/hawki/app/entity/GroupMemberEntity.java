package com.hawki.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author zyoung
 * @email yz3204190017@gmail.com
 * @date 2021-02-28 18:08:16
 */
@Data
@TableName("group_member")
public class GroupMemberEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private Long userid;
	/**
	 * 
	 */
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
