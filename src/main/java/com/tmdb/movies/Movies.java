package com.tmdb.movies;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

import com.tmdb.interfaces.MoviesAPIService;
import com.tmdb.utils.RetrofitClient;
import com.tmdb.companies.Companies;

public class Movies {
	
	private String url = "";
	private String apiKey = "";
	
	public Movies(String url, String apiKey){
		this.url = url;
		this.apiKey = apiKey;
	}
	
	public List<MovieCompaniesItem> getMovieCompanies(int movieId){
		MoviesAPIService client = RetrofitClient.getClient(this.url).create(MoviesAPIService.class);
        Call<MovieItem> call = client.fetchMovie(Integer.toString(movieId), this.apiKey);
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
		MoviesAPIService client = RetrofitClient.getClient(this.url).create(MoviesAPIService.class);
        Call<MovieItem> call = client.fetchMovie(Integer.toString(movieId), this.apiKey);
        try {
			Response<MovieItem> response = call.execute();
			return response.body();
		} catch (IOException e) {
			System.out.println("There was an error in movie request : ");
			e.printStackTrace();
		}
        return null;
	}
}
