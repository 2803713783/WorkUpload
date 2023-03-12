package com.siyi.project.common.dto;

/**
 * @author siyi
 * @version 1.0
 * @date 2023/3/12 8:48
 */
public class Result<T> {

    private int code;
    private String message;
    private T data;

    public Result() {}

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setResult(T documents) {
        this.data = documents;
    }
}
