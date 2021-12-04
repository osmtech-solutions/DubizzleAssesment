package com.osmtech.dubizzleassesment.classifiedsapp.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.osmtech.dubizzleassesment.R;


public class ClassifiedsDetailActivity extends AppCompatActivity {
    private ImageView product_img, iv_back;
    private TextView txt_date, txt_price, txt_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classifieds_detail);

        product_img = findViewById(R.id.product_img);
        txt_date = findViewById(R.id.txt_date);
        txt_price = findViewById(R.id.txt_price);
        txt_name = findViewById(R.id.txt_name);
        iv_back = findViewById(R.id.iv_back);

        txt_date.setText(getIntent().getStringExtra("date"));
        txt_price.setText(getIntent().getStringExtra("price"));
        txt_name.setText(getIntent().getStringExtra("name"));

        Glide.with(ClassifiedsDetailActivity.this)
                .load(getIntent().getStringExtra("image"))
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(product_img);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        //Image caching framework
        //String encodedImage = SharedPrefsData.getString(ClassifiedsDetailActivity.this, Constants.HEADER, Constants.PREF_NAME);
        //byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
        //Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        //product_img.setImageBitmap(decodedByte);

    }
}