package com.testrtc.api;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.HttpURLConnection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


/**
 * Created by Martin Keprta on 2/21/2018.
 */
public class TestInfo extends WebRtcApi {

    //Setting endpoint to required value
    @BeforeMethod
    @Parameters({"service"})
    @Description("Setting endpoint to correct value , if no value is present default is used")
    public void setEndPoint(@Optional("tests/{testId}") String service) {
        endPoint = url + service;
        System.out.println("Base url for this case is :[" + endPoint + "]");
    }

    //Data provider to supply tests to run to runner
    @DataProvider(name = "testList")
    public Iterator<Object[]> testList() {
        Set<Object[]> testList = new HashSet<Object[]>();
        testList.add(new Object[]{"5a8d0a043717290011aa36c0"});
        testList.add(new Object[]{"5a8d0a3769d4d60011dd8975"});
        return testList.iterator();
    }

    //Retrieves information about test and return Json object with it
    @Test(dataProvider = "testList")
    public void getTestInformation(String testId) throws UnirestException {
        //Execute request
        HttpResponse<JsonNode> response = Unirest.get(endPoint).routeParam("testId", testId).asJson();
        //Write output to console
        System.out.println(response.getBody().toString());
        //Check response is correct.Expected 200
        Assert.assertEquals(response.getStatus(), HttpURLConnection.HTTP_OK);


    }

}
