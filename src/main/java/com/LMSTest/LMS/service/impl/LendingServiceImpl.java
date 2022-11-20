package com.LMSTest.LMS.service.impl;

import com.LMSTest.LMS.beans.ApiResponse;
import com.LMSTest.LMS.beans.request.CreateClientRequest;
import com.LMSTest.LMS.beans.request.CreateScoringClientRequest;
import com.LMSTest.LMS.beans.request.SubscriptionRequest;
import com.LMSTest.LMS.beans.request.TransactionRequest;
import com.LMSTest.LMS.beans.response.CbsCustomerKycResponse;
import com.LMSTest.LMS.beans.response.CreateClientResponse;
import com.LMSTest.LMS.beans.response.ScoringRequeryResponse;
import com.LMSTest.LMS.beans.response.Transactions;
import com.LMSTest.LMS.config.LendingConfig;
import com.LMSTest.LMS.controllers.LendingController;
import com.LMSTest.LMS.helper.AppConstants;
import com.LMSTest.LMS.service.LendingService;
import com.LMSTest.LMS.service.client.CbsHttpClient;
import com.LMSTest.LMS.service.client.ScoringHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LendingServiceImpl implements LendingService {

    Logger logger = LoggerFactory.getLogger(LendingServiceImpl.class);
    private CbsHttpClient cbsHttpClient;
    private ScoringHttpClient scoringHttpClient;

    @Autowired
    public LendingServiceImpl(CbsHttpClient cbsHttpClient, ScoringHttpClient scoringHttpClient) {
        this.cbsHttpClient = cbsHttpClient;
        this.scoringHttpClient = scoringHttpClient;
    }

    // get customer kyc data from core banking
    public ApiResponse<CbsCustomerKycResponse> getCustomerKycData(SubscriptionRequest subscriptionRequest) {

        try {
            if (subscriptionRequest == null)
                throw new Exception("customer number cannot be empty");

            CbsCustomerKycResponse cbsCustomerKycResponse = this.cbsHttpClient.getCustomerKycData(subscriptionRequest.getCustomerNumber());

            ApiResponse<CbsCustomerKycResponse> apiResponse = new ApiResponse<>();
            apiResponse.setData(cbsCustomerKycResponse);
            String status = cbsCustomerKycResponse != null ? AppConstants.SUCCESS_CODE : AppConstants.BAD_REQUEST_CODE;
            String message = cbsCustomerKycResponse != null ? AppConstants.SUCCESS : AppConstants.BAD_REQUEST;
            apiResponse.setStatus(status);
            apiResponse.setMessage(message);

            return apiResponse;
        } catch (Exception ex) {

        }
        return null;

    }

    public ApiResponse<List<Transactions>> getTransactions(TransactionRequest transactionRequest) {

        try {
            if (transactionRequest == null)
                throw new Exception("customer number cannot be empty");

            List<Transactions> lstCustomerTransactions = this.cbsHttpClient.getCustomerTransactionData(transactionRequest.getCustomerNumber());

            ApiResponse<List<Transactions>> apiResponse = new ApiResponse<>();
            apiResponse.setData(lstCustomerTransactions);
            String status = !lstCustomerTransactions.isEmpty() ? AppConstants.SUCCESS_CODE : AppConstants.BAD_REQUEST_CODE;
            String message = !lstCustomerTransactions.isEmpty() ? AppConstants.SUCCESS : AppConstants.BAD_REQUEST;
            apiResponse.setStatus(status);
            apiResponse.setMessage(message);

            return apiResponse;
        } catch (Exception ex) {

        }
        return null;

    }


    public ApiResponse<CreateClientResponse> createClient(long customerNumber) {
        CreateScoringClientRequest createClientRequest = CreateScoringClientRequest.builder()
                .name("Scoring_Service_" + customerNumber)
                .url(LendingConfig.LendingScoreTestUrl)
                .username(LendingConfig.cbsUsername)
                .password(LendingConfig.cbsPassword)
                .build();

        CreateClientResponse createClientResponse = this.scoringHttpClient.createScoringClient(createClientRequest);

        ApiResponse<CreateClientResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(createClientResponse);
        String status = createClientResponse != null ? AppConstants.SUCCESS_CODE : AppConstants.BAD_REQUEST_CODE;
        String message = createClientResponse != null ? AppConstants.SUCCESS : AppConstants.BAD_REQUEST;
        apiResponse.setStatus(status);
        apiResponse.setMessage(message);

        return apiResponse;

    }

    public ApiResponse<String> doInitiateScoring(long customerNumber) {
        ApiResponse<CreateClientResponse> createClientApiResponse = this.createClient(customerNumber);
        String clientToken = createClientApiResponse.getData().getToken();

        // store token on db .. cos of other time.

        // will come back to that

        String token = this.scoringHttpClient.doInitiateCustomerScore(customerNumber, clientToken);

        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setData(token);
        String status = token != null ? AppConstants.SUCCESS_CODE : AppConstants.BAD_REQUEST_CODE;
        String message = token != null ? AppConstants.SUCCESS : AppConstants.BAD_REQUEST;
        apiResponse.setStatus(status);
        apiResponse.setMessage(message);

        return apiResponse;

    }


    public ApiResponse<ScoringRequeryResponse> doScoringRequery( String token , long customerNumber) {
        ApiResponse<CreateClientResponse> createClientApiResponse = this.createClient(customerNumber);
        String clientToken = createClientApiResponse.getData().getToken();

        // store token on db .. cos of other time.

        // will come back to that

        ScoringRequeryResponse requeryResult = this.scoringHttpClient.doFinalCustomerScore(token, clientToken);

        ApiResponse<ScoringRequeryResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(requeryResult);
        String status = requeryResult != null ? AppConstants.SUCCESS_CODE : AppConstants.BAD_REQUEST_CODE;
        String message = requeryResult != null ? AppConstants.SUCCESS : AppConstants.BAD_REQUEST;
        apiResponse.setStatus(status);
        apiResponse.setMessage(message);

        return apiResponse;

    }
}
