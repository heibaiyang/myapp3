package com.example.myapp.utils;

import android.util.Log;
import com.example.myapp.models.Users;
import com.google.gson.Gson;

/**
 * Created by Li on 2016/3/27.
 */
public class JsonUtils {
    public static String objectToJson(Object obj) {
        String json = "";
        Gson gson = new Gson();
        json = gson.toJson(obj, Object.class);
        return json;
    }

    public static Object jsonToObject(String json, Class c) {
        try {
            Gson gson = new Gson();
            return gson.fromJson(json, c);
        } catch (Exception e) {
            Log.v("error", e.getMessage());
        }
        return null;
    }
}
