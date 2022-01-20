package com.wh.lawliet.config;

//import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * 什么？我是真滴帅
 *
 * @author WuHao on 13:50 2021/11/11
 */
@Configuration // 必须
@EnableSwagger2 // 必须
//@EnableSwaggerBootstrapUI // 第三方swagger增强API注解
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.wh.lawliet"))
                .paths(PathSelectors.any())
                .build();
//                .securitySchemes(security())
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("lawliet统一测试系统")
                .version("1.0")
                .description("统一后台管理系统")
                .contact("lawliet")
                .build();
    }

//    private List<ApiKey> security() {
//        List<ApiKey> list = new ArrayList<>();
//        ApiKey apiKey = new ApiKey("Authorization", "Authorization", "header");
//        list.add(apiKey);
//        return list;
//    }
    /**
     * 允许跨域
     *
     * @param registry
     */
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("*")
//                .allowCredentials(true)
//                .allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH")
//                .maxAge(3600);
//    }
}
