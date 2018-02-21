package com.testrtc.api;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import org.testng.annotations.BeforeClass;
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
    public String url;
    public String key;
    public String endPoint;


    @BeforeClass
    @Parameters({"url","key"})
    public void test1(@Optional("https://api.testrtc.com/v1/") String url, @Optional("9d578682-bc24-446f-905c-8d4061be8900") String key) {

        this.url = url;

        Unirest.setDefaultHeader("apikey", key);
        Unirest.setDefaultHeader("Content-Type", "application/json");
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

