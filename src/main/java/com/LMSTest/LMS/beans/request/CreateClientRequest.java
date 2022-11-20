package com.LMSTest.LMS.beans.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateClientRequest {
    private String url;
    private String name;
    private String username;
    private String password;

}
