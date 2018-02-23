package com.testrtc.api;

import com.testrtc.api.testRun.TestRun;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Martin Keprta on 2/22/2018.
 * If run from testng tests are run in parallel.
 */
public class ParametrizedTestExample {

    //Data provider to supply tests to run to runner
    @DataProvider(name = "testRunList")
    public Iterator<Object[]> testList() {
        Set<Object[]> testList = new HashSet<Object[]>();
        testList.add(new Object[]{"5a85dcf571aaec00113f091f"});
        testList.add(new Object[]{"5a82eae077a5940011f64ed6"});
        return testList.iterator();
    }

    @BeforeClass
    public void setCredentials() {
        WebRtcApiConnector.setCredentials("9d578682-bc24-446f-905c-8d4061be8900", "https://api.testrtc.com/v1/");
    }


    @Test(groups = "waitr")
    @Description("Waitr sample test")
    public void Waitr() throws InterruptedException {
        TestRun testResult = WebRtcApiConnector.launchTest("5a82eae077a5940011f64ed6");

        if (!testResult.status.equals("completed")) {
            Assert.fail("Test failed. For more information visit:" + testResult.url);
        }

    }

    @Test(groups = "twillio")
    @Description("Twillio phone test")
    public void Twillio() throws InterruptedException {
        TestRun testResult = WebRtcApiConnector.launchTest("5a85dcf571aaec00113f091f");


        if (!testResult.status.equals("completed")) {
            Assert.fail("Test failed. For more information visit:" + testResult.url);
        }
    }


}