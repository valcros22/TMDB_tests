package com.tmdb.interfaces;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import com.tmdb.discover.DiscoverList;

public interface DiscoveriesAPIService {
 
    @GET("/3/discover/movie")
    public Call<DiscoverList> fetchDiscoveries(@Query("api_key") String apiKey, 
    		@Query("with_original_language") String withOrigLan, 
    		@Query("release_date.lte") String relDateLte, 
    		@Query("with_genres") String withGenres);
	
}
