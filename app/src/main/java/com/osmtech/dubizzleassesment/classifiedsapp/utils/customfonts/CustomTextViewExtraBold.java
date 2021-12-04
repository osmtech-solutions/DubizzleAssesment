package com.osmtech.dubizzleassesment.classifiedsapp.utils.customfonts;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;


/**
 * Created by Venkateswarlu SKP on 30-08-2016.
 */
@SuppressLint("AppCompatCustomView")
public class CustomTextViewExtraBold extends TextView {
    public CustomTextViewExtraBold(Context context) {
        super(context);
        init();
    }

    public CustomTextViewExtraBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomTextViewExtraBold(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomTextViewExtraBold(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }


    private void init() {
        Typeface externalFont = Typeface.createFromAsset(getContext().getAssets(), "fonts/montserrat_extrabold.ttf");

        setTypeface(externalFont);
//        setAllCaps(true);

    }


}