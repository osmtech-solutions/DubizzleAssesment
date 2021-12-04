package com.osmtech.dubizzleassesment.classifiedsapp.networking;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import rx.Observable;

public interface ApiService {
    @GET("default/dynamodb-writer")
    Observable<ResponseBody> getClassifieds();

    @Retention(RetentionPolicy.CLASS)
    @IntDef({RequestCode.getClassifieds})
    @interface RequestCode {
        int SESSION_EXPIRE = 1017;
        int getClassifieds = 1;

    }

}
