package com.example.vdovin.bankproject.controller;

import com.example.vdovin.bankproject.extra.Constants;
import com.example.vdovin.bankproject.extra.Utils;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by vdovin on 7/9/16.
 */
public class SyncController {

    private static SyncController mInstance;

    private OkHttpClient okHttpClient = new OkHttpClient();

    public static SyncController getInstance() {

        if (mInstance == null) {
            synchronized (SyncController.class) {
                if (mInstance == null) {
                    mInstance = new SyncController();
                }
            }
        }

        return mInstance;
    }

    public String doSync(){
        RequestBody body = new FormBody.Builder()
                .add(Constants.BODY_PARAM, Utils.getCurrentDay())
                .build();

        Request request = new Request.Builder()
                .url(Constants.BANKS_URL)
                .post(body)
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
