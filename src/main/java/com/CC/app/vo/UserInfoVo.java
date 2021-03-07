package com.CC.app.vo;

import lombok.Data;

@Data
public class UserInfoVo {

    private Long id;
    /**
     * 用户名
     */
    private Boolean isCertificated = true;

    /**
     * 是否好友
     */
    private Boolean isFriend;
    /**
     * 昵称
     */
    private String nickName;

    /**
     * 性别1男0女
     */
    private String gender;
    /**
     * 住址
     */
    private String address;

    /**
     * 工作
     */
    private String job;
    /**
     * 证件号
     */
    private String identity;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 评级分
     */
    private Integer score;
    /**
     * 星级
     */
    private Integer riskLevel;

    /**
     * 头像地址
     */
    private String avatar;

    private Integer loanCount;

    private Integer finishCount;

    private Integer processCount;

}

