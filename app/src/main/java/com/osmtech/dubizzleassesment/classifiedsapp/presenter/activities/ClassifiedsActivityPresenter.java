package com.osmtech.dubizzleassesment.classifiedsapp.presenter.activities;

import android.content.Context;

import com.osmtech.dubizzleassesment.R;
import com.osmtech.dubizzleassesment.classifiedsapp.networking.ApiService;
import com.osmtech.dubizzleassesment.classifiedsapp.networking.NetCheck;
import com.osmtech.dubizzleassesment.classifiedsapp.networking.NetworkUtils;
import com.osmtech.dubizzleassesment.classifiedsapp.utils.DialogsUtils;

import org.json.JSONObject;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ClassifiedsActivityPresenter implements GenericActivityContract.Presenter {
    GenericActivityContract.View mView;

    public ClassifiedsActivityPresenter(GenericActivityContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void start() {
        mView.init();
    }

    @Override
    public void loadApi(Context context, final int requestCode) {

        if(NetCheck.isInternetConnection(context)) {
            if(ApiService.RequestCode.getClassifieds == requestCode){
                NetworkUtils.getUserApiInstance().getClassifieds().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() { DialogsUtils.hideLoading(); }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError(e.toString());
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        mView.showResult(responseBody, requestCode);
                    }
                });
            }
        }else {
            mView.showError(context.getString(R.string.error_no_internet));

        }
    }


}
