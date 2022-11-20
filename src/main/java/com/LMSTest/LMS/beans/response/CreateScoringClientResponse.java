package com.LMSTest.LMS.beans.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateScoringClientResponse {
    private int id;
    private String url;
    private String name;
    private String username;
    private String password;
    private String token;
}
