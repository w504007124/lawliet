//package com.wh.lawliet.feign.remote.impl;
//
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.zhanfu.system.domain.vo.CompanyOptionsVo;
//import com.zhanfu.system.feign.UcApiFeign;
//import com.zhanfu.system.manager.remote.UcApiManager;
//import com.zhanfu.system.utils.ResponseResult;
//import com.zhanfu.system.utils.exception.LogicException;
//import com.zhanfu.system.utils.log.LogUtils;
//import com.zhanfu.system.vo.remote.DictDataVo;
//import com.zhanfu.system.vo.remote.FinanceContractCompanyVo;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.Resource;
//import java.util.List;
//
///**
// * @description:
// * @author: ylw
// * @create: 2021-12-20 15:12
// **/
//@Component
//public class UcApiManagerImpl implements UcApiManager {
//    @Resource
//    UcApiFeign ucApiFeign;
//
//    private String className = "";
//
//    @PostConstruct
//    public void init() {
//        this.className = this.getClass().getName();
//    }
//
//    @Override
//    public ResponseResult<List<DictDataVo>> getDictType(String dictType) {
//        final String METHOD_NAME = "getDictType";
//        if (StringUtils.isEmpty(dictType)) {
//            throw new LogicException(this.className, METHOD_NAME, "dictType不可为空");
//        }
//        ResponseResult<List<DictDataVo>> responseResult = null;
//        try {
//            Object object = ucApiFeign.getDictDataType(dictType);
//            ResponseResult<JSONArray> result = JSONObject.parseObject(JSONObject.toJSONString(object), ResponseResult.class);
//            responseResult = ResponseResult.success();
//            responseResult.setCode(result.getCode());
//            responseResult.setMsg(result.getMsg());
//            if (null == result.getData()) {
//                LogUtils.logReqInfo(this.className, METHOD_NAME, "ucApiFeign.getDictDataType接口返回的Data为null", dictType);
//            } else {
//                List<DictDataVo> dataVoList = JSONObject.parseArray(result.getData().toJSONString(), DictDataVo.class);
//                responseResult.setData(dataVoList);
//            }
//        } catch (Exception e) {
//            throw new LogicException(this.className, METHOD_NAME, "ucApiFeign.getDictType调用异常", e, dictType);
//        }
//        if (200 != responseResult.getCode()) {
//            throw new LogicException(this.className, METHOD_NAME, "ucApiFeign.getDictType调用返回异常", dictType);
//        }
//        return responseResult;
//    }
//
//    @Override
//    public ResponseResult<List<DictDataVo>> getInfo(Long dictCode) {
//        final String METHOD_NAME = "getInfo";
//        if (StringUtils.isEmpty(dictCode)) {
//            throw new LogicException(this.className, METHOD_NAME, "dictType不可为空");
//        }
//        ResponseResult<List<DictDataVo>> responseResult = null;
//        try {
//            Object object = ucApiFeign.getInfo(dictCode);
//            ResponseResult<JSONArray> result = JSONObject.parseObject(JSONObject.toJSONString(object), ResponseResult.class);
//            responseResult = ResponseResult.success();
//            responseResult.setCode(result.getCode());
//            responseResult.setMsg(result.getMsg());
//            if (null == result.getData()) {
//                LogUtils.logReqInfo(this.className, METHOD_NAME, "ucApiFeign.getInfo接口返回的Data为null", dictCode);
//            } else {
//                List<DictDataVo> dataVoList = JSONObject.parseArray(result.getData().toJSONString(), DictDataVo.class);
//                responseResult.setData(dataVoList);
//            }
//        } catch (Exception e) {
//            throw new LogicException(this.className, METHOD_NAME, "ucApiFeign.getInfo调用异常", e, dictCode);
//        }
//        if (200 != responseResult.getCode()) {
//            throw new LogicException(this.className, METHOD_NAME, "ucApiFeign.getInfo调用返回异常", dictCode);
//        }
//        return responseResult;
//    }
//
//    @Override
//    public ResponseResult<List<FinanceContractCompanyVo>> getConpanyList(Integer pageNum, Integer pageSize) {
//        final String METHOD_NAME = "getConpanyList";
//        ResponseResult<List<FinanceContractCompanyVo>> responseResult = null;
//        try {
//            Object object = ucApiFeign.getConpanyList(pageNum, pageSize);
//            ResponseResult<JSONArray> result = JSONObject.parseObject(JSONObject.toJSONString(object), ResponseResult.class);
//            responseResult = ResponseResult.success();
//            responseResult.setCode(result.getCode());
//            responseResult.setMsg(result.getMsg());
//            if (null == result.getData()) {
//                LogUtils.logReqInfo(this.className, METHOD_NAME, "ucApiFeign.getConpanyList接口返回的Data为null");
//            } else {
//                List<FinanceContractCompanyVo> dataVoList = JSONObject.parseArray(result.getData().toJSONString(), FinanceContractCompanyVo.class);
//                responseResult.setData(dataVoList);
//            }
//        } catch (Exception e) {
//            throw new LogicException(this.className, METHOD_NAME, "ucApiFeign.getConpanyList调用异常", e);
//        }
//        if (200 != responseResult.getCode()) {
//            throw new LogicException(this.className, METHOD_NAME, "ucApiFeign.getConpanyList调用返回异常");
//        }
//        return responseResult;
//    }
//
//    @Override
//    public ResponseResult<List<CompanyOptionsVo>> getCompanyOptions() {
//        final String METHOD_NAME = "getCompanyOptions";
//        ResponseResult<List<CompanyOptionsVo>> responseResult = null;
//        try {
//            Object object = ucApiFeign.getCompanyOptions();
//            ResponseResult<JSONArray> result = JSONObject.parseObject(JSONObject.toJSONString(object), ResponseResult.class);
//            responseResult = ResponseResult.success();
//            responseResult.setCode(result.getCode());
//            responseResult.setMsg(result.getMsg());
//            if (null == result.getData()) {
//                LogUtils.logReqInfo(this.className, METHOD_NAME, "ucApiFeign.getCompanyOptions接口返回的Data为null");
//            } else {
//                List<CompanyOptionsVo> dataVoList = JSONObject.parseArray(result.getData().toJSONString(), CompanyOptionsVo.class);
//                responseResult.setData(dataVoList);
//            }
//        } catch (Exception e) {
//            throw new LogicException(this.className, METHOD_NAME, "ucApiFeign.getCompanyOptions调用异常", e);
//        }
//        if (200 != responseResult.getCode()) {
//            throw new LogicException(this.className, METHOD_NAME, "ucApiFeign.getCompanyOptions调用返回异常");
//        }
//        return responseResult;
//    }
//
//
//}
