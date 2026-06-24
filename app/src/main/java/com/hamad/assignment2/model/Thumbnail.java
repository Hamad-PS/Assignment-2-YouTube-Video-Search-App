package com.hamad.assignment2.model;

import com.google.gson.annotations.SerializedName;

public class Thumbnail {

    @SerializedName("url")
    private String url;

    public String getUrl() {
        return url;
    }
}