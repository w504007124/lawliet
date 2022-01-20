package com.wh.lawliet.service;

import com.wh.lawliet.entity.Admin;
import com.wh.lawliet.utils.AjaxResult;

/**
 * 什么？我是真滴帅
 *
 * @author WuHao on 16:59 2021/11/12
 */

public interface AdminService {
    /**
     * 添加管理员
     * @param admin
     * @return
     */
    AjaxResult addAdmin(Admin admin);

    AjaxResult<Admin> getList();

}
