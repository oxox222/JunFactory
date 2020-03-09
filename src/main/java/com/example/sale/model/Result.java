package com.example.sale.model;

/**
 * @ClassName: Result
 * @Description: TODO
 * @Author: PANLVZ
 * @Date: 2020-03-06
 */
public class Result<T> {

    private boolean status = true;

    private String statusCode = "200";

    private String statusMessage;

    private T result;

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
