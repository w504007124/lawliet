package com.wh.lawliet.threadpool;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 什么？我是真滴帅
 *
 * @author WuHao on 15:53 2021/12/27
 */

@Component
@Slf4j
public class GenerateThreadExecutor {

    @Getter
    private ExecutorService taskExecutor;
    private int corePoolSize = 50 ;
    public GenerateThreadExecutor() {
        taskExecutor = Executors.newFixedThreadPool(corePoolSize);
    }
    public <T> Future<T> submit(Callable<T> task) {
        return taskExecutor.submit(task);
    }

}
