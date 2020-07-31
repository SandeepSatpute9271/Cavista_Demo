package com.mefy.cavista_demo.model.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Images implements Serializable {
    private String id;
    private String link;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
