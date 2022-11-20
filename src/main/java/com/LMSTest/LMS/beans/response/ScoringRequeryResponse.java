package com.LMSTest.LMS.beans.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ScoringRequeryResponse {
    private  int id;
    private  String customerNumber;
    private  int score;
    private int limitAmount;
    private String exclusion;
    private  String exclusionReason;

}
