package com.osmtech.dubizzleassesment.classifiedsapp.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.ResponseBody;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.osmtech.dubizzleassesment.R;
import com.osmtech.dubizzleassesment.classifiedsapp.model.ClassifiedModel;
import com.osmtech.dubizzleassesment.classifiedsapp.networking.ApiService;
import com.osmtech.dubizzleassesment.classifiedsapp.presenter.activities.ClassifiedsActivityPresenter;
import com.osmtech.dubizzleassesment.classifiedsapp.presenter.activities.GenericActivityContract;
import com.osmtech.dubizzleassesment.classifiedsapp.view.adapters.ClassifiedsListAdapter;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.net.URL;

public class ClassifiedsActivity extends AppCompatActivity implements GenericActivityContract.View  {
    private GenericActivityContract.Presenter presenter;
    private Context context; private ProgressBar progressBar;
    private ClassifiedsListAdapter classifiedsListAdapter;
    private RecyclerView rv_classified_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context= ClassifiedsActivity.this;
        presenter = new ClassifiedsActivityPresenter(this);
        presenter.start();
        presenter.loadApi(context, ApiService.RequestCode.getClassifieds);

    }

    @Override
    public void init() {
        rv_classified_list = findViewById(R.id.rv_classified_list);
        progressBar = findViewById(R.id.progressBar);
    }

    @Override
    public void showError(String message) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showResult(ResponseBody responseBody, int requestCode) {
        progressBar.setVisibility(View.GONE);
        rv_classified_list.setVisibility(View.VISIBLE);
        try {
            String bodyString = new String(responseBody.bytes());
            if (ApiService.RequestCode.getClassifieds == requestCode) {
                ClassifiedModel classifiedModel = new Gson().fromJson(bodyString, ClassifiedModel.class);

                if(classifiedModel.getResults().size() > 0){

                    RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
                    rv_classified_list.setLayoutManager(mLayoutManager1);
                    classifiedsListAdapter = new ClassifiedsListAdapter(ClassifiedsActivity.this, classifiedModel);
                    rv_classified_list.setAdapter(classifiedsListAdapter);

                }else{
                    Toast.makeText(context, R.string.data_not_found, Toast.LENGTH_SHORT).show();

                }

            }
        }catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, R.string.error_msg, Toast.LENGTH_SHORT).show();
        }

    }

}