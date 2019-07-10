package com.seanzx.common;

import lombok.Getter;

/**
 * @author : zhaoxin
 * @date : 2019/7/10.
 */
@Getter
public class ServerResponse<T> {

    private int code;

    private String message;

    private T data;

    private ServerResponse(int code) {
        this.code = code;
    }

    private ServerResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private ServerResponse(int code, T data) {
        this.code = code;
        this.data = data;
    }

    private ServerResponse(int code, String message, T data) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public static ServerResponse ofSuccess() {
        return new ServerResponse(ResponseCode.SUCCESS.getCode());
    }

    public static ServerResponse ofNeedLogin() {
        return new ServerResponse(ResponseCode.NEED_LOGIN.getCode());
    }

    public static ServerResponse ofMessage(String message) {
        return new ServerResponse(ResponseCode.SUCCESS.getCode(), message);
    }

    public static ServerResponse ofMessage(int code, String message) {
        return new ServerResponse(code, message);
    }

    public static <T>ServerResponse<T> ofData(T data) {
        return new ServerResponse<>(ResponseCode.SUCCESS.getCode(), data);
    }

    public static <T>ServerResponse<T> ofData(int code, T data) {
        return new ServerResponse<>(code, data);
    }

    public static <T>ServerResponse<T> ofMessageAndData(String message, T data) {
        return new ServerResponse<>(ResponseCode.SUCCESS.getCode(), message, data);
    }

    public static <T>ServerResponse<T> ofMessageAndData(int code, String message, T data) {
        return new ServerResponse<>(code, message, data);
    }
}
