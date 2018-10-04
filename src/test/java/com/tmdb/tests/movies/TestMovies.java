package com.tmdb.tests.movies;

import java.util.List;

import com.tmdb.movies.MovieCompaniesItem;
import com.tmdb.movies.Movies;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestMovies{
	
	private String mainApiUrl;
	private String mainApiKey;

    @BeforeClass
    @Parameters({"apiUrl", "apiKey"})
    public void initialSetup(String apiUrl, String apiKey) {
    	if (apiUrl.equals("no_api_url") || (apiKey.equals("no_api_key"))){
    		Assert.fail("Suite variables 'apiUrl' and 'apiKey' should be set as maven commandline parameters");
    	}
    	else {
    		this.mainApiUrl = apiUrl;
    		this.mainApiKey = apiKey;
    	}
     }  

	@DataProvider(name = "Movies")
    public static Object[][] genres() {	  
        return new Object[][] { { 100}, { 245}};
    }
	
	@Test(dataProvider = "Movies", description = "Verify that each company from movie exists and its name matches")
	public void verifyMovieCompaniesExistAndNameMatch(int movieId){
		Movies movies = new Movies(this.mainApiUrl, this.mainApiKey);
		boolean result = movies.movieCompaniesExistAndMatchName(movieId);
		Assert.assertTrue(result, "Not all companies from Movie ID " + Integer.toString(movieId) + " exist on companies catalog");
	}
	
}
