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
@TableName("like_post")
public class LikeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 帖子
	 */
	private Integer postId;
	/**
	 * 点赞人id
	 */
	private Integer usersId;
	/**
	 * 
	 */
	@TableId
	private Long id;

}
