package com.tmdb.tests.movies;

import java.util.List;

import com.tmdb.discover.DiscoverItem;
import com.tmdb.discover.Discoveries;
import com.tmdb.movies.MovieItem;
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
    public static Object[][] movies() {	  
        return new Object[][] {{255}, {284}};
    }
	
	@Test(dataProvider = "Movies", description = "Verify that each company from movie exists and its name matches")
	public void verifyMovieCompaniesExistAndNameMatch(int movieId){
		Movies movies = new Movies(this.mainApiUrl, this.mainApiKey);
		boolean result = movies.movieCompaniesExistAndMatchName(movieId);
		Assert.assertTrue(result, "Not all companies from Movie ID " + Integer.toString(movieId) + " exist on companies catalog");
	}
	
	@Test(dataProvider = "Movies", description = "Verify that movie is present on discoveries filtered by original language, release date and genres")
	public void verifyMovieExistInDiscoveries(int movieId){
		Movies movies = new Movies(this.mainApiUrl, this.mainApiKey);
		MovieItem movieData = movies.getMovieData(movieId);
		Discoveries discoveries = new Discoveries(this.mainApiUrl, this.mainApiKey);
		List<DiscoverItem> discoveriesList = discoveries.getDiscoveries(movieData.getReleaseDate(), movieData.getOriginalLanguage(), 
				discoveries.parseMovieItemGenres(movieData.getGenres()));
		boolean result = discoveries.isMoviePresentInDiscoveries(discoveriesList, movieData.getId(), movieData.getTitle());
		Assert.assertTrue(result, "Movie ID " + Integer.toString(movieId) + " was not present on the filtered discoveries");
	}
	
}
