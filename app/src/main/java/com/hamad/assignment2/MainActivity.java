package com.hamad.assignment2;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hamad.assignment2.model.SearchItem;
import com.hamad.assignment2.model.Snippet;
import com.hamad.assignment2.model.YouTubeResponse;
import com.hamad.assignment2.network.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String API_KEY = "AIzaSyAEk7F_bbhTFUWxwJXDn5fzxviwCJYk7EY";
    private static final int MAX_RESULTS = 10;

    private EditText editTextSearch;
    private Button buttonSearch;
    private ProgressBar progressBar;
    private TextView textViewMessage;
    private RecyclerView recyclerViewVideos;

    private final List<VideoItem> videoList = new ArrayList<>();
    private VideoAdapter videoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupRecyclerView();
        setupListeners();
    }

    private void initViews() {
        editTextSearch = findViewById(R.id.editTextSearch);
        buttonSearch = findViewById(R.id.buttonSearch);
        progressBar = findViewById(R.id.progressBar);
        textViewMessage = findViewById(R.id.textViewMessage);
        recyclerViewVideos = findViewById(R.id.recyclerViewVideos);
    }

    private void setupRecyclerView() {
        videoAdapter = new VideoAdapter(videoList);
        recyclerViewVideos.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewVideos.setAdapter(videoAdapter);
    }

    private void setupListeners() {
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performSearch();
            }
        });

        editTextSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch();
                return true;
            }
            return false;
        });
    }

    private void performSearch() {
        String query = editTextSearch.getText().toString().trim();

        if (query.isEmpty()) {
            Toast.makeText(this, "Please enter a search term", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isNetworkAvailable()) {
            showMessage("No internet connection. Please check your network.");
            return;
        }

        clearResults();
        showLoading(true);

        Call<YouTubeResponse> call = ApiClient.getApiService()
                .searchVideos("snippet", "video", query, MAX_RESULTS, API_KEY);

        call.enqueue(new Callback<YouTubeResponse>() {
            @Override
            public void onResponse(Call<YouTubeResponse> call, Response<YouTubeResponse> response) {
                showLoading(false);

                if (!response.isSuccessful() || response.body() == null) {
                    showMessage("Request failed (code " + response.code()
                            + "). Check your API key or try again.");
                    return;
                }

                List<SearchItem> items = response.body().getItems();

                if (items == null || items.isEmpty()) {
                    showMessage("No results found");
                    return;
                }

                for (SearchItem item : items) {
                    Snippet snippet = item.getSnippet();
                    if (snippet == null) {
                        continue;
                    }

                    String thumbnailUrl = null;
                    if (snippet.getThumbnails() != null
                            && snippet.getThumbnails().getMedium() != null) {
                        thumbnailUrl = snippet.getThumbnails().getMedium().getUrl();
                    }

                    String rawTime = snippet.getPublishTime() != null
                            ? snippet.getPublishTime()
                            : snippet.getPublishedAt();

                    VideoItem videoItem = new VideoItem(
                            snippet.getTitle(),
                            snippet.getDescription(),
                            snippet.getChannelTitle(),
                            formatDate(rawTime),
                            thumbnailUrl
                    );
                    videoList.add(videoItem);
                }

                recyclerViewVideos.setVisibility(View.VISIBLE);
                videoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<YouTubeResponse> call, Throwable t) {
                // Network failure or unexpected error.
                showLoading(false);
                showMessage("Error: " + t.getMessage());
            }
        });
    }

    private void showLoading(boolean loading) {
        progressBar.setVisibility(loading ? View.VISIBLE : View.GONE);
        buttonSearch.setEnabled(!loading);
        if (loading) {
            textViewMessage.setVisibility(View.GONE);
        }
    }

    private void showMessage(String message) {
        textViewMessage.setText(message);
        textViewMessage.setVisibility(View.VISIBLE);
        recyclerViewVideos.setVisibility(View.GONE);
    }

    private void clearResults() {
        videoList.clear();
        videoAdapter.notifyDataSetChanged();
        textViewMessage.setVisibility(View.GONE);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) {
            return false;
        }
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }

    private String formatDate(String rawDate) {
        if (rawDate == null) {
            return "";
        }
        int tIndex = rawDate.indexOf("T");
        if (tIndex > 0) {
            return rawDate.substring(0, tIndex);
        }
        return rawDate;
    }
}