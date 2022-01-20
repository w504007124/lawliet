package com.wh.lawliet.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;

/**
 * 什么？我是真滴帅
 * 抛出异常并打印Log日志
 * @author WuHao on 9:46 2021/11/15
 */
@Slf4j
public class LogicException extends RuntimeException {

    public LogicException(String err) {
        super(err);
        log.error(err);
    }

    public LogicException(String className, String modelName, String err, @Nullable Object... arguments) {
        super(err);
//        log.error("类:{},用户:{},路径:{},请求方式:{},方法:{},错误:{},线程:{},arguments:{}", className,
//                );
        log.error("类:{},路径:{},请求方式:{},方法:{},错误:{},线程:{},arguments:{}", className
                );
    }
}
