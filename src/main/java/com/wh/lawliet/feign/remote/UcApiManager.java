//package com.wh.lawliet.feign.remote;
//
//import com.zhanfu.system.domain.vo.CompanyOptionsVo;
//import com.zhanfu.system.utils.ResponseResult;
//import com.zhanfu.system.vo.remote.DictDataVo;
//import com.zhanfu.system.vo.remote.FinanceContractCompanyVo;
//
//import java.util.List;
//
///**
// * @description: 远程调用uc-api服务
// * @author: ylw
// * @create: 2021-12-20 15:12
// **/
//public interface UcApiManager {
//    /**
//     * @description: 获取dictType列表
//     * @author: ylw
//     **/
//    ResponseResult<List<DictDataVo>> getDictType(String dictType);
//
//    /**
//     * @throws :
//     * @Description:
//     * @Author : renhao
//     * @create : 2022/1/7 2:34 下午
//     * @params :
//     */
//    ResponseResult<List<DictDataVo>> getInfo(Long dictCode);
//
//    ResponseResult<List<FinanceContractCompanyVo>> getConpanyList(Integer pageNum, Integer pageSize);
//
//    ResponseResult<List<CompanyOptionsVo>> getCompanyOptions();
//}
