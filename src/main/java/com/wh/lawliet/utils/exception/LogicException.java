package com.wh.lawliet.utils.exception;

//import com.zhanfu.system.utils.servlet.ServletTool;
//import com.zhanfu.system.utils.user.UserTool;
import com.wh.lawliet.utils.servlet.ServletTool;
import com.wh.lawliet.utils.user.UserTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;

/**
 * 抛出异常并打印log
 * @author ylw
 * @Description
 * @date 2021/9/30
 */
@Slf4j
public class LogicException extends RuntimeException {
    public LogicException(String err){
        super(err);
        log.error(err);
    }
    /**
     * @param className 类名
     * @param methodName 方法名
     * @param err 错误
     * @param arguments 参数
     */
    public LogicException(String className, String methodName, String err, @Nullable Object... arguments){
        super(err);
        log.error("类:{},用户:{},路径:{},请求方式:{},方法:{},错误:{},线程:{},arguments:{}",className,
                UserTool.getUserId(), ServletTool.getRequestURL()
                , ServletTool.getRequestMethod(),methodName,err, Thread.currentThread().getId(),arguments);
    }
}
