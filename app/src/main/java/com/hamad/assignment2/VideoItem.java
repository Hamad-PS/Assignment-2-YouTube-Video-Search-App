package com.hamad.assignment2;

public class VideoItem {

    private final String title;
    private final String description;
    private final String channelTitle;
    private final String publishTime;
    private final String thumbnailUrl;

    public VideoItem(String title, String description, String channelTitle,
                     String publishTime, String thumbnailUrl) {
        this.title = title;
        this.description = description;
        this.channelTitle = channelTitle;
        this.publishTime = publishTime;
        this.thumbnailUrl = thumbnailUrl;
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

    public String getPublishTime() {
        return publishTime;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }
}