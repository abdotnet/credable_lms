package com.LMSTest.LMS.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class CustomerLoanEntity {

    private int  id;
    private  long customerNumber;
    private  String loanId;
    private Date SubmittionDate;
    private BigDecimal amount;
    private  BigDecimal interest;
    private  Date MaturityDate;
    private  boolean loanDisbursed;
    private  boolean isLoanClosed;
    private  String LoanStatus;

}
