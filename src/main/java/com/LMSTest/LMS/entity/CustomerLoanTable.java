package com.LMSTest.LMS.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CustomerLoanTable {
    public static List<CustomerLoanEntity> CustomerLoan;
}
