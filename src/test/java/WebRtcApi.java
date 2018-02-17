import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.IOException;

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
    public void test1(@Optional("https://api.com.testrtc.com/v1/") String url, @Optional("9d578682-bc24-446f-905c-8d4061be8900") String key) {
        this.key = key;
        this.url = url;

        Unirest.setObjectMapper(new com.mashape.unirest.http.ObjectMapper() {

            private ObjectMapper mapper = new ObjectMapper();

            @Override
            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return mapper.readValue(value, valueType);
                } catch (JsonParseException e) {
                    e.printStackTrace();
                } catch (JsonMappingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            public String writeValue(Object value) {
                try {
                    return mapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                return null;
            }
        });


    }


}

