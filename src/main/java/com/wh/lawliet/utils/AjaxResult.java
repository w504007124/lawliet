package com.wh.lawliet.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * 什么？我是真滴帅
 *
 * @author WuHao on 13:48 2021/11/11
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "请求结果响应体")
public class AjaxResult<T> implements Serializable {

    /**
     * 操作成功的状态码
     */
    @Deprecated
    public static final int GET_OK = 200;
    @Deprecated
    public static final int POST_PUT_OK = 201;
    @Deprecated
    public static final int DEL_OK = 204;
    public static final int OK = 200;

    /**
     * 错误码
     */
    @Deprecated
    public static final int REQUEST_ERROR = 400;
    @Deprecated
    public static final int NOT_PERMISSION = 401;
    @Deprecated
    public static final int NOT_FOUND = 404;
    @Deprecated
    public static final int VERIFY_ERROR = 422;
    public static final int SERVER_ERROR = 500;

    /**
     * 返回是否成功
     */
    @ApiModelProperty(value = "是否成功")
    private Boolean success;
    /**
     * 返回编码
     */
    @ApiModelProperty(value = "响应状态回执码")
    private Integer code;

    /**
     * 返回消息
     */
    @ApiModelProperty(value = "响应回执消息")
    private String msg;

    /**
     * 返回数据
     */
    @ApiModelProperty(value = "数据体")
    private T data;

    public static AjaxResult response(Boolean success, Integer code, String msg, Object data) {
        return new AjaxResult(success, code, msg, data);
    }

    /**
     * 操作成功
     * @return
     */
    public static AjaxResult success() {
        return response(true, 200, "操作成功", null);
    }

    /**
     * 操作成功，返回数据
     * @param data
     * @return
     */
    public static AjaxResult success(Object data) {
        return response(true, 200, "操作成功", data);
    }

    /**
     * 操作成功，返回信息
     * @param msg
     * @return
     */
    public static AjaxResult success(String msg) {
        return response(true, 200, msg, null);
    }

    /**
     * 操作失败
     */
    public static AjaxResult fail(Integer code) {
        return response(false, code, "操作失败", null);
    }

    public static AjaxResult fail(String msg) {
        return response(false, 500, msg,null);
    }
    public static AjaxResult fail(Integer code, String msg) {
        return response(false, code, msg, null);
    }
}
