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
public class Config {
    private String version;
    private String agentVersion;
    private String systemName;
    private String instanceName;


    public Config(@JsonProperty("version") String version,
                @JsonProperty("agentVersion") String agentVersion,
                @JsonProperty("systemName") String systemName,
                @JsonProperty("instanceName") String instanceName
    ){
        this.version=version;
        this.agentVersion=agentVersion;
        this.systemName =systemName;
        this.instanceName =instanceName;
    }
}
