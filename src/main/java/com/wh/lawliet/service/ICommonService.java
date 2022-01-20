package com.wh.lawliet.service;

import com.wh.lawliet.model.resp.UploadResp;
import com.wh.lawliet.utils.AjaxResult;
//import com.zhanfu.system.utils.ResponseResult;
//import com.zhanfu.system.web.resp.UploadResp;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 通用业务类接口
 *
 * @author WuHao on 10:43 2021/12/10
 */
public interface ICommonService {

    /**
     * 上传文件
     * @param file
     * @return
     */
    AjaxResult<UploadResp> uploadFile(MultipartFile file);

    /**
     * 下载文件
     * @param response
     * @param id
     */
    void download(HttpServletResponse response, Long id);

    HttpServletResponse downloadZip(List<String> fileUrl, HttpServletResponse response );
}
