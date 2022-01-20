package com.wh.lawliet.test;

import com.wh.lawliet.service.AdminService;
import com.wh.lawliet.service.serviceImpl.AdminServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 什么？我是真滴帅
 *
 * @author WuHao on 17:12 2021/11/12
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestAdmin {
    @Autowired
    AdminServiceImpl adminService;

    @Test
    public void testAdmin() {
        // adminService的方法
    }
}
