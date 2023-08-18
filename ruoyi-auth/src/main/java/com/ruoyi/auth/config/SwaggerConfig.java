package com.ruoyi.auth.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashSet;

/**
 * @author yangsang
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)//导入其他的配置类 让配置生效
public class SwaggerConfig {
    @Bean
    public Docket buildDocket() {
        HashSet<String> strings = new HashSet<>();
        strings.add("application/json");
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInfo())
                //设置返回数据类型
                .produces(strings)
                //分组名称
                .groupName("1.0-SNAPSHOT")
                .select()
                //这里指定扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.ruoyi.auth.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    private ApiInfo buildApiInfo() {
        Contact contact = new Contact(
                "若依认证授权中心服务api文档",
                "http://localhost:9200/doc.html",
                "nisang9527@outlook.com");
        return new ApiInfoBuilder()
                .title("ruoyi-file")
                .description("若依认证授权中心服务api文档")
                .contact(contact)
                .version("1.0-SNAPSHOT").build();
    }
}