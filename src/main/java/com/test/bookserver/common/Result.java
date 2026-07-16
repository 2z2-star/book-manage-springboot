package com.test.bookserver.common;

/**
 * 统一响应结果封装类
 * @param <T> 泛型，承载具体业务数据
 */
public class Result<T> {
    // 状态码：200=成功，401=未授权，400=参数错误，500=服务器错误
    private Integer code;
    // 响应消息
    private String msg;
    // 响应数据
    private T data;

    // -------------------------- 构造方法 --------------------------
    // 空构造
    public Result() {}

    // 全参构造
    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    // 只包含状态码和消息的构造（无数据）
    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }

    // -------------------------- 静态工厂方法（推荐使用） --------------------------
    // 成功：默认 200 状态码
    public static <T> Result<T> success(T data) {
        return new Result<>(200, "操作成功", data);
    }

    // 成功：自定义状态码和消息 + 数据
    public static <T> Result<T> success(Integer code, String msg, T data) {
        return new Result<>(code, msg, data);
    }

    // 失败：自定义状态码和消息（无数据）
    public static <T> Result<T> error(Integer code, String msg) {
        return new Result<>(code, msg, null);
    }

    // -------------------------- getter/setter --------------------------
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}