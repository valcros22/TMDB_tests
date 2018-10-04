package com.tmdb.interfaces;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import com.tmdb.movies.MovieItem;

public interface MoviesAPIService {
 
    @GET("/3/movie/{movie_id}")
    public Call<MovieItem> fetchMovie(@Path("movie_id") String movieId, @Query("api_key") String apiKey);
	
}
