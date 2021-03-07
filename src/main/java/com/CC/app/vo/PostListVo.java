package com.CC.app.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class PostListVo {

    /**
     *
     */
    private Long id;
    /**
     * 发贴者账号
     */
    private String nickName;

    private String avatar;

    private Integer score;

    private Integer forward;
    /**
     * 金额
     */
    private BigDecimal amount;
    /**
     * 还款期限
     */
    private Date duetime;

    /**
     * 利息
     */
    private BigDecimal interest;
//    /**
//     * 谁可以见
//     */
//    private Integer permission;
    /**
     * 可否转发
     */
    private Integer type;
    /**
     * 帖子内容
     */
    private String content;
//    /**
//     *
//     */
//    private Integer status;
//    /**
//     *
//     */
//    private Date createTime;
//    /**
//     *
//     */
//    private Date updatetime;
    /**
     * 原帖
     */
    private Long forwardId;
//    /**
//     * 推送 0仅好友
//     */
//    private Integer push;
    private List<LikeVo> likes;

    private List<CommentVo> comments;
}
