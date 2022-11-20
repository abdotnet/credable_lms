package com.LMSTest.LMS.beans.response;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponseBody {

    @JsonProperty("CustomerResponse")
    public CustomerResponse CustomerResponse;
}
