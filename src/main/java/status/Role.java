package status;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Martin Keprta on 2/17/2018.
 *
 */
public class Role {
    private Boolean web;
    private Boolean monitor;
    private Boolean bachexec;
    private Boolean remoteWorker;
    private Boolean api;

    public Role(@JsonProperty("web") Boolean web,
                @JsonProperty("monitor") Boolean monitor,
                @JsonProperty("batchexec") Boolean batchexec,
                @JsonProperty("remoteWorker") Boolean remoteWorker,
                @JsonProperty("api") Boolean api
    ){
        this.web=web;
        this.monitor=monitor;
        this.bachexec =batchexec;
        this.remoteWorker =remoteWorker;
        this.api =api;
    }

    @Override
    public String toString(){
        return null;
    }

}
