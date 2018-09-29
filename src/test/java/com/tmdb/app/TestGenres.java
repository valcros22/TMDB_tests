package com.tmdb.app;

import com.tmdb.Genres.Genres;
import org.testng.Assert;

public class TestGenres {

	public static final String DEFAULT_MAIN_URL = "https://api.themoviedb.org/";
	public static final String API_KEY = "8bbd6058a4d112020fcd81f86a0a59c6";
	
	public static void main(String args[]){
		Genres genres = new Genres(DEFAULT_MAIN_URL, API_KEY);
		boolean result = genres.isGenrePresent("Comedy");
		if (result){
			System.out.println("Comedy genre was present");
		}
		Assert.assertTrue(result, "Comedy genre was not present");
	}
	
}
