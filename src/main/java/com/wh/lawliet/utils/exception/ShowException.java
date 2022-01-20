package com.wh.lawliet.utils.exception;
//import com.zhanfu.system.utils.servlet.ServletTool;
//import com.zhanfu.system.utils.user.UserTool;
import com.wh.lawliet.utils.servlet.ServletTool;
import com.wh.lawliet.utils.user.UserTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;

/**
 * 抛出异常打印log在info
 * @author ylw
 * @Description
 * @date 2021/10/27
 */
@Slf4j
public class ShowException extends RuntimeException {
    public ShowException(String err){
        super(err);
    }
    /**
     * @param className 类名
     * @param methodName 方法名
     * @param err 错误
     * @param arguments 参数
     */
    public ShowException(String className, String methodName, String err, @Nullable Object... arguments){
        super(err);
        log.info("类:{},用户:{},路径:{},请求方式:{},方法:{},错误:{},线程:{},arguments:{}",
                className, UserTool.getUserId(), ServletTool.getRequestURL(),
                ServletTool.getRequestMethod(),methodName,err, Thread.currentThread().getId(),arguments);
    }
}
