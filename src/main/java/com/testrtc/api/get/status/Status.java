package com.testrtc.api.get.status;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by Martin Keprta on 2/17/2018.
 */

@Getter
@Setter
@ToString
public class Status {
    private Role role;
    private Config config;
    private String queueStatus;
    private String queueCount;
    private String dbStatus;
    private String dbCount;
    private String status;

    public Status(
            @JsonProperty("role") Role role,
            @JsonProperty("config") Config config,
            @JsonProperty("queueStatus") String queueStatus,
            @JsonProperty("queueCount") String queueCount,
            @JsonProperty("dbStatus") String dbStatus,
            @JsonProperty("dbCount") String dbCount,
            @JsonProperty("com/testrtc") String status
    ){
        this.role=role;
        this.config=config;
        this.queueStatus=queueStatus;
        this.queueCount=queueCount;
        this.dbStatus =dbStatus;
        this.dbCount =dbCount;
        this.status=status;
    }
}