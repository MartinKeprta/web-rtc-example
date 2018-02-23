package com.testrtc.api;

import io.qameta.allure.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by Martin Keprta on 2/22/2018.
 * If run from testng tests are run in parallel.
 */
public class ApiTest {

    @BeforeClass
    public void setCredentials() {
        WebRtcApiConnector.setCredentials("9d578682-bc24-446f-905c-8d4061be8900", "https://api.testrtc.com/v1/");
    }


    @Test
    @Description("Waitr test")
    public void Waitr() throws InterruptedException {
        WebRtcApiConnector.launchTest("5a82eae077a5940011f64ed6");
    }

    @Test
    @Description("Twillio phone test")
    public void Twillio() throws InterruptedException {
        WebRtcApiConnector.launchTest("5a85dcf571aaec00113f091f");
    }


}
