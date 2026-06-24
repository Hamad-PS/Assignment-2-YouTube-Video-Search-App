package com.hamad.assignment2.model;

import com.google.gson.annotations.SerializedName;

public class SearchItem {

    @SerializedName("id")
    private VideoId id;

    @SerializedName("snippet")
    private Snippet snippet;

    public VideoId getId() {
        return id;
    }

    public Snippet getSnippet() {
        return snippet;
    }
}