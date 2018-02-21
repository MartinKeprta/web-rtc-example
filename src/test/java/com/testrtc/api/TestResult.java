package com.testrtc.api;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.HttpURLConnection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


/**
 * Created by Martin Keprta on 2/21/2018.
 */
public class TestResult extends WebRtcApi {

    //Setting endpoint to required value
    @BeforeMethod
    @Parameters({"service"})
    @Description("Setting endpoint to correct value , if no value is present default is used")
    public void setEndPoint(@Optional("test_runs/{testId}") String service) {
        endPoint = "https://app.testrtc.com/api/" + service;
        System.out.println("Base url for this case is :[" + endPoint + "]");
    }

    //Data provider to supply tests to run to runner
    @DataProvider(name = "testRunList")
    public Iterator<Object[]> testList() {
        Set<Object[]> testList = new HashSet<Object[]>();
        testList.add(new Object[]{"5a8d38be3717290011aa36e3"});
        testList.add(new Object[]{"5a8d38bf3717290011aa36e4"});
        return testList.iterator();
    }

    //Takes data from provider and launches test
    @Test(dataProvider = "testRunList")
    @Description("Execute tests by providing test ids in parameter")
    @Severity(SeverityLevel.NORMAL)
    @Feature("API")
    public void runTests(String testId) throws UnirestException {
        //Execute request
        HttpResponse<String> response = Unirest.get(endPoint).routeParam("testId", testId).asString();
        //Write output to console
        System.out.println(response.getBody().toString());
        //Check response is correct.Expected 200
        Assert.assertEquals(response.getStatus(), HttpURLConnection.HTTP_OK);
    }


}
