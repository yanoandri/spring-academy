package com.example.academy.model.custom.response;

public class MetaResponse {

    private int code;
    private String message;
    private String debugInfo;

    public MetaResponse(){
    }

    public MetaResponse(int code, String message, String debugInfo){
        this.setCode(code);
        this.setMessage(message);
        this.setDebugInfo(debugInfo);
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDebugInfo() {
        return debugInfo;
    }

    public void setDebugInfo(String debugInfo) {
        this.debugInfo = debugInfo;
    }
}
