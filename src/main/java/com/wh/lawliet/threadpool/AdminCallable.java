package com.wh.lawliet.threadpool;

import com.wh.lawliet.entity.Admin;
import com.wh.lawliet.service.serviceImpl.AdminServiceImpl;

import java.util.concurrent.Callable;

/**
 * 什么？我是真滴帅
 *
 * @author WuHao on 15:54 2021/12/27
 */
public class AdminCallable implements Callable<String> {

    private AdminServiceImpl adminService;

    private Admin admin;
    //参数
//    private String params;
    public AdminCallable(AdminServiceImpl adminService, Admin admin) {
        this.adminService = adminService;
        this.admin = admin;
    }

    @Override
    public String call() throws Exception {
        return adminService.throwPool(admin);
    }
}
