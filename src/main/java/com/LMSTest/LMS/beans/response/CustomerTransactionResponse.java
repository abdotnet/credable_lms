package com.LMSTest.LMS.beans.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerTransactionResponse {
    @JacksonXmlProperty(localName = "ns2:transactions")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Transactions> transactions;
    @JacksonXmlProperty(localName = "xmlns:ns2")
    private String ns2;
    @JacksonXmlProperty(localName = "text")
    private String text;


}
