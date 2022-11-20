package com.LMSTest.LMS.beans.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionsResponse {

    @JacksonXmlProperty(localName="ns2:TransactionsResponse")
    private CustomerTransactionResponse TransactionsResponse;
}
