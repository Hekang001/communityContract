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
 * @date 2021-02-28 18:08:17
 */
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
