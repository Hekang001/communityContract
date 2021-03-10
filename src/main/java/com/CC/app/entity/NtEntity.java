package com.CC.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
@TableName("nt")
public class NtEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 公告标签id
	 */
	@TableId
	private Long id;
	/**
	 * 信息
	 */
	private Long infoid;
	/**
	 * 
	 */
	private String tag;
	/**
	 * 创建时间
	 */
	private Date createtime;
	/**
	 * 更新时间
	 */
	private Date updatetiem;

}
