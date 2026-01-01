package com.maxim.application;

/*
* 给前端的响应结果 -> 泛型类
* */
public class Response <T>{
    private T data;
    private boolean success;
    private String errorMsg;

    // 返回成功信息
    public static<K> Response<K> newSuccess(K data){
        Response res = new Response<>();
        res.setData(data);
        res.setSuccess(true);
        return res;
    }

    // 返回空信息
    public static<K> Response<K> newEmpty(){
        Response res = new Response<>();
        res.setSuccess(true);
        return res;
    }

    // 返回失败信息
    public static Response<Void> newError(String errorMsg){
        Response res = new Response<>();
        res.setErrorMsg(errorMsg);
        res.setSuccess(false);
        return res;
    }

    public Response(){};

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
