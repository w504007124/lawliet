//package com.wh.lawliet.utils;
//
//import com.zhanfu.common.security.utils.SecurityUtils;
//import com.zhanfu.system.utils.log.LogUtils;
//import lombok.extern.slf4j.Slf4j;
//
///**
// * @description: 用户工具
// * @author: ylw
// * @create: 2021-12-20 15:12
// **/
//@Slf4j
//public class UserTool {
//    public static Long getUserId(){
//        final String METHOD_NAME="getUserId";
//        Long userId=null;
//        try {
//            userId = SecurityUtils.getUserId();
//        }catch (Exception e){
//            LogUtils.logInfo(Thread.currentThread().getStackTrace()[1].getClassName(),METHOD_NAME,"当前可能为异步"+e.getMessage());
//        }
//        return userId;
//    }
//    public static String getUserName(){
//        final String METHOD_NAME="getUserName";
//        String userName=null;
//        try {
//            SecurityUtils.getUsername();
//        }catch (Exception e){
//            LogUtils.logInfo(Thread.currentThread().getStackTrace()[1].getClassName(),METHOD_NAME,"当前可能为异步"+e.getMessage());
//        }
//        return userName;
//    }
//}
