package com.LMSTest.LMS.beans.request;

import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
public class LoanApplicationRequest {
    @NotNull
    private long customerNumber;
    private  double amount;
    private int tenor;

}
