package com.osmtech.dubizzleassesment.classifiedsapp.presenter.activities;

import android.content.Context;

import org.json.JSONObject;

import okhttp3.ResponseBody;

public interface GenericActivityContract {
    interface View {
        void init();
        void showError(String message);
        void showResult(ResponseBody responseBody, int requestCode);
    }

    interface Presenter {
        void start();
        void loadApi(Context context, int requestCode);
    }

}
