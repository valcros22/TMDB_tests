package com.tmdb.Genres;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

import com.tmdb.interfaces.GenresAPIService;
import com.tmdb.utils.RetrofitClient;

public class Genres {
	
	private String url = "";
	private String apiKey = "";
	private List<GenreItem> genres = null;
	
	public Genres(String url, String apiKey){
		this.url = url;
		this.apiKey = apiKey;
		this.retrieveGenres();
	}
	
	public void retrieveGenres(){
		GenresAPIService client = RetrofitClient.getClient(this.url).create(GenresAPIService.class);
        Call<GenreList> call = client.fetchGenres(this.apiKey);
        try {
			Response<GenreList> response = call.execute();
			this.genres = response.body().getGenres();
		} catch (IOException e) {
			System.out.println("There was an error in genres request : ");
			e.printStackTrace();
			this.genres = null;
		}
	}
	
	public List<GenreItem> getGenresList(){
		return this.genres;
	}

	public boolean isGenrePresent(String genre){
		if (this.genres != null){
			for(GenreItem genreItem : this.genres) {
				if (genreItem.name.equals(genre)){
					return true;
				}
			}
		}
		return false;	
	}
}
