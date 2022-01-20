package com.wh.lawliet;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * 什么？我是真滴帅
 *
 * @author WuHao on 17:10 2021/12/27
 */
@Slf4j
public class test {
//    public class TestNormal3 {
        public static ThreadPoolTaskExecutor slowExecutor() {
            ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
            executor.setCorePoolSize(8);
            executor.setMaxPoolSize(50);
            executor.initialize();
            return executor;
        }

        public static void main(String[] args) throws Exception{
            ThreadPoolTaskExecutor executor= slowExecutor();
            long start=System.currentTimeMillis();
            List<Long> list=new ArrayList<>();
            List<Future<List<Long>>>futureList=new LinkedList<>();
            for (int j=0;j<100;j++){
                CallableDemo2 callableDemo=new CallableDemo2();
                Future<List<Long>>future=executor.submit(callableDemo);
                futureList.add(future);
            }
            for (Future<List<Long>> future:futureList){
                list.addAll(future.get());
            }
            long end=System.currentTimeMillis();
            log.info("耗时:{}",end-start);
        }
    }

    class CallableDemo2 implements Callable<List<Long>> {
        Snowflake snowflake = IdUtil.getSnowflake(1, 1);
        @Override
        public List<Long> call() throws Exception {
            List<Long> list=new ArrayList<>();
            for (int i=0;i<700000;i++){
                list.add(snowflake.nextId());
            }
            return list;
        }

//    }
}
