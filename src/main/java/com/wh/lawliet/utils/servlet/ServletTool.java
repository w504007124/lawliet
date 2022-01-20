package com.wh.lawliet.utils.servlet;

//import com.zhanfu.system.utils.log.LogUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @description:
 * @author: ylw
 * @create: 2021-12-20 15:43
 **/
@Slf4j
public class ServletTool {
    /**
     * 获取请求路径
     * @return
     */
    public static String getRequestURL(){
        HttpServletRequest request=null;
        try {
            request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
            return request.getRequestURI();
        }catch (Exception e){
            log.info(Thread.currentThread().getStackTrace()[1].getClassName(),"getRequestURL","当前可能为异步,没有request对象",e);
        }
        return null;
    }

    /**
     * 获取请求方式,比如get,post,put
     * @return
     */
    public static String getRequestMethod(){
        HttpServletRequest request=null;
        try {
            request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
            return request.getMethod();
        }catch (Exception e){
            log.info(Thread.currentThread().getStackTrace()[1].getClassName(),"getRequestMethod","当前可能为异步,没有request对象",e);
        }
        return null;
    }
}
