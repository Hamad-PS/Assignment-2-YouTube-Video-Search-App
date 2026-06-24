package com.hamad.assignment2.network;

import com.hamad.assignment2.model.YouTubeResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

// Retrofit interface that describes the YouTube search endpoint.
public interface YouTubeApiService {

    // Final URL becomes:
    // youtube/v3/search?part=snippet&type=video&q=...&maxResults=...&key=...
    @GET("youtube/v3/search")
    Call<YouTubeResponse> searchVideos(
            @Query("part") String part,
            @Query("type") String type,
            @Query("q") String query,
            @Query("maxResults") int maxResults,
            @Query("key") String apiKey
    );
}