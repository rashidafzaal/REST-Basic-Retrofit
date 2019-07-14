package com.example.patientmodule.utils;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Response;

public class Utils {

    public static String getResponseErrorMessage(Context context, Response response) {

        String userMessage = "";
        if (response.code() == 400) {
            if (!response.isSuccessful()) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response.errorBody().string());
                    userMessage = jsonObject.getString("message");
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return userMessage;
    }
}
