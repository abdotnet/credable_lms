package com.LMSTest.LMS.beans.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class CreateClientResponse {
    private String id;
    private String url;
    private String token;
    private String username;
    private String password;
    private String name;
    private Date createdAt;
    private Date updatedAt;
}
