package com.example.practica_aplicacion;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EpisodeActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private EpisodeAdapter episodeAdapter;
    private List<Episode> episodeList = new ArrayList<>();
    private int animeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episode);

        animeId = getIntent().getIntExtra("animeId", -1);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        episodeAdapter = new EpisodeAdapter(episodeList, this);
        recyclerView.setAdapter(episodeAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.jikan.moe/v4/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JikanApi jikanApi = retrofit.create(JikanApi.class);
        jikanApi.getEpisodeList(animeId).enqueue(new Callback<EpisodeResponse>() {
            @Override
            public void onResponse(Call<EpisodeResponse> call, Response<EpisodeResponse> response) {
                if (response.isSuccessful()) {
                    episodeList.addAll(response.body().getData());
                    episodeAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<EpisodeResponse> call, Throwable t) {
                // Manejar error
            }
        });
    }
}

