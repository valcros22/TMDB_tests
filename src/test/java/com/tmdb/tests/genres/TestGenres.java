package com.tmdb.tests.genres;

import com.tmdb.genres.Genres;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestGenres{
	
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

	@DataProvider(name = "Genres")
    public static Object[][] genres() {	  
        return new Object[][] { { "Comedy"}, { "Action"}};
    }
	
	@Test(dataProvider = "Genres", description = "Verify that choosen genre is present on genres catalog")
	public void verifyGenreIsPresent(String genre){
		Genres genres = new Genres(this.mainApiUrl, this.mainApiKey);
		boolean result = genres.isGenrePresent(genre);
		Assert.assertTrue(result, genre + " genre was not present");
	}
	
	public void verifyGenreIsNotPresent(String genre){
		// TODO : this test will ensure that the genre used is not on the API base.
	}
	
	public void verifyGenreNotRepeated(String genre){
		// TODO : this test will ensure that the genre does not have more than 1 entry on the results
	}
	
}
