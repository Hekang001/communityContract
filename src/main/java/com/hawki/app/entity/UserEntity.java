package com.hawki.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户表
 * 
 * @author zyoung
 * @email yz3204190017@gmail.com
 * @date 2021-02-28 18:08:18
 */
@Data
@TableName("user")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 昵称
	 */
	private String nickName;
	/**
	 * 真实姓名
	 */
	private String realName;
	/**
	 * 性别1男0女

	 */
	private Integer sex;
	/**
	 * 手机号
	 */
	private String phoneNum;
	/**
	 * 电子邮箱
	 */
	private String email;
	/**
	 * 生日
	 */
	private Date birthday;
	/**
	 * 学历
	 */
	private String education;
	/**
	 * 住址
	 */
	private String address;
	/**
	 * 住宅信息
	 */
	private String housing;
	/**
	 * 已婚：1 未婚：2
	 */
	private Integer marriage;
	/**
	 * 收入（元）
	 */
	private BigDecimal income;
	/**
	 * 债务
	 */
	private BigDecimal debt;
	/**
	 * 保险
	 */
	private String insurance;
	/**
	 * 证件号
	 */
	private String identity;
	/**
	 * 评级日期
	 */
	private Date ratingday;
	/**
	 * 评级分
	 */
	private Integer score;
	/**
	 * 星级
	 */
	private Integer stars;
	/**
	 * 余额
	 */
	private BigDecimal balance;
	/**
	 * 头像地址
	 */
	private String avatar;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date updateTime;

}
