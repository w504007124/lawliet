package com.wh.lawliet.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/*
*
 * @Author ylw
 * @Description web接收返回体
 * @Date 9:11 2021/12/31
 **/
@Slf4j
@Data
@AllArgsConstructor
public class ResponseResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int OK = 200;
    public static final int SERVER_ERROR = 500;
    /**
     * 返回编码
     */
    private Integer code;

    /**
     * 返回消息
     */
    private String msg;

    /**
     * 返回数据
     */
    private T data;

    private  static final String SUCCESS_MSG="操作成功";

    private static final String ERROR_MSG="操作失败";

    /**
     * 返回信息
     *
     * @param code    返回编码
     * @param msg 返回消息
     * @param data    返回数据
     * @return
     */
    private static ResponseResult response(Integer code, String msg,  Object data) {
        return new ResponseResult(code, msg, data);
    }

    /**
     * 返回成功
     *
     * @param data 返回数据
     * @return
     */
    public static ResponseResult success(Object data) {
        return response(OK,SUCCESS_MSG, data);
    }

    /**
     * 返回成功
     *
     * @return
     */
    public static ResponseResult success() {
        return response(OK,SUCCESS_MSG, null);
    }

    /**
     * 返回成功
     *
     * @param data 返回数据
     * @return
     */
    public static ResponseResult success(Integer code, String msg,  Object data) {
        return response(code,msg, data);
    }

    /**
     * 返回成功
     *
     * @param
     * @return
     */
    public static ResponseResult success(Integer code, String msg) {
        return response(code,msg, null);
    }

    /**
     * 返回成功提示
     *
     * @param msg 返回数据
     * @return
     */
    public static ResponseResult successMsg(String msg) {
        return response(OK, msg,  null);
    }

    /**
     * 返回错误
     *
     * @param code
     * @return
     */
    public static ResponseResult fail(Integer code, String msg) {
        return response(code, msg, null);
    }

    /**
     * 返回错误
     *
     * @param code
     * @return
     */
    public static ResponseResult fail(Integer code, String msg,Object data) {
        return response(code, msg, data);
    }

    /**
     * 返回错误
     *
     * @param msg
     * @return
     */
    public static ResponseResult fail(String msg) {
        return response(SERVER_ERROR,  msg, null);
    }

    public static ResponseResult fail(){
        return response(SERVER_ERROR,ERROR_MSG,null);
    }
}
