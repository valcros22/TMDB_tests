package com.tmdb.discover;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

import com.tmdb.interfaces.DiscoveriesAPIService;
import com.tmdb.movies.MovieGenreItem;
import com.tmdb.utils.RetrofitClient;

public class Discoveries {
	
	private String url = "";
	private String apiKey = "";
	
	public Discoveries(String url, String apiKey){
		this.url = url;
		this.apiKey = apiKey;
	}
	
	public List<DiscoverItem> getDiscoveries(String relDate, String origLan, String genres){
		DiscoveriesAPIService client = RetrofitClient.getClient(this.url).create(DiscoveriesAPIService.class);
        Call<DiscoverList> call = client.fetchDiscoveries(this.apiKey, origLan, relDate, genres);
        try {
			Response<DiscoverList> response = call.execute();
			return response.body().getResults();
		} catch (IOException e) {
			System.out.println("There was an error in genres request : ");
			e.printStackTrace();
		}
        return null;
	}

	public boolean isMoviePresentInDiscoveries(List<DiscoverItem> listDiscoveries, int movieId, String movieTitle){
		if ((listDiscoveries != null) && !listDiscoveries.isEmpty()){
			for (DiscoverItem discoveryItem : listDiscoveries){
				if (discoveryItem.id == movieId && discoveryItem.title.equals(movieTitle)){
					return true;
				}
			}
			return false;
		}
		return false;
	}
	
	public String parseMovieItemGenres(List<MovieGenreItem> listGenres){
		String parsedGenres = "";
		if ((listGenres != null) && !listGenres.isEmpty()){
			for (MovieGenreItem genreItem : listGenres){
				parsedGenres += Integer.toString(genreItem.getId()) + ",";
			}
			parsedGenres = parsedGenres.substring(0, parsedGenres.length() - 1);
		}
		return parsedGenres;
	}
}
