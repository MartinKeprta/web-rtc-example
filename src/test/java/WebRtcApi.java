import com.mashape.unirest.http.HttpResponse;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

/**
 * Created by Martin Keprta on 2/17/2018.
 * This class sets up constant values for key and url
 * To launch Your tests You need to insert Your own api key
 * How to find API key : <URL>something</URL>
 */


public class WebRtcApi {
    protected String url;
    protected String key;



    @BeforeSuite
    @Parameters({"url","key"})
    public void test1(@Optional("https://api.testrtc.com/v1/") String url,@Optional("9d578682-bc24-446f-905c-8d4061be8900") String key){
        this.key=key;
        this.url=url;

    }

    @Step
    public void checkResponse(HttpResponse response,int expectedStatus){
        Assert.assertEquals(response.getStatus(),expectedStatus);
    }


}
