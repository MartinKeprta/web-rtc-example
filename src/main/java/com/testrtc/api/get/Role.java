package com.testrtc.api.get;

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
public class Role {
    private String web;
    private String monitor;
    private String bachexec;
    private String remoteWorker;
    private String api;

    public Role(@JsonProperty("web") String web,
                @JsonProperty("monitor") String monitor,
                @JsonProperty("batchexec") String batchexec,
                @JsonProperty("remoteWorker") String remoteWorker,
                @JsonProperty("api") String api
    ) {
        this.web = web;
        this.monitor = monitor;
        this.bachexec = batchexec;
        this.remoteWorker = remoteWorker;
        this.api = api;
    }
}
