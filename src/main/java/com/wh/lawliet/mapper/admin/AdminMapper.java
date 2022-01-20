package com.wh.lawliet.mapper.admin;

import com.wh.lawliet.base.BaseMapper;
import com.wh.lawliet.entity.Admin;

import java.util.List;

/**
 * 什么？我是真滴帅
 *
 * @author WuHao on 17:01 2021/11/12
 */
public interface AdminMapper extends BaseMapper<Admin> {
    /**
     * 添加管理员
     * @param admin
     * @return
     */
    void addAdmin(Admin admin);

    int threadAddAdmin(Admin admin);

    List<Admin> getListAdmin();
//    List<Admin> getListAdmin(@Param("adminName") String adminName);
}
