package com.tmdb.discover;

import java.io.IOException;
import java.util.List;
import retrofit2.Call;
import retrofit2.Response;
import com.tmdb.interfaces.DiscoveriesAPIService;
import com.tmdb.movies.MovieGenreItem;
import com.tmdb.RetrofitClient;

public class Discoveries extends RetrofitClient{
	
	private String url = "";
	private String apiKey = "";
	private DiscoveriesAPIService client = null;
	
	public Discoveries(String url, String apiKey){
		this.url = url;
		this.apiKey = apiKey;
		this.client = super.getRetrofitClient(this.url).create(DiscoveriesAPIService.class);
	}
	
	public List<DiscoverItem> getDiscoveries(String relDate, String origLan, String genres){
        Call<DiscoverList> call = this.client.fetchDiscoveries(this.apiKey, origLan, relDate, genres);
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
