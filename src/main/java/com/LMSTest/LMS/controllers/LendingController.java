package com.LMSTest.LMS.controllers;

import com.LMSTest.LMS.beans.ApiResponse;
import com.LMSTest.LMS.beans.request.LoanApplicationRequest;
import com.LMSTest.LMS.beans.request.SubscriptionRequest;
import com.LMSTest.LMS.beans.request.TransactionRequest;
import com.LMSTest.LMS.beans.response.*;
import com.LMSTest.LMS.entity.CustomerLoanEntity;
import com.LMSTest.LMS.entity.CustomerLoanTable;
import com.LMSTest.LMS.helper.AppConstants;
import com.LMSTest.LMS.service.LendingService;
import com.ctc.wstx.stax.WstxInputFactory;
import com.ctc.wstx.stax.WstxOutputFactory;
import com.fasterxml.jackson.dataformat.xml.XmlFactory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.stream.XMLInputFactory;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/lending")
public class LendingController {

    Logger logger = LoggerFactory.getLogger(LendingController.class);
    @Autowired
    private LendingService lendingService;

    @Autowired
    public LendingController(LendingService lendingService) {
        this.lendingService = lendingService;
    }

    @PostMapping("/transactions")
    public ResponseEntity<ApiResponse<List<Transactions>>> getTransaction(@RequestBody TransactionRequest transactionRequest) {

        ApiResponse<List<Transactions>> lstCustomerTransactionResponse = this.lendingService.getTransactions(transactionRequest);

        if (lstCustomerTransactionResponse == null) {
            lstCustomerTransactionResponse = new ApiResponse<List<Transactions>>();
            lstCustomerTransactionResponse.setMessage(AppConstants.INTERNAL_SERVER_ERROR);
            lstCustomerTransactionResponse.setStatus(AppConstants.INTERNAL_SERVER_ERROR_CODE);
            return ResponseEntity.status(500).body(lstCustomerTransactionResponse);
        }
        logger.info(lstCustomerTransactionResponse.toString());

        if (lstCustomerTransactionResponse.getStatus() == AppConstants.SUCCESS_CODE)
            return ResponseEntity.ok(lstCustomerTransactionResponse);
        else if (lstCustomerTransactionResponse.getStatus() == AppConstants.BAD_REQUEST_CODE) {
            return ResponseEntity.badRequest().body(lstCustomerTransactionResponse);
        } else if (lstCustomerTransactionResponse.getStatus() == AppConstants.UNAUTHORIZED_CODE) {
            return ResponseEntity.status(401).body(lstCustomerTransactionResponse);
        }
        return ResponseEntity.status(500).body(lstCustomerTransactionResponse);

    }

    @PostMapping("/subscription")
    public ResponseEntity<ApiResponse<CbsCustomerKycResponse>> subscription(@RequestBody SubscriptionRequest subscriptionRequest) {


        if (subscriptionRequest == null) {
            ApiResponse<CbsCustomerKycResponse> apiResponseError = new ApiResponse<CbsCustomerKycResponse>();
            apiResponseError.setStatus(AppConstants.BAD_REQUEST_CODE);
            apiResponseError.setMessage(AppConstants.BAD_REQUEST);
            return ResponseEntity.badRequest().body(apiResponseError);
        }


        ApiResponse<CbsCustomerKycResponse> cbsCustomerKycResponse = this.lendingService.getCustomerKycData(subscriptionRequest);

        logger.info(cbsCustomerKycResponse.toString());

        if (cbsCustomerKycResponse.getStatus() == AppConstants.SUCCESS_CODE)
            return ResponseEntity.ok(cbsCustomerKycResponse);
        else if (cbsCustomerKycResponse.getStatus() == AppConstants.BAD_REQUEST_CODE) {
            return ResponseEntity.badRequest().body(cbsCustomerKycResponse);
        } else if (cbsCustomerKycResponse.getStatus() == AppConstants.UNAUTHORIZED_CODE) {
            return ResponseEntity.status(401).body(cbsCustomerKycResponse);
        }
        return ResponseEntity.status(500).body(cbsCustomerKycResponse);

    }

    @PostMapping("loanRequest")
    public ResponseEntity<ApiResponse<LoanApplicationResponse>> loanApplicationRequest(@RequestBody LoanApplicationRequest loanApplicationRequest) {

        try {

            logger.info("Request: {}",loanApplicationRequest);
            // check that the customer does not have a running loan locally
            List<CustomerLoanEntity> customerEntity = CustomerLoanTable.CustomerLoan;
            boolean customerLoanExist = customerEntity.stream().anyMatch(c -> c.getCustomerNumber() == loanApplicationRequest.getCustomerNumber());

            if (customerLoanExist) {
                ApiResponse<LoanApplicationResponse> apiResponseError = new ApiResponse<>();
                apiResponseError.setStatus(AppConstants.BAD_REQUEST_CODE);
                apiResponseError.setMessage("Customer has a running loan. Kindly repay to be qualified for a new loan");
                logger.info(apiResponseError.toString());
                return ResponseEntity.badRequest().body(apiResponseError);
            }

            // log the transaction of  table

            // check if the amount falls between the min and max amount

            // create client on score engine if client does not exist on our local before to get token

          ApiResponse<CreateClientResponse>  apiCreateClientResponse =  this.lendingService.
                  createClient(loanApplicationRequest.getCustomerNumber());

            logger.info(apiCreateClientResponse.toString());

            // Submit request to check customer  score credit engine for the customer limit
            ApiResponse<String>  apiDoInitiateScoringResponse =  this.lendingService
                    .doInitiateScoring(loanApplicationRequest.getCustomerNumber());

            logger.info(apiDoInitiateScoringResponse.toString());

            // Submit request to check customer  score credit engine for the customer limit
            ApiResponse<ScoringRequeryResponse>  requeryEnginer =  this.lendingService
                    .doScoringRequery(apiDoInitiateScoringResponse.getData() , loanApplicationRequest.getCustomerNumber());

            logger.info(requeryEnginer.toString());

            // The loan booking process will be async as customer will be notified of the final status
            ApiResponse<LoanApplicationResponse> apiResponseFinal = new ApiResponse<>();
            apiResponseFinal.setStatus(AppConstants.PENDING_CODE);
            apiResponseFinal.setMessage(AppConstants.PENDING);
            logger.info(apiResponseFinal.toString());
            return ResponseEntity.status(202).body(apiResponseFinal);

        } catch (Exception ex) {
            ApiResponse<LoanApplicationResponse> apiResponseError = new ApiResponse<>();
            apiResponseError.setStatus(AppConstants.INTERNAL_SERVER_ERROR_CODE);
            apiResponseError.setMessage(AppConstants.INTERNAL_SERVER_ERROR);
            logger.error(ex.getMessage());
            return ResponseEntity.badRequest().body(apiResponseError);
        }

    }

    // Check loan status
    @PostMapping("loanStatus/{customerId}")
    public ResponseEntity<ApiResponse<LoanStatusResponse>> loanStatus(@PathVariable("customerId") long customerId) {
        try {
            // check that the customer does not have a running loan locally
            List<CustomerLoanEntity> customerEntity = CustomerLoanTable.CustomerLoan;
            boolean customerLoanExist = customerEntity.stream().anyMatch(c -> c.getCustomerNumber() == customerId);

            if (!customerLoanExist) {
                ApiResponse<LoanStatusResponse> apiResponseError = new ApiResponse<>();
                apiResponseError.setStatus(AppConstants.BAD_REQUEST_CODE);
                apiResponseError.setMessage("Customer loan not found.");
                return ResponseEntity.badRequest().body(apiResponseError);
            }

          // build status response
           Optional<CustomerLoanEntity> customerLoanEntity =customerEntity.stream().filter(c -> c.getCustomerNumber() == customerId).findFirst();

            LoanStatusResponse loanStatusResponse = LoanStatusResponse.builder()
                    .loanStatus(customerLoanEntity.get().getLoanStatus())
                    .loanId(customerLoanEntity.get().getLoanId())
                    .amountDisbursed(customerLoanEntity.get().getAmount())
                    .build();

            ApiResponse<LoanStatusResponse> apiResponse = new ApiResponse<>();
            apiResponse.setData(loanStatusResponse);
            apiResponse.setStatus(AppConstants.SUCCESS_CODE);
            apiResponse.setMessage(AppConstants.SUCCESS);

            return ResponseEntity.ok(apiResponse);
        } catch (Exception ex) {
            ApiResponse<LoanStatusResponse> apiResponseError = new ApiResponse<>();
            apiResponseError.setStatus(AppConstants.INTERNAL_SERVER_ERROR_CODE);
            apiResponseError.setMessage(AppConstants.INTERNAL_SERVER_ERROR);
            return ResponseEntity.badRequest().body(apiResponseError);
        }
    }

    @PostMapping("createClient/{customerId}")
    public ResponseEntity<ApiResponse<CreateClientResponse>> createNewClient(@RequestBody SubscriptionRequest createClient) {

        ApiResponse<CreateClientResponse> apiResponse = this.lendingService.createClient(createClient.getCustomerNumber());

        if (apiResponse.getStatus() == AppConstants.SUCCESS_CODE)
            return ResponseEntity.ok(apiResponse);
        else if (apiResponse.getStatus() == AppConstants.BAD_REQUEST_CODE) {
            return ResponseEntity.badRequest().body(apiResponse);
        } else if (apiResponse.getStatus() == AppConstants.UNAUTHORIZED_CODE) {
            return ResponseEntity.status(401).body(apiResponse);
        }
        return ResponseEntity.status(500).body(apiResponse);
    }
}
