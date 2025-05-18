package cn.xuyj.springboot.template.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @Author: xuyj
 * @Date: 2025/5/18 21:37
 * @Email: 1768335576@qq.com
 * @Desc：捕获接口的返回，并进行封装
 */
@RestControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice {
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 判断是否要执行 beforeBodyWrite 方法，true为执行，false不执行，有注解标记的时候处理返回值
     * 这里整合swagger出现了问题，swagger相关的不拦截
     * @param returnType
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(MethodParameter returnType, Class aClass) {
        return !returnType.getDeclaringClass().getName().contains("springfox");
    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        //针对返回值是字符串的
        if (body instanceof String)
            return objectMapper.writeValueAsString(Result.success(body));
        //针对异常捕获的
        if (body instanceof Result)
            return body;
        //接口成功调用的
        return Result.success(body);
    }
}
