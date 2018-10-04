package com.tmdb.genres;

import java.io.IOException;
import java.util.List;
import retrofit2.Call;
import retrofit2.Response;
import com.tmdb.interfaces.GenresAPIService;
import com.tmdb.RetrofitClient;

public class Genres extends RetrofitClient{
	
	private String url = "";
	private String apiKey = "";
	private List<GenreItem> genres = null;
	private GenresAPIService client = null;
	
	public Genres(String url, String apiKey){
		this.url = url;
		this.apiKey = apiKey;
		this.client  = super.getRetrofitClient(this.url).create(GenresAPIService.class);
		this.retrieveGenres();
	}
	
	public void retrieveGenres(){
        Call<GenreList> call = this.client.fetchGenres(this.apiKey);
        try {
        	Response<GenreList> response = call.execute();
        	if (response.code() != 401){
        		this.genres = response.body().getGenres();
        	}
        	else{
        		System.out.println("The API key "+ this.apiKey +" was unauthorized");
        		this.genres = null;
        	}
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
