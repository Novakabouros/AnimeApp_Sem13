package com.example.practica_aplicacion;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JikanApi {
    @GET("anime")
    Call<AnimeResponse> getAnimeList();

    @GET("anime/{id}/episodes")
    Call<EpisodeResponse> getEpisodeList(@Path("id") int animeId);
}

