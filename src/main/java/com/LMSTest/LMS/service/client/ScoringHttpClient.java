package com.LMSTest.LMS.service.client;


import com.LMSTest.LMS.beans.request.CreateScoringClientRequest;
import com.LMSTest.LMS.beans.response.CreateClientResponse;
import com.LMSTest.LMS.beans.response.CustomerTransactionEnvelop;
import com.LMSTest.LMS.beans.response.ScoringRequeryResponse;
import com.LMSTest.LMS.beans.response.Transactions;
import com.LMSTest.LMS.config.LendingConfig;
import com.LMSTest.LMS.helper.Dry;
import com.LMSTest.LMS.helper.ServiceLocatorPath;
import com.ctc.wstx.stax.WstxInputFactory;
import com.ctc.wstx.stax.WstxOutputFactory;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlFactory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.xml.stream.XMLInputFactory;
import java.util.List;

@Service
public class ScoringHttpClient {

    Logger logger = LoggerFactory.getLogger(ScoringHttpClient.class);

    public CreateClientResponse createScoringClient(CreateScoringClientRequest createScoringClientRequest) {

        try {
            final String url = LendingConfig.scoringTestBaseUrl + ServiceLocatorPath.ScoringClientCreation;
            logger.info("Url :" + url);

            try (final CloseableHttpClient httpClient = HttpClients.createDefault()) {
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonRequest = objectMapper.writeValueAsString(createScoringClientRequest);
                StringEntity strEntity = new StringEntity(jsonRequest, "application/json", "UTF-8");
                // URL of request
                HttpPost httpPost = new HttpPost(url);
                httpPost.setEntity(strEntity);
                final CloseableHttpResponse response = httpClient.execute(httpPost);
                // Execute request
                HttpEntity respEntity = response.getEntity();
                logger.info("final result :" + respEntity.toString());
                if (respEntity != null) {
                    String result = EntityUtils.toString(response.getEntity());
                    logger.info("final result  :" + result);

                    CreateClientResponse createClientResponse = objectMapper.readValue(result, CreateClientResponse.class);

                    return createClientResponse;

                }
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                e.getMessage();
                e.getLocalizedMessage();
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }


        return null;
    }


    public String doInitiateCustomerScore(long customerNumber, String clientToken) {

        try {
            final String url = LendingConfig.scoringTestBaseUrl + ServiceLocatorPath.ScoringInitiateQuery +"/" + customerNumber;
            logger.info("Url :" + url);

            try (final CloseableHttpClient httpClient = HttpClients.createDefault()) {
                ObjectMapper objectMapper = new ObjectMapper();
                StringEntity strEntity = new StringEntity("", "application/json", "UTF-8");
                // URL of request
                HttpGet httpGet = new HttpGet(url);
                httpGet.addHeader("client-token",clientToken);
                final CloseableHttpResponse response = httpClient.execute(httpGet);
                // Execute request
                HttpEntity respEntity = response.getEntity();
                logger.info("final result :" + respEntity.toString());

                if (respEntity != null) {
                    String result = EntityUtils.toString(response.getEntity());
                    logger.info("final result  :" + result);
                    return result;
                }
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                e.getMessage();
                e.getLocalizedMessage();
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public ScoringRequeryResponse doFinalCustomerScore(String token , String clientToken) {

        try {
            final String url = LendingConfig.scoringTestBaseUrl + ServiceLocatorPath.ScoringTokenQuery +"/" + token;
            logger.info("Url :" + url);

            try (final CloseableHttpClient httpClient = HttpClients.createDefault()) {
                ObjectMapper objectMapper = new ObjectMapper();
                StringEntity strEntity = new StringEntity("", "application/json", "UTF-8");
                // URL of request
                HttpGet httpGet = new HttpGet(url);
                httpGet.addHeader("client-token",clientToken);
                final CloseableHttpResponse response = httpClient.execute(httpGet);
                // Execute request
                HttpEntity respEntity = response.getEntity();
                logger.info("final result :" + respEntity.toString());

                if (respEntity != null) {
                    String result = EntityUtils.toString(response.getEntity());
                    logger.info("final result  :" + result);

                    ScoringRequeryResponse scoringRequeryResponse = new ObjectMapper().readValue(result,
                            ScoringRequeryResponse.class);
                    return scoringRequeryResponse;
                }
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                e.getMessage();
                e.getLocalizedMessage();
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
