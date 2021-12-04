package com.osmtech.dubizzleassesment.classifiedsapp.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;




public class DialogsUtils {
    static ProgressDialog pDialog;

    public static ProgressDialog showProgressDialog(Context context, String message){
        final ProgressDialog m_Dialog = new ProgressDialog(context);
        m_Dialog.setMessage(message);
        m_Dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        m_Dialog.setCancelable(true);
//        m_Dialog.setOnShowListener(new DialogInterface.OnShowListener() {
//            @Override
//            public void onShow(DialogInterface dialog) {
//                ProgressBar v = (ProgressBar)m_Dialog.findViewById(android.R.id.progress);
//                v.getIndeterminateDrawable().setColorFilter(0xFF2196E3,
//                        android.graphics.PorterDuff.Mode.MULTIPLY);
//            }
//        });
        m_Dialog.show();
        return m_Dialog;
    }

    public static void showLoading(Context mContext, String message, final int tag, boolean cancelable) {
        try {

            pDialog = new ProgressDialog(mContext);
            pDialog.setMessage(message);
            pDialog.setCancelable(false);
            pDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    //AppController.getInstance().getRequestQueue().cancelAll(tag);
                    dialog.dismiss();
                }
            });
            pDialog.show();
        } catch (Exception e) {
            //FirebaseCrashlytics.getInstance().recordException(new Exception("Web Error : " + e.getMessage().toString()));
        }

    }
    public static void hideLoading() {
        try {
            if (pDialog != null && pDialog.isShowing())
                pDialog.dismiss();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
