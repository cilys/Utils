package com.cily.utils.web.base;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 13218 on 2017/7/21.
 */
public class ResponseBean<T> implements Serializable {
    private String code;
    private String msg;
    private T data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public final static <T>Map<String, Object> getMap(String code, String msg, T t){
        Map<String, Object>map = new HashMap<>();
        map.put("code", code);
        if (msg != null) {
            map.put("msg", msg);
        }
        if (t != null) {
            map.put("data", t);
        }
        return map;
    }

    public final static <T>ResponseBean getBean(String code, String msg, T t){
        ResponseBean b = new ResponseBean();
        b.setCode(code);
        if (msg != null){
            b.setMsg(msg);
        }
        if (t != null){
            b.setData(t);
        }
        return b;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
