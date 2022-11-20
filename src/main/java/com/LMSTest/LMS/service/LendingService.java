package com.LMSTest.LMS.service;

import com.LMSTest.LMS.beans.ApiResponse;
import com.LMSTest.LMS.beans.request.SubscriptionRequest;
import com.LMSTest.LMS.beans.request.TransactionRequest;
import com.LMSTest.LMS.beans.response.CbsCustomerKycResponse;
import com.LMSTest.LMS.beans.response.CreateClientResponse;
import com.LMSTest.LMS.beans.response.ScoringRequeryResponse;
import com.LMSTest.LMS.beans.response.Transactions;

import java.util.List;


public interface LendingService  {
    ApiResponse<CbsCustomerKycResponse> getCustomerKycData(SubscriptionRequest subscriptionRequest);
    ApiResponse<List<Transactions>> getTransactions(TransactionRequest transactionRequest);
    ApiResponse<CreateClientResponse> createClient(long customerNumber);
    ApiResponse<String> doInitiateScoring(long customerNumber);
    ApiResponse<ScoringRequeryResponse> doScoringRequery(String token , long customerNumber);
}
