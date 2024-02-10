package com.example.vigoinfotechassignment.Services;

import com.example.vigoinfotechassignment.Model.Product;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("products/2")
    Call<Product> getProduct();
}
