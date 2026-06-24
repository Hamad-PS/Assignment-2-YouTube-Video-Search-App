package com.hamad.assignment2.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class YouTubeResponse {

    @SerializedName("items")
    private List<SearchItem> items;

    public List<SearchItem> getItems() {
        return items;
    }
}