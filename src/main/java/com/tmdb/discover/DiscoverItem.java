package com.tmdb.discover;

public class DiscoverItem {
	
    int id;
    String title;
	int[] genre_ids;
    String release_date;
    String original_language;
 
    public int getId() {
        return this.id;
    }
 
    public String getTitle() {
        return this.title;
    }
    
    public int[] getGenreIds() {
		return this.genre_ids;
	}

	public String getReleaseDate() {
		return this.release_date;
	}

	public String getOriginalLanguage() {
		return this.original_language;
	}

}
