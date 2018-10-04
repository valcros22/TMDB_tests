package com.tmdb.interfaces;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import com.tmdb.companies.CompanyItem;

public interface CompaniesAPIService {
 
    @GET("/3/company/{company_id}")
    public Call<CompanyItem> fetchCompany(@Path("company_id") String companyId, @Query("api_key") String apiKey);
	
}
