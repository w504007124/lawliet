package com.wh.lawliet.controller;

import com.wh.lawliet.apo.SysLogs;
import com.wh.lawliet.entity.Admin;
import com.wh.lawliet.service.AdminService;
import com.wh.lawliet.utils.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 什么？我是真滴帅
 *
 * @author WuHao on 10:02 2021/11/11
 */
@RestController
@RequestMapping("/admin")
@Api("管理员操作")
public class AdminController {

    @Autowired
    private AdminService adminService;
    /**
     * 添加管理员
     * @param admin
     * @return
     */
    @RequestMapping(value = "/addAdmin", method = RequestMethod.POST)
    @ApiOperation(value = "添加管理员")
    @SysLogs("添加管理员")
    public AjaxResult addAdmin(@RequestBody Admin admin){
        return adminService.addAdmin(admin);
    }

    @GetMapping(value = "/getList")
    @ApiOperation(value = "查看管理员list")
    @SysLogs("查看管理员list")
    public AjaxResult<Admin> getList(){
        return adminService.getList();
    }
}
