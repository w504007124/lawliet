package com.wh.lawliet.feign.fallBack;

import com.wh.lawliet.feign.UcApiFeign;
import com.wh.lawliet.utils.ResponseResult;
import org.springframework.stereotype.Component;

@Component
public class UcApiFeignFallBack implements UcApiFeign {
   @Override
   public Object getDictDataType(String dictType){
       return ResponseResult.fail();
   }

    @Override
    public Object getInfo(Long dictCode) {
        return ResponseResult.fail();
    }

    @Override
    public Object getConpanyList(Integer pageNum, Integer pageSize) {
        return ResponseResult.fail();
    }

    @Override
    public Object getCompanyOptions() {
        return ResponseResult.fail();
    }
}
