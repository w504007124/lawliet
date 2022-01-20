package com.wh.lawliet;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wh.lawliet.feign.UcApiFeign;
import com.wh.lawliet.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 什么？我是真滴帅
 *
 * @author WuHao on 14:55 2022/1/12
 */
public class 类型转换 {
    @Autowired
    private UcApiFeign ucApiFeign;
    public void go1() {
//        AjaxResult ajaxResult = (AjaxResult) ucApiFeign.getDictDataType("overdue_status");
//        JSONArray jsonArray = JSONObject.parseArray(JSONObject.toJSONString(ajaxResult.get("data")));
//        List<DIctDataVo> dataVoList = JSONObject.parseArray(jsonArray.toJSONString(), DictDataVo.class);
    }
}
