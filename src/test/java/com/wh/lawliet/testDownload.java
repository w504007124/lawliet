package com.wh.lawliet;

import com.wh.lawliet.service.ICommonService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * 什么？我是真滴帅
 *
 * @author WuHao on 16:11 2022/1/10
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class testDownload {

    @Autowired
    ICommonService iCommonService;

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    @Test
    public void go2(){
        request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");
        response = new MockHttpServletResponse();
        List<String> file = new ArrayList<>();
        file.add("F:\\贷后系统\\财务sql.txt");
        file.add("F:\\贷后系统\\财务sql-2.txt");
        file.add("F:\\贷后系统\\资产包明细模板.xlsx");
        file.add("F:\\贷后系统\\佣金结算记录表.xlsx");
        iCommonService.downloadZip(file,response);
    }
}
