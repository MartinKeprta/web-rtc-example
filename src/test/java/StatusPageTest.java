import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.*;

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
    @Description("Check service status with correct API key.Expected HTTP response is 200")
    @Severity(SeverityLevel.NORMAL)
    @Feature("API")
    public void getServiceStatus(){
        try {
            HttpResponse response = Unirest.get(endPoint)
                        .header("apikey",key)
                        .asString();

            Assert.assertEquals(response,HttpURLConnection.HTTP_ACCEPTED);

            } catch (UnirestException e) {
                e.printStackTrace();
            }



    }

    @Test
    @Description("Check service status with incorrect API.Expected HTTP response is 401")
    @Severity(SeverityLevel.NORMAL)
    @Feature("API")
    public void getServiceStatusInvalidApiKey(){
        try {
            HttpResponse response = Unirest.get(endPoint)
                    .header("apikey","incorrect value")
                    .asString();

            Assert.assertEquals(response,HttpURLConnection.HTTP_UNAUTHORIZED);

        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }
}
