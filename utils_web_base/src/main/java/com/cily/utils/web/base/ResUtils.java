package com.cily.utils.web.base;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by 13218 on 2017/7/21.
 */
public class ResUtils {
    public final static <T>Map<String, Object> res(String code, String msg, T t){
        return ResponseBean.getMap(code, msg, t);
    }

    public final static <T>Map<String, Object> success(T t){
        return res(ResConf.C_SUCCESS, ResConf.M_SUCCESS, t);
    }

    public final static <T>String resStr(String code, String msg, T t){
        return JSON.toJSONString(ResponseBean.getBean(code, msg, t));
    }

    public final static <T>String successStr(T t){
        return resStr(ResConf.C_SUCCESS, ResConf.M_SUCCESS, t);
    }

    public final static <T>void response(HttpServletResponse response, String code, String msg, T t) throws IOException {
        if (response == null){
            return;
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().append(
                resStr(code, msg, t)).flush();
    }
}
