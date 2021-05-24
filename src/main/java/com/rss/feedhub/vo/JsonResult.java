package com.rss.feedhub.vo;

/**
 * @Author: ZhangKaijie
 * @Date: 2021/4/4 13:04
 * @Description:
 */
public class JsonResult<T> {
    private int error_code;
    private String msg;
    private T data;

    public JsonResult() {
        this.error_code = 0;
        this.msg = "success";
    }

    public JsonResult(T data) {
        this.data = data;
        this.error_code = 0;
        this.msg = "success";
    }

    public JsonResult(int error_code, String msg) {
        this.error_code = error_code;
        if(msg.equals("")){
            this.msg = getErrorMessage(error_code);
        }else{
            this.msg = msg;
        }
    }

    private String getErrorMessage(int error_code){
        switch (error_code){
            case -1:return "system error";
            case 1001:return "用户已存在";
            case 1002:return "用户不存在";
            case 1003:return "密码错误";
        }
        return "system error";
    }
    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
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
