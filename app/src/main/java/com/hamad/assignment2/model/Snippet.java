package com.hamad.assignment2.model;

import com.google.gson.annotations.SerializedName;

public class Snippet {

    @SerializedName("publishedAt")
    private String publishedAt;

    @SerializedName("publishTime")
    private String publishTime;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("channelTitle")
    private String channelTitle;

    @SerializedName("thumbnails")
    private Thumbnails thumbnails;

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public Thumbnails getThumbnails() {
        return thumbnails;
    }
}