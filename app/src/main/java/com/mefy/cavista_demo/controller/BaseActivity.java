package com.mefy.cavista_demo.controller;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mefy.cavista_demo.R;
import com.mefy.cavista_demo.model.responces.CommonResponse;
import com.mefy.cavista_demo.view.DialogWaitting;

public abstract class BaseActivity extends AppCompatActivity {
    private DialogWaitting mWaitDialog = null;
    private Dialog dialog = null;

    public abstract void onGetResponse(CommonResponse response, String callFor);

    public void showWaitDialog(String s) {
        if (null == mWaitDialog) {
            mWaitDialog = new DialogWaitting(BaseActivity.this);
        }
        mWaitDialog.show();
    }

    public void hideWaitDialog() {
        if (null != mWaitDialog) {
            mWaitDialog.dismiss();
        }
    }

    public void customToastActivity(Context context, String message) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.toast_layout_root));
        TextView text = layout.findViewById(R.id.tvToast);
        text.setTextSize(18);
        text.setText(message);
        Toast toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
}
