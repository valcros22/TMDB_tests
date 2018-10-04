package com.tmdb.movies;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

import com.tmdb.interfaces.MoviesAPIService;
import com.tmdb.RetrofitClient;
import com.tmdb.companies.Companies;

public class Movies extends RetrofitClient{
	
	private String url = "";
	private String apiKey = "";
	private MoviesAPIService client = null;
	
	public Movies(String url, String apiKey){
		this.url = url;
		this.apiKey = apiKey;
		this.client = super.getRetrofitClient(this.url).create(MoviesAPIService.class);
	}
	
	public List<MovieCompaniesItem> getMovieCompanies(int movieId){
        Call<MovieItem> call = this.client.fetchMovie(Integer.toString(movieId), this.apiKey);
        try {
			Response<MovieItem> response = call.execute();
			return response.body().getProductionCompaniesList();
		} catch (IOException e) {
			System.out.println("There was an error in movie companies request : ");
			e.printStackTrace();
		}
        return null;
	}
	
	public boolean movieCompaniesExistAndMatchName(int movieId){
		List<MovieCompaniesItem> companiesList = this.getMovieCompanies(movieId);
		if ((companiesList != null) && !companiesList.isEmpty()){
			Companies companyObject = new Companies(this.url, this.apiKey);
			for (MovieCompaniesItem companyItem : companiesList){
				if (! companyObject.isCompanyNameEqual(companyItem.id, companyItem.name)){
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	public MovieItem getMovieData(int movieId){
        Call<MovieItem> call = this.client.fetchMovie(Integer.toString(movieId), this.apiKey);
        try {
			Response<MovieItem> response = call.execute();
        	if (response.code() != 401){
        		return response.body();
        	}
        	else{
        		System.out.println("The API key "+ this.apiKey +" was unauthorized");
        		return null;
        	}
		} catch (IOException e) {
			System.out.println("There was an error in movie request : ");
			e.printStackTrace();
		}
        return null;
	}
}
