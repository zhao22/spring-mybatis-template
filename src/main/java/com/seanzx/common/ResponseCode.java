package com.seanzx.common;

/**
 * @author : zhaoxin
 * @date : 2019/7/10.
 */
public enum ResponseCode {

    SUCCESS(0, "SUCCESS"), ILLEGAL_ARGUMENT(1, "ILLEGAL_ARGUMENT"), NEED_LOGIN(2, "NEED_LOGIN");

    private final int code;
    private final String desc;

    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
