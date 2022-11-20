package com.LMSTest.LMS.beans.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
public class LoanApplicationResponse {
    private String loanNumber;
    private BigDecimal amountDisbursed;
    private  BigDecimal totalRepaymentAmount;
    private Date MaturityDate;

}
