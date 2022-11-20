package com.LMSTest.LMS.beans.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Root {
    public Transactions transactions;
    public String ns2;
    public String text;
}
