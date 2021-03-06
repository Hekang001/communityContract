package com.CC.app.common.exception;


public enum BizCodeEnum {
    UNKNOW_EXCEPTION(10000,"系统未知异常"),
    VAILD_EXCEPTION(10001,"参数格式校验失败"),
    LOGINACCT_PASSWORD_INVAILD_EXCEPTION(10002,"账号密码错误"),
    ADDFRIEND_FAILED_EXCEPTION(10003, "添加好友失败");

    private int code;
    private String msg;
    BizCodeEnum(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
