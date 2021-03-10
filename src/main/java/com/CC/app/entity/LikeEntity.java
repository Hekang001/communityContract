package com.CC.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
@TableName("like_post")
public class LikeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 帖子
	 */
	private Long postId;
	/**
	 * 点赞人id
	 */
	private Long usersId;
	/**
	 * 
	 */
	@TableId
	private Long id;

}
