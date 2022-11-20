package com.LMSTest.LMS.beans.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerKycResponseEnvelope {
    public Object Header;
    @JsonProperty("Body")
    public CustomerResponseBody Body;
    @JsonProperty("SOAP-ENV")
    public String SOAPENV;
    public String text;
}
