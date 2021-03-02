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
 * @date 2021-03-02 21:30:17
 */
@Data
@TableName("add_group")
public class AddGroupEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 群号
	 */
	private Long groupid;
	/**
	 * 用户id
	 */
	private Long userid;
	/**
	 * 
	 */
	private String content;
	/**
	 * 
	 */
	private Integer status;
	/**
	 * 
	 */
	private Date createtime;
	/**
	 * 
	 */
	private Date updatetime;

}
