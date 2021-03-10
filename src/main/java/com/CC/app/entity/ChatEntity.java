package com.CC.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 私聊
 */
@Data
@TableName("chat")
public class ChatEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@TableId
	private Long id;
	/**
	 * 接收者id
	 */
	private Long toid;
	/**
	 * 发送者id
	 */
	private Long fromid;
	/**
	 * 消息内容
	 */
	private String content;
	/**
	 * 已读否
	 */
	private Integer status;

	private Date createTime;

	private Date updateTime;

}
