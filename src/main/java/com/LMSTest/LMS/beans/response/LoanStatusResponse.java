package com.LMSTest.LMS.beans.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class LoanStatusResponse {
    private  String loanId;
    private BigDecimal amountDisbursed;
    private long customerNumber;
    private String loanStatus;
}
