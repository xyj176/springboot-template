package cn.xuyj.springboot.template.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @Author: xuyj
 * @Date: 2025/5/18 21:13
 * @Email: 1768335576@qq.com
 * @Desc：类描述
 */
@Configuration
@EnableOpenApi
public class SwaggerConfig {
    @Autowired
    private Environment environment;

    @Bean
    public Docket buildDocket() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(buildApiInfo())
                .select()
                //选择要扫描的接口：带有 RestController 注解的类
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo buildApiInfo() {
        return new ApiInfoBuilder()
                .title(environment.getProperty("spring.application.name") + "系统接口文档")
                .contact(new Contact("xuyj", "https://github.com/xuyj", "1768335576@qq.com"))
                .version("1.0")
                .build();
    }
}
