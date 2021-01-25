package com.example.sale.model;


import com.example.sale.emun.ResultEnum;

/**
 * @ClassName: Result
 * @Description: 封装返回结果
 * @Author: PANLVZ
 * @Date: 2020-03-06
 */
public class Result<T> {

    /**
     * 状态 true成果  false失败
     */
    private boolean status;

    /**
     * 状态码
     */
    private String statusCode;

    /**
     * 返回信息
     */
    private String statusMessage;

    /**
     * 结果集
     */
    private T result;

    public static <T> Result success(T date) {
        return new Result(true, ResultEnum.OPERATION_SUCCESS.getStatusCode(),
                ResultEnum.OPERATION_SUCCESS.getMessage(), date);
    }

    public static <T> Result success(ResultEnum resultEnum, T date) {
        return new Result(true, resultEnum.getStatusCode(), resultEnum.getMessage(), date);
    }

    public static Result error(String statusCode, String message) {
        return new Result(false, statusCode, message, null);
    }

    public static Result error(ResultEnum resultEnum) {
        return error(resultEnum.getStatusCode(), resultEnum.getMessage());
    }

    public Result() {
    }

    public Result(boolean status, String statusCode, String statusMessage, T result) {
        this.status = status;
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.result = result;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
