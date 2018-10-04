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
		if (movieData != null){
			Discoveries discoveries = new Discoveries(this.mainApiUrl, this.mainApiKey);
			String parsedGenres = discoveries.parseMovieItemGenres(movieData.getGenres());
			List<DiscoverItem> discoveriesList = discoveries.getDiscoveries(movieData.getReleaseDate(), 
					movieData.getOriginalLanguage(), parsedGenres);
			if (discoveriesList != null && !discoveriesList.isEmpty()){
				boolean result = discoveries.isMoviePresentInDiscoveries(discoveriesList, movieData.getId(), movieData.getTitle());
				Assert.assertTrue(result, "Movie ID " + Integer.toString(movieId) + " was not present on the filtered discoveries");
			}
			else{
				Assert.fail("There were no discoveries for the used filter (release date lte = "+ movieData.getReleaseDate() +
						". original language = "+ movieData.getOriginalLanguage() +" and genres = "+ parsedGenres + ")");
			}
		}
		else{
			Assert.fail("There was no movie found with id "+ Integer.toString(movieId));
		}
	}
	
	public void verifyIncorrectMovieId(int movieId){
		// TODO : this test will ensure that any incorrect id like 0 or negative values return a 404 response code
	}
	
	public void verifyMoviebelongsToCollection(int movieId, boolean belongs){
		// TODO : this test will ensure that movies that should belong to collection have the data and the ones that don't
		// 		  only show false as value
	}
	
	public void verifyMovieAdult(int movieId, boolean isAdult){
		// TODO : this test will ensure that movies that should be adult are correctly marked and same for 
		//		  the ones that should not
	}
	
	public void verifyMovieVideo(int movieId, boolean isVideo){
		// TODO : this test will ensure that movies that should have video are correctly marked and same for 
		//		  the ones that should not
	}
	
	public void verifyMovieHomepage(int movieId, boolean hasHomepage){
		// TODO : this test will ensure that movies that should have homepage have a string webpage and for 
		//		  the ones that should not, a null value will be present
	}
	
	public void verifyMoviesSpokenLanguages(int movieId, List<String> spokenLanguages){
		// TODO : this test will ensure that the movie has all the correct spoken languages. The list will include
		//		  cases with 1 spoken languages and more.	
	}
	
	public void verifyMoviePresentSearch(int movieId){
		// TODO : this test will ensure that searching on search API using data from a movie correctly appears on the results
	}
}
