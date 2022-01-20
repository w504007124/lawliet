package com.wh.lawliet;

import cn.hutool.log.StaticLog;
import com.wh.lawliet.apo.SysLogs;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.wh.lawliet.mapper")
public class LawlietApplication {

    public static void main(String[] args) {
        SpringApplication.run(LawlietApplication.class, args);
        StaticLog.info("LawlietApplication启动成功!!!");
    }

}
