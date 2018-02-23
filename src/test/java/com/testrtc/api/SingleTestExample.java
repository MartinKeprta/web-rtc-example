package com.testrtc.api;

import com.testrtc.api.testRun.TestRun;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by Martin Keprta on 2/22/2018.
 * If run from testng tests are run in parallel.
 */
public class SingleTestExample {

    @BeforeClass
    public void setCredentials() {
        WebRtcApiConnector.setCredentials("9d578682-bc24-446f-905c-8d4061be8900", "https://api.testrtc.com/v1/");
    }


    @Test(groups = "waitr")
    @Description("Waitr sample test")
    public void Waitr() throws InterruptedException {
        TestRun testResult = WebRtcApiConnector.launchTest("5a82eae077a5940011f64ed6");

        if (!testResult.status.equals("completed")) {
            System.out.println(testResult.toString());
            Assert.fail("Test failed. For more information visit:" + testResult.url);
        } else {
            System.out.println(testResult.toString());
        }

    }

    @Test(groups = "twillio")
    @Description("Twillio phone test")
    public void Twillio() throws InterruptedException {
        TestRun testResult = WebRtcApiConnector.launchTest("5a85dcf571aaec00113f091f");

        if (!testResult.status.equals("completed")) {
            System.out.println(testResult.toString());
            Assert.fail("Test failed. For more information visit:" + testResult.url);
        } else {
            System.out.println(testResult.toString());
        }
    }


}
