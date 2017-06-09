package com.app.annotation.apt;

import java.util.HashMap;

/**
 * Created by zhuzhejun on 2017/6/6.
 */

public class DataMap {
    public final static String DATA_HEAD = "data_head";
    public HashMap<String, Object> mMap = new HashMap<>();

    public DataMap add(String var1, Object var2) {
        mMap.put(var1, var2);
        return this;
    }

    public DataMap addHead(Object var1) {
        mMap.put(DATA_HEAD, var1);
        return this;
    }

    public Object get(String var) {
        return mMap.get(var);
    }
}
