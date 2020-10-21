package com.zarmin.rdrandms;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    private boolean success;
    private Integer intResponse = null;
    private String dataResponse = null;

    Response(final boolean success, final int response) {
        this.success = success;
        this.intResponse = response;
    }

    Response(final boolean success, final String response) {
        this.success = success;
        this.dataResponse = response;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getData() {
        return dataResponse;
    }

    public Integer getInt() {
        return intResponse;
    }
}
