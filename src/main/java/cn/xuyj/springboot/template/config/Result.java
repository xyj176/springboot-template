package cn.xuyj.springboot.template.config;

import lombok.Data;

/**
 * @Author: xuyj
 * @Date: 2025/5/18 21:30
 * @Email: 1768335576@qq.com
 * @Desc：类描述
 */
@Data
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMessage("success");
        result.setData(data);
        return result;
    }

    public static <T> Result<T> error(Integer code, String message) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }
}
