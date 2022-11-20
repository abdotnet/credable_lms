package com.LMSTest.LMS.beans.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerTransactionEnvelop {
    @JacksonXmlProperty(localName = "SOAP-ENV:Header")
    public Object Header;
    @JacksonXmlProperty(localName="SOAP-ENV:Body")
    public TransactionsResponse Body;
    @JacksonXmlProperty(localName = "xmlns:SOAP-ENV")
    public String SOAPENV;
    @JacksonXmlProperty(localName = "xmlns:text")
    public String text;
}
