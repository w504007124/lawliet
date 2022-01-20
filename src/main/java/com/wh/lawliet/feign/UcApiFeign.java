package com.wh.lawliet.feign;

import com.wh.lawliet.feign.fallBack.UcApiFeignFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @description: 调用uc-api的feign
 * @author: ylw
 * @create: 2021-12-20 15:01
 **/
@FeignClient(value = "uc-api", fallback = UcApiFeignFallBack.class)
public interface UcApiFeign {
    @GetMapping("dict/data/type/{dictType}")
    Object getDictDataType(@PathVariable(value = "dictType") String dictType);

    @GetMapping("dict/data/{dictCode}")
    Object getInfo(@PathVariable(value = "dictCode") Long dictCode);

    /**
     * 分页查询所有公司信息
     */
    @GetMapping("company/list")
    Object getConpanyList(@RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "pageSize") Integer pageSize);

    @GetMapping("company/getCompanyOptions")
    Object getCompanyOptions();
}
