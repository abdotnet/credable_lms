package com.LMSTest.LMS.beans.request;

import lombok.Builder;
import lombok.Data;
import org.springframework.context.annotation.Bean;

@Data
@Builder
public class CreateScoringClientRequest {
    private String url;
    private String name;
    private String username;
    private String password;
}
