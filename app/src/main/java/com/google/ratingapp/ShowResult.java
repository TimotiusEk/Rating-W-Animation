package com.google.ratingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ShowResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_result);
        final Intent intentFromMain = getIntent();
        final Bundle extraFromMain = intentFromMain.getExtras();

        final TextView carRating = (TextView) findViewById(R.id.textView2);
        final TextView journeyRating = (TextView) findViewById(R.id.textView4);
        final TextView driverRating = (TextView) findViewById(R.id.textView6);

        final TextView carReview= (TextView) findViewById(R.id.textView9);
        final TextView journeyReview = (TextView) findViewById(R.id.textView11);
        final TextView driverReview = (TextView) findViewById(R.id.textView14);

        carRating.setText(String.valueOf(extraFromMain.getFloat("carRating")));
        journeyRating.setText(String.valueOf(extraFromMain.getFloat("journeyExperienceRating")));
        driverRating.setText(String.valueOf(extraFromMain.getFloat("driverRating")));

        carReview.setText(extraFromMain.getString("carReview"));
        journeyReview.setText(extraFromMain.getString("journeyExperienceReview"));
        driverReview.setText(extraFromMain.getString("driverReview"));


    }
}
