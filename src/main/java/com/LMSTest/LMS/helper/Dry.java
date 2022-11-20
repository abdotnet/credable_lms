package com.LMSTest.LMS.helper;

import com.LMSTest.LMS.config.LendingConfig;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Base64;

public class Dry {




    public static String getAuthorization() {

        String auth = LendingConfig.cbsUsername + ":" + LendingConfig.cbsPassword;
        String authResponse = "Basic "+Base64.getEncoder().encodeToString(auth.getBytes());
        return authResponse;
    }
}
