package com.watson.annababy.web;

import lombok.Data;

@Data
public class ApiResponse {
    boolean success;
    Object data;
    String error;


    public static ApiResponse buildSuccessResp(Object data) {
        ApiResponse rsp = new ApiResponse();
        rsp.success = true;
        rsp.data = data;
        return rsp;
    }

    public static ApiResponse buildErrorResp(String error) {
        ApiResponse rsp = new ApiResponse();
        rsp.success = false;
        rsp.error = error;
        return rsp;
    }

    public static ApiResponse buildErrorResp(String error, Throwable t) {
        ApiResponse rsp = new ApiResponse();
        rsp.success = false;
        rsp.error = String.format("%s. reason:%s", error, t.getMessage());
        return rsp;
    }
}
