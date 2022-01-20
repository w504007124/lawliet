package com.wh.lawliet.model.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 上传文件返回实体类
 *
 * @author WuHao on 10:30 2021/12/10
 */
@Data
@ApiModel("上传文件返回实体类")
public class UploadResp {

    @ApiModelProperty("文件名称")
    private String fileName;
    @ApiModelProperty("文件路径")
    private String fileUrl;
}
