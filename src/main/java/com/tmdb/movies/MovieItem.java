package com.tmdb.movies;

import java.util.List;

public class MovieItem {
	
    int id;
    String title;
    List<MovieCompaniesItem> production_companies;
	String release_date;
    String original_language;
    List<MovieGenreItem> genres;
    
    
    public int getId() {
        return this.id;
    }
 
    public String getTitle() {
        return this.title;
    }
    
    public List<MovieCompaniesItem> getProductionCompaniesList() {
        return this.production_companies;
    }
    
    public String getReleaseDate() {
		return this.release_date;
	}

	public String getOriginalLanguage() {
		return this.original_language;
	}

	public List<MovieGenreItem> getGenres() {
		return this.genres;
	}
}
