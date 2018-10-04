package com.tmdb.companies;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

import com.tmdb.interfaces.CompaniesAPIService;
import com.tmdb.RetrofitClient;

public class Companies extends RetrofitClient{
	
	private String url = "";
	private String apiKey = "";
	private CompaniesAPIService client = null;
	
	public Companies(String url, String apiKey){
		this.url = url;
		this.apiKey = apiKey;
		this.client = super.getRetrofitClient(this.url).create(CompaniesAPIService.class);
	}
	
	public CompanyItem getCompany(int companyId){
		if (companyId >= 0){
	        Call<CompanyItem> call = this.client.fetchCompany(Integer.toString(companyId), this.apiKey);
	        try {
				Response<CompanyItem> response = call.execute();
				return response.body();
			} catch (IOException e) {
				System.out.println("There was an error in company request : ");
				e.printStackTrace();
			}
		}
        return null;
	}
	
	public boolean isCompanyNameEqual(int companyId, String companyName){
		if (companyId >= 0){
			CompanyItem company = this.getCompany(companyId);
			if (company.name.equals(companyName)){
				return true;
			}
		}
		return false;	
	}
	
	public boolean isCompanyPresent(int companyId){
		if (companyId >= 0){
			CompanyItem company = this.getCompany(companyId);
			if (company != null){
				return true;
			}
		}
		return false;	
	}

}


