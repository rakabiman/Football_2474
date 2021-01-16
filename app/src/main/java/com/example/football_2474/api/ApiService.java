package com.example.football_2474.api;

import com.example.football_2474.model.MatchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("eventspastleague.php?id=4328")
    Call<MatchResponse> getLastEvents(@Query("api_key") String apiKey);

    @GET("eventsnextleague.php?id=4328")
    Call<MatchResponse> getNextEvents(@Query("api_key") String apiKey);
}
