package com.LMSTest.LMS.beans.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@XmlRootElement(name= "ns2:transactions")
public class Transactions {
    @JacksonXmlProperty(localName = "ns2:accountNumber")
    public String accountNumber;
    @JacksonXmlProperty(localName = "ns2:alternativechanneltrnscrAmount")
    @JsonProperty("ns2:alternativechanneltrnscrAmount")
    public double alternativechanneltrnscrAmount;
    @JacksonXmlProperty(localName = "ns2:alternativechanneltrnscrNumber")
    public int alternativechanneltrnscrNumber;
    @JacksonXmlProperty(localName = "ns2:alternativechanneltrnsdebitAmount")
    public double alternativechanneltrnsdebitAmount;
    @JacksonXmlProperty(localName = "ns2:alternativechanneltrnsdebitNumber")
    public int alternativechanneltrnsdebitNumber;
    @JacksonXmlProperty(localName = "ns2:atmTransactionsNumber")
    public int atmTransactionsNumber;
    @JacksonXmlProperty(localName = "ns2:atmtransactionsAmount")
    public double atmtransactionsAmount;
    @JacksonXmlProperty(localName = "ns2:bouncedChequesDebitNumber")
    public int bouncedChequesDebitNumber;
    @JacksonXmlProperty(localName = "ns2:bouncedchequescreditNumber")
    public int bouncedchequescreditNumber;
    @JacksonXmlProperty(localName = "ns2:bouncedchequetransactionscrAmount")
    public double bouncedchequetransactionscrAmount;
    @JacksonXmlProperty(localName = "ns2:bouncedchequetransactionsdrAmount")
    public double bouncedchequetransactionsdrAmount;
    @JacksonXmlProperty(localName = "ns2:chequeDebitTransactionsAmount")
    public double chequeDebitTransactionsAmount;
    @JacksonXmlProperty(localName = "ns2:chequeDebitTransactionsNumber")
    public int chequeDebitTransactionsNumber;
    @JacksonXmlProperty(localName = "ns2:createdAt")
    public Date createdAt;
    @JacksonXmlProperty(localName = "ns2:createdDate")
    public Date createdDate;
    @JacksonXmlProperty(localName = "ns2:credittransactionsAmount")
    public double credittransactionsAmount;
    @JacksonXmlProperty(localName = "ns2:debitcardpostransactionsAmount")
    public double debitcardpostransactionsAmount;
    @JacksonXmlProperty(localName = "ns2:debitcardpostransactionsNumber")
    public int debitcardpostransactionsNumber;
    @JacksonXmlProperty(localName = "ns2:fincominglocaltransactioncrAmount")
    public double fincominglocaltransactioncrAmount;
    @JacksonXmlProperty(localName = "ns2:id")
    public int id;
    @JacksonXmlProperty(localName = "ns2:incominginternationaltrncrAmount")
    public double incominginternationaltrncrAmount;
    @JacksonXmlProperty(localName = "ns2:incominginternationaltrncrNumber")
    public int incominginternationaltrncrNumber;
    @JacksonXmlProperty(localName = "ns2:incominglocaltransactioncrNumber")
    public int incominglocaltransactioncrNumber;
    @JacksonXmlProperty(localName = "ns2:intrestAmount")
    public int intrestAmount;
    @JacksonXmlProperty(localName = "ns2:lastTransactionDate")
    public Date lastTransactionDate;
    @JacksonXmlProperty(localName = "ns2:lastTransactionValue")
    public int lastTransactionValue;
    @JacksonXmlProperty(localName = "ns2:maxAtmTransactions")
    public double maxAtmTransactions;
    @JacksonXmlProperty(localName = "ns2:maxMonthlyBebitTransactions")
    public double maxMonthlyBebitTransactions;
    @JacksonXmlProperty(localName = "ns2:maxalternativechanneltrnscr")
    public double maxalternativechanneltrnscr;
    @JacksonXmlProperty(localName = "ns2:maxalternativechanneltrnsdebit")
    public double maxalternativechanneltrnsdebit;
    @JacksonXmlProperty(localName = "ns2:maxbouncedchequetransactionscr")
    public double maxbouncedchequetransactionscr;
    @JacksonXmlProperty(localName = "ns2:maxchequedebittransactions")
    public double maxchequedebittransactions;
    @JacksonXmlProperty(localName = "ns2:maxdebitcardpostransactions")
    public double maxdebitcardpostransactions;
    @JacksonXmlProperty(localName = "ns2:maxincominginternationaltrncr")
    public double maxincominginternationaltrncr;
    @JacksonXmlProperty(localName = "ns2:maxincominglocaltransactioncr")
    public double maxincominglocaltransactioncr;
    @JacksonXmlProperty(localName = "ns2:maxmobilemoneycredittrn")
    public double maxmobilemoneycredittrn;
    @JacksonXmlProperty(localName = "ns2:maxmobilemoneydebittransaction")
    public double maxmobilemoneydebittransaction;
    @JacksonXmlProperty(localName = "ns2:maxmonthlycredittransactions")
    public double maxmonthlycredittransactions;
    @JacksonXmlProperty(localName = "ns2:maxoutgoinginttrndebit")
    public double maxoutgoinginttrndebit;
    @JacksonXmlProperty(localName = "ns2:maxoutgoinglocaltrndebit")
    public double maxoutgoinglocaltrndebit;
    @JacksonXmlProperty(localName = "ns2:maxoverthecounterwithdrawals")
    public double maxoverthecounterwithdrawals;
    @JacksonXmlProperty(localName = "ns2:minAtmTransactions")
    public double minAtmTransactions;
    @JacksonXmlProperty(localName = "ns2:minMonthlyDebitTransactions")
    public double minMonthlyDebitTransactions;
    @JacksonXmlProperty(localName = "ns2:minalternativechanneltrnscr")
    public double minalternativechanneltrnscr;
    @JacksonXmlProperty(localName = "ns2:minalternativechanneltrnsdebit")
    public double minalternativechanneltrnsdebit;
    @JacksonXmlProperty(localName = "ns2:minbouncedchequetransactionscr")
    public double minbouncedchequetransactionscr;
    @JacksonXmlProperty(localName = "ns2:minchequedebittransactions")
    public double minchequedebittransactions;
    @JacksonXmlProperty(localName = "ns2:mindebitcardpostransactions")
    public double mindebitcardpostransactions;
    @JacksonXmlProperty(localName = "ns2:minincominginternationaltrncr")
    public double minincominginternationaltrncr;
    @JacksonXmlProperty(localName = "ns2:minincominglocaltransactioncr")
    public double minincominglocaltransactioncr;
    @JacksonXmlProperty(localName = "ns2:minmobilemoneycredittrn")
    public double minmobilemoneycredittrn;
    @JacksonXmlProperty(localName = "ns2:minmobilemoneydebittransaction")
    public double minmobilemoneydebittransaction;
    @JacksonXmlProperty(localName = "ns2:minmonthlycredittransactions")
    public double minmonthlycredittransactions;
    @JacksonXmlProperty(localName = "ns2:minoutgoinginttrndebit")
    public double minoutgoinginttrndebit;
    @JacksonXmlProperty(localName = "ns2:minoutgoinglocaltrndebit")
    public double minoutgoinglocaltrndebit;
    @JacksonXmlProperty(localName = "ns2:minoverthecounterwithdrawals")
    public double minoverthecounterwithdrawals;
    @JacksonXmlProperty(localName = "ns2:mobilemoneycredittransactionAmount")
    public double mobilemoneycredittransactionAmount;
    @JacksonXmlProperty(localName = "ns2:mobilemoneycredittransactionNumber")
    public int mobilemoneycredittransactionNumber;
    @JacksonXmlProperty(localName = "ns2:mobilemoneydebittransactionAmount")
    public double mobilemoneydebittransactionAmount;
    @JacksonXmlProperty(localName = "ns2:mobilemoneydebittransactionNumber")
    public int mobilemoneydebittransactionNumber;
    @JacksonXmlProperty(localName = "ns2:monthlyBalance")
    public double monthlyBalance;
    @JacksonXmlProperty(localName = "ns2:monthlydebittransactionsAmount")
    public double monthlydebittransactionsAmount;
    @JacksonXmlProperty(localName = "ns2:outgoinginttransactiondebitAmount")
    public double outgoinginttransactiondebitAmount;
    @JacksonXmlProperty(localName = "ns2:outgoinginttrndebitNumber")
    public int outgoinginttrndebitNumber;
    @JacksonXmlProperty(localName = "ns2:outgoinglocaltransactiondebitAmount")
    public double outgoinglocaltransactiondebitAmount;
    @JacksonXmlProperty(localName = "ns2:outgoinglocaltransactiondebitNumber")
    public int outgoinglocaltransactiondebitNumber;
    @JacksonXmlProperty(localName = "ns2:overdraftLimit")
    public double overdraftLimit;
    @JacksonXmlProperty(localName = "ns2:overthecounterwithdrawalsAmount")
    public double overthecounterwithdrawalsAmount;
    @JacksonXmlProperty(localName = "ns2:overthecounterwithdrawalsNumber")
    public int overthecounterwithdrawalsNumber;
    @JacksonXmlProperty(localName = "ns2:transactionValue")
    public double transactionValue;
    @JacksonXmlProperty(localName = "ns2:updatedAt")
    public Date updatedAt;
}

