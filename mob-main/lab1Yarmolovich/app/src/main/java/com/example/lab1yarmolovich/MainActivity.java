package com.example.lab1yarmolovich;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.lab1yarmolovich.model.Image;
import com.example.lab1yarmolovich.model.ImageApi;
import com.example.lab1yarmolovich.model.ImageResponse;

import java.util.List;
import java.util.Random;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private String currentImageUrl;
    private String imagePageUrl = "https://unsplash.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText searchEditText = findViewById(R.id.searchEditText);
        ImageButton searchBtn = findViewById(R.id.searchBtn);
        Button likeBtn = findViewById(R.id.likeBtn);
        Button dislikeBtn = findViewById(R.id.dislikeBtn);
        Button downloadBtn = findViewById(R.id.downloadBtn);
        Button showInWeb = findViewById(R.id.showInWeb);
        Button authorBtn = findViewById(R.id.authorBtn);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.unsplash.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ImageApi imageApi = retrofit.create(ImageApi.class);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = searchEditText.getText().toString();
                if (!query.isEmpty()) {
                    searchImages(imageApi, query);
                }
            }
        });

        likeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = searchEditText.getText().toString();
                if (!query.isEmpty()) {
                    searchImages(imageApi, query);
                }
            }
        });

        dislikeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = searchEditText.getText().toString();
                if (!query.isEmpty()) {
                    searchImages(imageApi, query);
                }
            }
        });

        showInWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imagePageUrl != null) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(imagePageUrl));
                    startActivity(browserIntent);
                }
            }
        });

        downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentImageUrl != null) {
                    downloadImage(currentImageUrl);
                } else {
                    showAlertDialog("Error", "No image to download");
                }
            }
        });

        authorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog("Автор", "Разработал Ярмолович Александр, группа АС-63");
            }
        });

    }

    private void searchImages(ImageApi imageApi, String query) {

        imageView = findViewById(R.id.image);

        String api = "3d_ukmumfwmwcsFxEWfgB8yepI45aw-mtyTQklpzBqI";

        int page = 1;
        int perPage = 30;

        Call<ImageResponse> call = imageApi.searchImages(query, api, page, perPage);
        call.enqueue(new Callback<ImageResponse>() {
            @Override
            public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                if (!response.isSuccessful()) {
                    showAlertDialog("Error", "Failed to retrieve images");
                    return;
                }

                ImageResponse imageResponse = response.body();
                if (imageResponse == null) {
                    showAlertDialog("Error", "No response from the server");
                    return;
                }

                List<Image> images = imageResponse.getResults();
                if (images == null || images.isEmpty()) {
                    showAlertDialog("Error", "No images found");
                    return;
                }

                Random random = new Random();
                int randomIndex = random.nextInt(images.size());

                currentImageUrl = images.get(0).getUrls() != null ? images.get(randomIndex).getUrls().getSmall() : null;
                if (currentImageUrl == null) {
                    showAlertDialog("Error", "Image URL is null");
                    return;
                }

                imagePageUrl = "https://unsplash.com/photos/" + images.get(randomIndex).getId();
                Glide.with(MainActivity.this).load(currentImageUrl).into(imageView);
            }

            @Override
            public void onFailure(Call<ImageResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showAlertDialog(String title, String message) {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create()
                .show();
    }

    private void downloadImage(String imageUrl) {
        DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(imageUrl);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        downloadManager.enqueue(request);
    }

}