package com.wh.lawliet.base;


import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * 什么？我是真滴帅
 *
 * @author WuHao on 16:16 2021/11/15
 */
public interface BaseMapper<T> extends Mapper<T>, ConditionMapper<T>, IdsMapper<T>, InsertListMapper<T> {
    //, BatchMapper<T>


}
