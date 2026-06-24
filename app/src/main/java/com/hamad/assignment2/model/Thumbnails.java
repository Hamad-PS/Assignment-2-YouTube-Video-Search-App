package com.hamad.assignment2.model;

import com.google.gson.annotations.SerializedName;

public class Thumbnails {

    @SerializedName("default")
    private Thumbnail defaultThumbnail;

    @SerializedName("medium")
    private Thumbnail medium;

    @SerializedName("high")
    private Thumbnail high;

    public Thumbnail getDefaultThumbnail() {
        return defaultThumbnail;
    }

    public Thumbnail getMedium() {
        return medium;
    }

    public Thumbnail getHigh() {
        return high;
    }
}