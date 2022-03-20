package com.example.week03.t1.gateway.dto;

import java.io.Serializable;

/**
 * @author ltx
 * @date 2022/3/20 5:26 下午
 */
public class RequestDto implements Serializable {

    private final static String KEY = "key";

    private String key;

    private String data;


    public boolean checkKey() {
        return KEY.equals(this.key);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
