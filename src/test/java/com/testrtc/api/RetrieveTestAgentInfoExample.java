package com.testrtc.api;

import com.testrtc.api.testAgent.TestAgent;
import com.testrtc.api.testRun.TestRun;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by Martin Keprta on 2/22/2018.
 * Additional information about test agent
 */
public class RetrieveTestAgentInfoExample {

    @BeforeClass
    public void setCredentials() {
        WebRtcApiConnector.setCredentials("9d578682-bc24-446f-905c-8d4061be8900", "https://api.testrtc.com/v1/");
    }

    @Test(groups = "twillio")
    @Description("Twillio phone test")
    public void Twillio() throws InterruptedException {
        TestRun testResult = WebRtcApiConnector.launchTest("5a85dcf571aaec00113f091f");

        if (!testResult.status.equals("completed")) {
            System.out.println(testResult.toString());
            Assert.fail("Test failed. For more information visit:" + testResult.url);
        } else {
            System.out.println("----Test information-----");
            System.out.println(testResult.toString());
            System.out.println("----Agent information----");
            for (String testAgentId : testResult.agents) {
                System.out.println("Agent id:" + testAgentId);
                TestAgent agent = WebRtcApiConnector.getTestAgentInformation(testAgentId);
                System.out.println(agent.toString());
            }
        }
    }


}
