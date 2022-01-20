package com.wh.lawliet.service.serviceImpl;

import cn.hutool.http.HttpUtil;
import cn.hutool.log.StaticLog;
import com.wh.lawliet.model.resp.UploadResp;
import com.wh.lawliet.service.ICommonService;
import com.wh.lawliet.utils.AjaxResult;
//import com.zhanfu.system.domain.FcCommonAttachment;
//import com.zhanfu.system.enumClass.ResponseCode;
//import com.zhanfu.system.service.ICommonService;
//import com.zhanfu.system.service.IFcCommonAttachmentService;
//import com.zhanfu.system.utils.ResponseResult;
//import com.zhanfu.system.utils.fdfs.FastDFSClient;
//import com.zhanfu.system.web.resp.UploadResp;
//import com.wh.lawliet.utils.fdfs.FastDFSClient;
import com.wh.lawliet.utils.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipOutputStream;

/**
 * 通用业务类
 *
 * @author WuHao on 10:47 2021/12/10
 */
@Service
public class CommonServiceImpl implements ICommonService {
//    @Resource
//    private FastDFSClient fastDFSClient;

//    @Resource
//    private IFcCommonAttachmentService fcCommonAttachmentService;

    /**
     * 上传文件
     * @param file
     * @return
     */
    @Override
    public AjaxResult<UploadResp> uploadFile(MultipartFile file) {
        // 获取上传文件的名称
        String fileName = file.getOriginalFilename();
        UploadResp uploadResp = new UploadResp();

        try {
            String path = null;
//            fastDFSClient.uploadFile(file.getBytes(), fileName);
            uploadResp.setFileName(fileName);
            uploadResp.setFileUrl(path);
        } catch (Exception e) {
            StaticLog.error("文件存储异常:{}", e.getMessage());
            return AjaxResult.fail(AjaxResult.SERVER_ERROR, e.getMessage());
        }
        return AjaxResult.success(uploadResp);
    }

    @Override
    public void download(HttpServletResponse response, Long id) {
//        try {
//            FcCommonAttachment fcCommonAttachment = fcCommonAttachmentService.selectFcCommonAttachmentById(id);
//            String storeAddress = fcCommonAttachment.getStoreAddress();
//            if (fastDFSClient.getFileInfo(storeAddress) == null) {
//                throw new RuntimeException("文件不存在");
//            }
//
//            byte [] buffer = fastDFSClient.downloadFile(storeAddress);
//            String fileName = java.net.URLEncoder.encode(fcCommonAttachment.getFileName(),"UTF-8");
//            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
//            response.setContentType("application/octet-stream;charset=utf-8");
//            response.setHeader("Pragma", "no-cache");
//            response.setHeader("Cache-Control", "no-cache");
//            response.setDateHeader("Expires", 0);
//
//            OutputStream output = response.getOutputStream();
//            output.write(buffer);
//            output.flush();
//            output.close();
//        } catch (Exception e) {
//            throw new RuntimeException("文件下载失败");
//        }

    }

    @Override
    public HttpServletResponse downloadZip(List<String> fileUrl, HttpServletResponse response) {
        List<File> files = new ArrayList<File>();

        for (String url : fileUrl) {
            files.add(new File(url));
        }
//        final String METHOD_NAME = "downloadZip";
//        if (null == commonReq) {
//            throw new LogicException(this.className, METHOD_NAME, "文件列表不能为空");
//        }
//        for (FcCommonAttachment file : commonReq.getFileList()) {
//            // 下载到本地
//            HttpUtil.downloadFile(prefixUrl+file.getStoreAddress(), file.getFileName());
//            // 添加文件
//            files.add(new File(file.getFileName()));
//        }
        //打包凭证.zip与EXCEL一起打包
        try {
            String zipFilenameA = "/tempFileA.zip" ;
            File fileA = new File(zipFilenameA);
            if (!fileA.exists()){
                fileA.createNewFile();
            }
            response.reset();
            //response.getWriter()
            //创建文件输出流
            FileOutputStream fousa = new FileOutputStream(fileA);
            ZipOutputStream zipOutA = new ZipOutputStream(fousa);
            FileUtil.zipFile(files, zipOutA);
            zipOutA.close();
            fousa.close();
            return FileUtil.downloadZip(fileA,response);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
