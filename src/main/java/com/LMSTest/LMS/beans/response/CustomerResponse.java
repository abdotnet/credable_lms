package com.LMSTest.LMS.beans.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponse {
    @JsonProperty("customer")
    private CbsCustomerKycResponse customer;
    public String ns2;
    public String text;
}
