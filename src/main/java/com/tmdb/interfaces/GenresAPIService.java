package com.tmdb.interfaces;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import com.tmdb.genres.GenreList;

public interface GenresAPIService {
 
    @GET("/3/genre/movie/list")
    public Call<GenreList> fetchGenres(@Query("api_key") String apiKey);
	
}
