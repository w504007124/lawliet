package com.wh.lawliet.utils.id;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author ylw
 * @Description
 * @date 2021/9/30
 */
@Component
public class IdComponent {
    @Bean
    public Snowflake snowflake(){
        Snowflake snowflake = IdUtil.getSnowflake(1, 1);
        return snowflake;
    }
}
