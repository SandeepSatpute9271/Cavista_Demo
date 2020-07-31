package com.mefy.cavista_demo.model.responces;

import com.google.gson.annotations.SerializedName;
import com.mefy.cavista_demo.model.bean.Data;

import java.util.ArrayList;

public class CommonResponse {
    @SerializedName("data")
    private ArrayList<Data> data = new ArrayList<>();
    @SerializedName("success")
    private boolean success;
    @SerializedName("status")
    private int status;

    public ArrayList<Data> getData() {
        return data;
    }

    public void setData(ArrayList<Data> data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
