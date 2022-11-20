package com.LMSTest.LMS.config;

import lombok.Data;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration("lending")
@Data
public class LendingConfig {
    public static  String cbsUsername ="admin";
    public static String cbsPassword ="pwd123";
    public static String cbsKycBaseUrl ="https://kycapitest.credable.io";
    public static String cbsTrnxBaseUrl ="https://trxapitest.credable.io";
    public static String  scoringTestBaseUrl ="https://scoringtest.credable.io";

    public static String  LendingScoreTestUrl ="https://scoringtest.credable.io";
}
