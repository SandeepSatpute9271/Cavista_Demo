package com.mefy.cavista_demo.model.bean;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Data {
    private String id;
    @SerializedName("images")
    private ArrayList<Images> images = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Images> getImages() {
        return images;
    }

    public void setImages(ArrayList<Images> images) {
        this.images = images;
    }
}
