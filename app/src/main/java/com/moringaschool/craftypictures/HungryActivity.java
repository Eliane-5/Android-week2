package com.moringaschool.craftypictures;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HungryActivity extends AppCompatActivity {
    public static final String TAG = HungryActivity.class.getSimpleName();
    @BindView(R.id.locationEditText) EditText mLocationEditText;
    @BindView(R.id.submitHungryButton) Button mSubmitHungryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hungry);
        ButterKnife.bind(this);

        Intent intent = getIntent();

        mSubmitHungryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String location = mLocationEditText.getText().toString();
                Log.d(TAG, location);
                Intent intent = new Intent(HungryActivity.this, SearchRestaurantsActivity.class);
                startActivity(intent);
            }
        });
    }
}
