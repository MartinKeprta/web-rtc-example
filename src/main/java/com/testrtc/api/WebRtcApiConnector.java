package com.testrtc.api;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.testrtc.api.testRun.TestRun;
import com.testrtc.api.testRunId.TestRunId;
import io.qameta.allure.Step;

import java.io.IOException;

/**
 * Created by Martin Keprta on 2/21/2018.
 */
public final class WebRtcApiConnector {
    private static String baseUrl;
    private static String apiKey;

    private WebRtcApiConnector() {
    }

    //Create API object + sets up constructor
    @Step("Setting credentials")
    public static void setCredentials(String key, String url) {
        //TODO : Post request which will verify if API is correctly setted up
        apiKey = key;
        baseUrl = url;
        Unirest.setDefaultHeader("apikey", apiKey);
        Unirest.setDefaultHeader("Content-Type", "application/json");
        Unirest.setObjectMapper(new com.mashape.unirest.http.ObjectMapper() {

            private ObjectMapper mapper = new ObjectMapper();

            @Override
            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return mapper.readValue(value, valueType);
                } catch (JsonParseException e) {
                    e.printStackTrace();
                } catch (JsonMappingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            public String writeValue(Object value) {
                try {
                    return mapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                return null;
            }
        });


    }

    @Step("Launched test")
    public static Boolean launchTest(String testId) throws InterruptedException {
        String testRunId = executeTest(testId).getBody().getTestRunId();
        Boolean testFinished = false;

        TestRun testRun;


        while (testFinished == false) {

            Thread.sleep(30000);
            String status = getTestStatus(testRunId).status;
            System.out.println("Test status :" + status);

            switch (status) {
                case "completed":
                    testFinished = true;
                    break;
                case "warnings":
                    testFinished = true;
                    break;
                case "failure":
                    testFinished = true;
                    break;

                default:
                    testFinished = false;

            }
        }


        return true;
    }

    @Step("Asking for test status")
    private static TestRun getTestStatus(String testRunId) {
        HttpResponse<TestRun> response = null;

        try {
            response = Unirest.get(baseUrl + "testruns/{testRunId}")
                    .routeParam("testRunId", testRunId)
                    .asObject(TestRun.class);
        } catch (UnirestException e) {
            e.printStackTrace();
        }


        return response.getBody();
    }

    //Executes test and return JSON with information
    @Step("Executing test")
    private static HttpResponse<TestRunId> executeTest(String testId) {
        System.out.println("Executing test" + testId);
        //Execute request
        HttpResponse<TestRunId> response = null;
        try {

            response = Unirest.post(baseUrl + "tests/{testId}/run").routeParam("testId", testId).asObject(TestRunId.class);
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        //Write output to console
        System.out.println(response.getBody().toString());
        //Check response is correct.Expected 200
        System.out.println("Success:Test run id" + response.getBody().getTestRunId());
        return response;

    }

}
