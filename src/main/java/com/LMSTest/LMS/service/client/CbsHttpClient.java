package com.LMSTest.LMS.service.client;

import com.LMSTest.LMS.beans.response.*;
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
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.XMLInputFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class CbsHttpClient {

    Logger logger = LoggerFactory.getLogger(CbsHttpClient.class);
    private LendingConfig lendingConfig;
//    public static final MediaType JSON
//            = MediaType.parseMediaType(new StringReader("application/json; charset=utf-8"));

    public CbsHttpClient(LendingConfig lendingConfig) {
        this.lendingConfig = lendingConfig;

    }

    public CbsCustomerKycResponse getCustomerKycData(long customerNumber) {

        try {
            final String url = LendingConfig.cbsKycBaseUrl + ServiceLocatorPath.CBSCustomerKyc;
            logger.info("Url :" + url);

            String customerKycSoapRequest = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                    "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                    "    <soap:Header>\n" +
                    "        <wsse:Security xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\" wsse:mustUnderstand=\"1\">\n" +
                    "            <wsse:UsernameToken>\n" +
                    "                <wsse:Username>" + LendingConfig.cbsUsername + "</wsse:Username>\n" +
                    "                <wsse:Password>" + LendingConfig.cbsPassword + "</wsse:Password>\n" +
                    "            </wsse:UsernameToken>\n" +
                    "        </wsse:Security>\n" +
                    "    </soap:Header>\n" +
                    "    <soap:Body>\n" +
                    "        <CustomerRequest xmlns=\"http://credable.io/cbs/customer\">\n" +
                    "            <customerNumber>" + customerNumber + "</customerNumber>\n" +
                    "        </CustomerRequest>\n" +
                    "    </soap:Body>\n" +
                    "</soap:Envelope>";

            logger.info("Request payload :" + customerKycSoapRequest);

            logger.info("Auth :" + Dry.getAuthorization());
            try (final CloseableHttpClient httpClient = HttpClients.createDefault()) {
                StringEntity strEntity = new StringEntity(customerKycSoapRequest, "text/xml", "UTF-8");
                // URL of request
                HttpPost httpPost = new HttpPost(url);
                httpPost.setHeader("Authorization", Dry.getAuthorization());
                httpPost.setEntity(strEntity);
                final CloseableHttpResponse response = httpClient.execute(httpPost);
                // Execute request
                HttpEntity respEntity = response.getEntity();
                logger.info("final result :" + respEntity.toString());
                if (respEntity != null) {
                    String result = EntityUtils.toString(response.getEntity());
                    logger.info("final result 2 :" + result);

                    XmlMapper xmlMapper = new XmlMapper();
                    CustomerKycResponseEnvelope customerKycResponseEnvelope = xmlMapper.readValue(result, CustomerKycResponseEnvelope.class);
                    return customerKycResponseEnvelope.Body.CustomerResponse.getCustomer();
                    // return this.getRandomCustomerKycData(customerNumber);
                }
                return null;
            } catch (Exception e) {
                e.printStackTrace();
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }


        return null;
    }


    public List<Transactions> getCustomerTransactionData(long customerNumber) {

        try {
            final String url = LendingConfig.cbsTrnxBaseUrl + ServiceLocatorPath.CBSTransactionData;
            logger.info("Url :" + url);

            String customerKycSoapRequest = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                    "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                    "    <soap:Header>\n" +
                    "        <wsse:Security xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\" wsse:mustUnderstand=\"1\">\n" +
                    "            <wsse:UsernameToken>\n" +
                    "                <wsse:Username>" + LendingConfig.cbsUsername + "</wsse:Username>\n" +
                    "                <wsse:Password>" + LendingConfig.cbsPassword + "</wsse:Password>\n" +
                    "            </wsse:UsernameToken>\n" +
                    "        </wsse:Security>\n" +
                    "    </soap:Header>\n" +
                    "    <soap:Body>\n" +
                    "        <TransactionsRequest xmlns=\"http://credable.io/cbs/transaction\">\n" +
                    "            <customerNumber>" + customerNumber + "</customerNumber>\n" +
                    "        </TransactionsRequest>\n" +
                    "    </soap:Body>\n" +
                    "</soap:Envelope>";

            logger.info("Request payload :" + customerKycSoapRequest);

            logger.info("Auth :" + Dry.getAuthorization());
            try (final CloseableHttpClient httpClient = HttpClients.createDefault()) {
                StringEntity strEntity = new StringEntity(customerKycSoapRequest, "text/xml", "UTF-8");
                // URL of request
                HttpPost httpPost = new HttpPost(url);
                httpPost.setHeader("Authorization", Dry.getAuthorization());
                httpPost.setEntity(strEntity);
                final CloseableHttpResponse response = httpClient.execute(httpPost);
                // Execute request
                HttpEntity respEntity = response.getEntity();
                logger.info("final result :" + respEntity.toString());
                if (respEntity != null) {
                    String result = EntityUtils.toString(response.getEntity());
                    logger.info("final result 2 :" + result);

                    XMLInputFactory input = new WstxInputFactory();
                    input.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, Boolean.FALSE);
                    input.setProperty(XMLInputFactory.IS_VALIDATING, Boolean.FALSE);
                    XmlMapper xmlMapper = new XmlMapper(new XmlFactory(input, new WstxOutputFactory()));
                    xmlMapper.disable(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY);

                    // CustomerTransactionEnvelop customerTransactionEnvelop =  new ObjectMapper().readValue(result, CustomerTransactionEnvelop.class);
                    CustomerTransactionEnvelop customerTransactionEnvelop = xmlMapper.readValue(result, CustomerTransactionEnvelop.class);
                    logger.info("final result 3 : {}", customerTransactionEnvelop);
                    var responseVal = customerTransactionEnvelop.Body.getTransactionsResponse().getTransactions();
                    logger.info("final result 2 : {}", responseVal);

                    return responseVal;

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
