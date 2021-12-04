package com.osmtech.dubizzleassesment.classifiedsapp.view.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.osmtech.dubizzleassesment.R;
import com.osmtech.dubizzleassesment.classifiedsapp.model.ClassifiedModel;
import com.osmtech.dubizzleassesment.classifiedsapp.utils.Utils;
import com.osmtech.dubizzleassesment.classifiedsapp.view.activities.ClassifiedsDetailActivity;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


public class ClassifiedsListAdapter extends RecyclerView.Adapter<ClassifiedsListAdapter.MyviewHolder>{
    Context context;
    ClassifiedModel classifiedModel;


    public ClassifiedsListAdapter(Context context, ClassifiedModel classifiedModel) {
        this.context = context;
        this.classifiedModel = classifiedModel;

    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_classifieds_list2, viewGroup, false);
        return new MyviewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull final MyviewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.cv_store_list.setAnimation(AnimationUtils.loadAnimation(context, R.anim.item_animation_from_bottom));

        holder.txt_name.setText(classifiedModel.getResults().get(position).getName());
        holder.txt_price.setText(classifiedModel.getResults().get(position).getPrice());
        holder.txt_date.setText(Utils.changeDate(classifiedModel.getResults().get(position).getCreatedAt()));


        Glide.with(context)
                .load(classifiedModel.getResults().get(position).getImageUrlsThumbnails().get(0))
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(holder.iv_classified_image);

        //Setting image from url without Glide library
       // holder.iv_classified_image.setImageBitmap(getBitmapFromURL(classifiedModel.getResults().get(position).getImageUrlsThumbnails().get(0)));

        holder.cv_store_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Image caching framework
                //String header = convertUrlToBase64(classifiedModel.getResults().get(position).getImageUrls().get(0));
                //SharedPrefsData.putString(context, Constants.HEADER, header, Constants.PREF_NAME);
                Intent i = new Intent(context, ClassifiedsDetailActivity.class);
                i.putExtra("name", classifiedModel.getResults().get(position).getName());
                i.putExtra("price", classifiedModel.getResults().get(position).getPrice());
                i.putExtra("image", classifiedModel.getResults().get(position).getImageUrls().get(0));
                i.putExtra("date", Utils.changeDate(classifiedModel.getResults().get(position).getCreatedAt()));

                context.startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return classifiedModel.getResults().size();
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public static class MyviewHolder extends RecyclerView.ViewHolder {

        CardView cv_store_list;
        TextView txt_name, txt_date, txt_price;
        Button btn_view, btn_edit;
        ImageView iv_classified_image;


        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            cv_store_list = itemView.findViewById(R.id.cv_store_list);
            txt_name = itemView.findViewById(R.id.txt_name);
            txt_date = itemView.findViewById(R.id.txt_date);
            txt_price = itemView.findViewById(R.id.txt_price);
            iv_classified_image =  itemView.findViewById(R.id.iv_classified_image);

        }

    }
    public String convertUrlToBase64(String url) {
        URL newurl;
        Bitmap bitmap;
        String base64 = "";
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            newurl = new URL(url);
            bitmap = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            base64 = Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return base64;
    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
