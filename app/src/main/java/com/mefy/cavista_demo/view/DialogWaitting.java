package com.mefy.cavista_demo.view;


import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mefy.cavista_demo.R;

public class DialogWaitting {


    private Dialog mDialog = null;
    private TextView mTextView = null;
    private ImageView mImageView = null;

    public DialogWaitting(Context context) {
        mDialog = new Dialog(context, R.style.dialog_translucent);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(R.layout.dialog_waiting);
        mDialog.setCancelable(true);
        mImageView = mDialog.findViewById(R.id.loadingImage);
        mTextView = mDialog.findViewById(R.id.waittingText);
        Glide.with(context).load(R.drawable.triad_ring)
                .asGif()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mImageView);
    }


    public void show() {
        mTextView.setText("Please wait...");
        mDialog.show();
    }

    public void show(String hint) {
        mTextView.setText(hint);
        mDialog.show();
    }

    public void show(int hint) {
        mTextView.setText(hint);
        mDialog.show();
    }

    public void dismiss() {

        if (mDialog != null && mDialog.isShowing())
            mDialog.dismiss();
    }

    public boolean isMdialogShowing() {
        return mDialog != null && mDialog.isShowing();
    }
}
