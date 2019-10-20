package com.moringaschool.craftypictures;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchGalleryActivity extends AppCompatActivity {
    private static final String TAG = SearchGalleryActivity.class.getSimpleName();

    @BindView(R.id.locationTextView) TextView mLocationTextView;
    @BindView(R.id.listView) ListView mListView;
    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_gallery);
        ButterKnife.bind(this);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String gallery = ((TextView)view).getText().toString();
                Toast.makeText(SearchGalleryActivity.this, gallery, Toast.LENGTH_LONG).show();
            }
        });

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        mLocationTextView.setText("Here are all the art galleries near: " + location);

        YelpApi client = YelpClient.getClient();

        Call<YelpGalleriesSearchResponse> call = client.getGalleries(location, "art gallery");

        call.enqueue(new Callback<YelpGalleriesSearchResponse>() {
            @Override
            public void onResponse(Call<YelpGalleriesSearchResponse> call, Response<YelpGalleriesSearchResponse> response) {
                hideProgressBar();

                if (response.isSuccessful()) {
                    List<Business> galleriesList = response.body().getBusinesses();
                    String[] galleries = new String[galleriesList.size()];
                    String[] categories = new String[galleriesList.size()];

                    for (int i = 0; i < galleries.length; i++){
                        galleries[i] = galleriesList.get(i).getName();
                    }

                    for (int i = 0; i < categories.length; i++) {
                        Category category = galleriesList.get(i).getCategories().get(0);
                        categories[i] = category.getTitle();
                    }

                    ArrayAdapter adapter = new GalleriesArrayAdapter(SearchGalleryActivity.this, android.R.layout.simple_list_item_1, galleries, categories);
                    mListView.setAdapter(adapter);

                    showGalleries();
                } else {
                    showUnsuccessfulMessage();
                }
            }

            @Override
            public void onFailure(Call<YelpGalleriesSearchResponse> call, Throwable t) {
                hideProgressBar();
                showFailureMessage();
            }

        });
    }

    private void showFailureMessage() {
        mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showUnsuccessfulMessage() {
        mErrorTextView.setText("Something went wrong. Please try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showGalleries() {
        mListView.setVisibility(View.VISIBLE);
        mLocationTextView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }
}
