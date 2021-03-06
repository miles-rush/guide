package com.miles.demo.bean;

public class ResponseCode {
    private Integer code;
    private String info;

    private Integer additionalId;//传回某些数据的id
    private String additionalToken;

    public ResponseCode() {}

    public ResponseCode(Integer code, String info) {
        this.code = code;
        this.info = info;
    }

    public ResponseCode(Integer code, String info, Integer id) {
        this.code = code;
        this.info = info;
        this.additionalId = id;
    }

    public ResponseCode(Integer code, String info, Integer id,String token) {
        this.code = code;
        this.info = info;
        this.additionalId = id;
        this.additionalToken = token;
    }
    public Integer getAdditionalId() {
        return additionalId;
    }

    public void setAdditionalId(Integer additionalId) {
        this.additionalId = additionalId;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getAdditionalToken() {
        return additionalToken;
    }

    public void setAdditionalToken(String additionalToken) {
        this.additionalToken = additionalToken;
    }
}
