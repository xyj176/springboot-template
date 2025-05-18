package cn.xuyj.springboot.template.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: xuyj
 * @Date: 2025/5/18 21:19
 * @Email: 1768335576@qq.com
 * @Desc：类描述
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello()
    {
        return "Hello World!";
    }
}
