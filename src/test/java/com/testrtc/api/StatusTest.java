package com.testrtc.api;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.testrtc.api.get.status.Status;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;


/**
 * Created by Martin Keprta on 2/17/2018.
 */
public class StatusTest extends WebRtcApi {


    @BeforeMethod
    @Parameters({"service"})
    @Description("Setting endpoint to correct value , if no value is present default is used")
    public void setEndPoint(@Optional("status-page") String service) {
        this.endPoint = url + service;
        System.out.println("Base url for this case is :[" + endPoint + "]");
    }

    @Test
    @Description("Get service com.testrtc as JSON object")
    @Severity(SeverityLevel.NORMAL)
    @Feature("API")
    public void getServiceStatusJSON(){

        try {
            HttpResponse<Status> response = Unirest.get(endPoint)
                    .asObject(Status.class);

            Assert.assertEquals(response.getStatus(), HttpURLConnection.HTTP_OK);

            Status status = response.getBody();
            System.out.println(status.toString());


        } catch (UnirestException e) {
            e.printStackTrace();
        }

    }

    @Test
    @Description("Check service com.testrtc as raw HTTP response")
    @Severity(SeverityLevel.NORMAL)
    @Feature("API")
    public void getServiceStatusHTTP(){
        try {
            HttpResponse response = Unirest.get(endPoint)
                    .asJson();

            Assert.assertEquals(response.getStatus(), HttpURLConnection.HTTP_OK);


        } catch (UnirestException e) {
            e.printStackTrace();
        }


    }
}
