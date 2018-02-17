import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.*;
import status.Status;

import java.net.HttpURLConnection;

/**
 * Created by Martin Keprta on 2/17/2018.
 */
public class StatusPageTest extends WebRtcApi {


    private String endPoint;

    @BeforeClass
    @Parameters({"service"})
    public void setEndPoint(@Optional("status-page") String service){
      this.endPoint=url+"/"+service;
    }

    @Test
    @Description("Get service status as JSON object")
    @Severity(SeverityLevel.NORMAL)
    @Feature("API")
    public void getServiceStatusJSON(){

       Status status;

        try {
            HttpResponse<Status> response = Unirest.get(endPoint)
                        .header("apikey",key).asObject(Status.class);

            Assert.assertEquals(response,HttpURLConnection.HTTP_ACCEPTED);

            status = response.getBody();

            Assert.assertNotEquals(status,null);

            } catch (UnirestException e) {
                e.printStackTrace();
            }

            //
    }

    @Test
    @Description("Check service status as raw HTTP response")
    @Severity(SeverityLevel.NORMAL)
    @Feature("API")
    public void getServiceStatusHTTP(){
        try {
            HttpResponse response = Unirest.get(endPoint)
                    .header("apikey",key)
                    .asString();

            Assert.assertEquals(response,HttpURLConnection.HTTP_ACCEPTED);


        } catch (UnirestException e) {
            e.printStackTrace();
        }


    }
}
