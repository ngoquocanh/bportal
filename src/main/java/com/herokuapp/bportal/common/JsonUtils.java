package com.herokuapp.bportal.common;

import com.google.gson.Gson;

public class JsonUtils {
    public static String toJson(Object obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }
}
