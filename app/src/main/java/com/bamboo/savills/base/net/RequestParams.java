package com.bamboo.savills.base.net;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by tong on 2019/10/9.
 */

public class RequestParams {
    private Map<String, String> body = new HashMap<>();

    public RequestParams addBody(String key, String value) {
        body.put(key, value);
        return this;
    }

    public String getBody(String key) {
        return body.get(key);
    }

    public Map<String, String> getBody() {
        return body;
    }

    public Set<String> getKeys() {
        return body.keySet();
    }

    @Override
    public String toString() {
        return "RequestParams{" +
                "body=" + body +
                '}';
    }
}
