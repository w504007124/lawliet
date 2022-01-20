package com.wh.lawliet.service.serviceImpl;

import com.wh.lawliet.entity.Admin;
import com.wh.lawliet.mapper.admin.AdminMapper;
import com.wh.lawliet.service.AdminService;
import com.wh.lawliet.threadpool.AdminCallable;
import com.wh.lawliet.utils.AjaxResult;
import com.wh.lawliet.utils.dateUtils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 什么？我是真滴帅
 *
 * @author WuHao on 16:59 2021/11/12
 */
@Slf4j
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    private ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 30, 0L, TimeUnit.SECONDS, new LinkedBlockingDeque<>());


    //    @Transactional(rollbackFor = Exception.class)
    @Override
    public AjaxResult addAdmin(Admin admin) {
        admin.setCreateTime(DateUtils.getNowDate());
        adminMapper.addAdmin(admin);
        return AjaxResult.success("添加成功");
    }

    @Override
    public AjaxResult<Admin> getList() {
//        this.addListAdmin();
        return AjaxResult.success(adminMapper.getListAdmin());
    }

    /**
     * 多线程测试
     * @return
     */
    public AjaxResult addListAdmin(){
        // 存储线程的返回值
        List<Future<String>> results = new LinkedList<Future<String>>();
        long startTime = System.currentTimeMillis();
        log.info("多线程开始时间{}",startTime);
        for (int i = 0; i < 100; i++) {
            Admin admin = new Admin();
            admin.setAdminAccount(""+i);
            admin.setAdminName(""+i);
            admin.setAdminPassword(""+i);
            admin.setCreateTime(DateUtils.getNowDate());
            // 用了3798ms
//            adminMapper.threadAddAdmin(admin);

            // 无返回值多线程
            threadPoolExecutor.execute(()->{
                this.throwPool1(admin);
            });

            // 用了15ms,回调，有返回值
//            AdminCallable task = new AdminCallable(this,admin);
//            threadPoolExecutor.submit(task);
//            results.add(future);
        }
        long endTime = System.currentTimeMillis();
        log.info("多线程结束时间{}",endTime);
        log.info("程序运行时间{}",(endTime-startTime)+"ms");
        return AjaxResult.success(results);
    }

    public String throwPool(Admin admin){
        adminMapper.threadAddAdmin(admin);
        return "添加成功:"+admin.getId();
    }
    public void throwPool1(Admin admin){
        adminMapper.threadAddAdmin(admin);
    }
}
