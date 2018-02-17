package status;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Martin Keprta on 2/17/2018.


 "queueStatus": "string",
 "queueCount": 0,
 "dbStatus": "string",
 "dbCount": 0,
 "status": "string"
 }
 *
 */
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

    @Override
    public String toString(){
        return null;
    }

}
