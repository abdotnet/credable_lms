package com.LMSTest.LMS.beans;

import lombok.Data;

@Data
public class ApiResponse<T> {
    private  T Data;
    private String status;
    private  String message;

}
