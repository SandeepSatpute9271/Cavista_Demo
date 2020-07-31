package com.mefy.cavista_demo.controller.networking;

import android.util.Log;

import com.google.gson.Gson;
import com.mefy.cavista_demo.controller.BaseActivity;
import com.mefy.cavista_demo.model.responces.CommonResponse;
import com.mefy.cavista_demo.utils.Finals;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiHandler {
    private BaseActivity activity;
    private String callFor;
    private ApiInterface apiInterface;
    private Call<CommonResponse> call;
    private String TAG = "ApiHandler";

    public ApiHandler(BaseActivity activity, String callFor, String data) {
        apiInterface = ApiClient.getClient(activity).create(ApiInterface.class);
        this.activity = activity;
        this.callFor = callFor;
        activity.showWaitDialog("Please wait...");
        handleCallFor(callFor, data);
    }

    private void handleCallFor(String callFor, String data) {
        if (callFor.equals(Finals.SEARCH)) {
            call = apiInterface.deviceList(data);
        }
        CallEnqueue();
    }

    private void CallEnqueue() {
        call.enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                activity.hideWaitDialog();
                CommonResponse resp = response.body();
                activity.onGetResponse(resp, callFor);
            }

            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {
                Log.e(TAG, "API response is Fail "+t.getMessage());
                call.cancel();
            }
        });
    }
}
