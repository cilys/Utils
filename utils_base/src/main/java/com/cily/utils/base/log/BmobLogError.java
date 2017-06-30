package com.cily.utils.base.log;

import com.cily.utils.base.HttpUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * user:xuli
 * time:2017/6/29
 * desc:
 */

public class BmobLogError {
    private static String url = "https://api.bmob.cn/1/";

    private final static Map<String, String> getBmobHeaders(String applicationId, String restApi){
        Map<String, String>headers = new HashMap<>();
        headers.put("X-Bmob-Application-Id", applicationId);
        headers.put("X-Bmob-REST-API-Key", restApi);
        headers.put("Content-Type", "application/json");

        return headers;
    }

    public final static String postError(String tableName, String applicationId,
                                       String restApi, String errorJson) throws IOException {
        Map<String, String> param = new HashMap<>();
        param.put(HttpUtils.JSON_BODY, errorJson);
        return HttpUtils.post(url + tableName, false, getBmobHeaders(applicationId, restApi), param);
    }

    public static void main(String[] args) throws IOException {

    }
}