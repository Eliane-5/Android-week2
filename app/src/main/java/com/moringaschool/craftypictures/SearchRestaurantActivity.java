package com.moringaschool.craftypictures;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import retrofit2.Call;

public class SearchRestaurantActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_restaurant);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");



        YelpApi client = YelpClient.getClient();

        Call<YelpRestaurantsSearchResponse> call = client.getRestaurants(location, "restaurants");
    }
}
