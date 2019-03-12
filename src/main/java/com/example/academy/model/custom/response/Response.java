package com.example.academy.model.custom.response;

public class Response {

    public static final String MESSAGE_OK = "OK";
    public static final String MESSAGE_ERROR = "ERROR";

    private String status;
    private Object data;
    private MetaResponse meta;

    public Response(){

    }

    public Response(String status, Object data, MetaResponse meta){
        this.setStatus(status);
        this.setData(data);
        this.setMeta(meta);
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public MetaResponse getMeta() {
        return meta;
    }

    public void setMeta(MetaResponse meta) {
        this.meta = meta;
    }
}
