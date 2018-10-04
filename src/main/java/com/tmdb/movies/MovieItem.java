package com.tmdb.movies;

import java.util.List;

public class MovieItem {
    int id;
    String name;
    List<MovieCompaniesItem> production_companies;
 
    public int getId() {
        return this.id;
    }
 
    public String getName() {
        return this.name;
    }
    
    public List<MovieCompaniesItem> getProductionCompaniesList() {
        return this.production_companies;
    }
}
