package com.example.vigoinfotechassignment.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vigoinfotechassignment.Model.Product;
import com.example.vigoinfotechassignment.R;
import com.example.vigoinfotechassignment.RetrofitClient;
import com.example.vigoinfotechassignment.Services.ApiService;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiOutputActivity extends AppCompatActivity {

    private ApiService apiService;
    private TextView tvProductTitle, tvProductDescription, tvProductPrice, tvProductRating, tvProductStock, tvProductBrand, tvProductCategory;
    private ImageView ivProductThumbnail;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_output);

        apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

        tvProductTitle = findViewById(R.id.tvProductTitle);
        tvProductDescription = findViewById(R.id.tvProductDescription);
        tvProductPrice = findViewById(R.id.tvProductPrice);
        tvProductRating = findViewById(R.id.tvProductRating);
        tvProductStock = findViewById(R.id.tvProductStock);
        tvProductBrand = findViewById(R.id.tvProductBrand);
        tvProductCategory = findViewById(R.id.tvProductCategory);
        ivProductThumbnail = findViewById(R.id.ivProductThumbnail);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading..");
        progressDialog.setCancelable(false);

        Button btnFetchData = findViewById(R.id.btnFetchData);
        btnFetchData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchDataFromApi();
            }
        });
    }

    private void fetchDataFromApi(){
        progressDialog.show();
        Call<Product> call = apiService.getProduct();

        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if(response.isSuccessful()){
                    progressDialog.dismiss();
                    Toast.makeText(ApiOutputActivity.this, "Data Feteched", Toast.LENGTH_SHORT).show();

                    Product product = response.body();
                    if (product!=null){

                        tvProductTitle.setText(product.getTitle());
                        tvProductDescription.setText(product.getDescription());
                        tvProductPrice.setText(String.valueOf(product.getPrice()));
                        tvProductRating.setText(String.valueOf(product.getRating()));
                        tvProductStock.setText(String.valueOf(product.getStock()));
                        tvProductBrand.setText(product.getBrand());
                        tvProductCategory.setText(product.getCategory());

                        Picasso.get().load(product.getThumbnail()).into(ivProductThumbnail);
                    }
                }else {

                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                progressDialog.dismiss();
                Log.e("ApiOutputActivity", "API Call Failed",t);
            }
        });
    }
}