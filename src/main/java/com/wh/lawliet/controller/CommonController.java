package com.wh.lawliet.controller;

import com.sun.org.apache.bcel.internal.classfile.Constant;
import com.wh.lawliet.apo.SysLogs;
import com.wh.lawliet.model.resp.UploadResp;
import com.wh.lawliet.service.ICommonService;
import com.wh.lawliet.utils.AjaxResult;
import com.wh.lawliet.utils.FileUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipOutputStream;

/**
 * 通用类
 *
 * @author WuHao on 10:01 2022/1/6
 */
@RestController
@RequestMapping("/common")
public class CommonController {

    @Autowired
    private ICommonService iCommonService;

    @PostMapping("/upload")
    @ApiOperation(value = "上传单个附件")
    @SysLogs("上传单个附件")
    public AjaxResult<UploadResp> upload(@RequestParam("file") MultipartFile file) {
        return iCommonService.uploadFile(file);
    }

    @GetMapping("/download")
    @ApiOperation(value = "下载附件")
    @SysLogs("下载附件")
    public void download(HttpServletResponse response, Long id) {
        iCommonService.download(response,id);
    }

    @RequestMapping(value = "/downloadZip")
    @ApiOperation(value = "打包下载文件zip")
    @SysLogs("打包下载文件zip")
    public HttpServletResponse downloadZip(@RequestBody List<String> fileUrl, HttpServletResponse response)throws Exception {
        return iCommonService.downloadZip(fileUrl, response) ;
    }
}
