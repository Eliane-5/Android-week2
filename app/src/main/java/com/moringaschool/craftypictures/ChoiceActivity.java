package com.moringaschool.craftypictures;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChoiceActivity extends AppCompatActivity {
    @BindView(R.id.userNameTextView) TextView mUserNameTextView;
    @BindView(R.id.blackAndWhite) Button mBlackAndWhiteButton;
    @BindView(R.id.colorful) Button mColorfulButton;
    @BindView(R.id.exhibition) Button mExhibitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String userName = intent.getStringExtra("userName");
        mUserNameTextView.setText("Hello "+  userName +" have fun!");

        mBlackAndWhiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChoiceActivity.this, BlackAndWhiteActivity.class);
                startActivity(intent);
                Toast.makeText(ChoiceActivity.this, "Black and White pictures it is!", Toast.LENGTH_LONG).show();
            }
        });

        mColorfulButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChoiceActivity.this, ColorfulActivity.class);
                startActivity(intent);
                Toast.makeText(ChoiceActivity.this, "Colorful pictures it is!", Toast.LENGTH_LONG).show();
            }
        });
        mExhibitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChoiceActivity.this, LocationActivity.class);
                startActivity(intent);
                Toast.makeText(ChoiceActivity.this, "Enter your location to know which exhibition places are in your area!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
