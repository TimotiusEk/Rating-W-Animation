package com.google.ratingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toast;

public class RateDriverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_driver);

        final Animation animationFadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        final Counter counter = new Counter();

        final Button rateBtn = (Button) findViewById(R.id.driverButton);
        final Button skipBtn = (Button) findViewById(R.id.skipBtn);
        final EditText reviewField = (EditText) findViewById(R.id.driverEditText);
        final RatingBar ratingBar = (RatingBar) findViewById(R.id.driverRatingBar);
        final TextView textView = (TextView) findViewById(R.id.driverTextView);

        final Bundle extraContainer = new Bundle();
        final Intent intent = new Intent(RateDriverActivity.this, ShowResult.class);

        final Intent intentFromOtherActivity = getIntent();
        final Bundle extraFromOtherActivity = intentFromOtherActivity.getExtras();

        reviewField.setVisibility(View.INVISIBLE);
        reviewField.setEnabled(false);
        reviewField.setScroller(new Scroller(this));
        reviewField.setMaxLines(2);
        reviewField.setVerticalScrollBarEnabled(true);
        reviewField.setMovementMethod(new ScrollingMovementMethod());

        rateBtn.setVisibility(View.INVISIBLE);
        rateBtn.setEnabled(false);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(RateDriverActivity.this, "You Rate This Driver " + String.valueOf(ratingBar.getRating()), Toast.LENGTH_SHORT).show();

                if (counter.getCounter() == 0) {

                    textView.animate().setDuration(500).translationY((float) (-2 * (textView.getHeight()))).start();

                    ratingBar.animate().setDuration(500).translationY(-2 * (textView.getHeight())).start();

                    reviewField.setVisibility(View.VISIBLE);
                    reviewField.startAnimation(animationFadeIn);
                    reviewField.setEnabled(true);

                    rateBtn.setVisibility(View.VISIBLE);
                    rateBtn.startAnimation(animationFadeIn);
                    rateBtn.setEnabled(true);

                    counter.increament();
                }
            }
        });

        rateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                extraContainer.putFloat("driverRating", ratingBar.getRating());
                extraContainer.putFloat("carRating", extraFromOtherActivity.getFloat("carRating"));
                extraContainer.putFloat("journeyExperienceRating", extraFromOtherActivity.getFloat("journeyExperienceRating"));

                //if(!reviewField.getText().toString().equalsIgnoreCase("")){
                extraContainer.putString("driverReview", reviewField.getText().toString());
                //}

                //if(extraFromOtherActivity.getString("journeyExperienceReview") != null) {
                extraContainer.putString("carReview", extraFromOtherActivity.getString("carReview"));
                //}

                //if(extraFromOtherActivity.getString("carReview") != null) {
                extraContainer.putString("journeyExperienceReview", extraFromOtherActivity.getString("journeyExperienceReview"));
                // }

                intent.putExtras(extraContainer);
                startActivity(intent);
            }
        });

        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                extraContainer.putFloat("driverRating", -1);
                extraContainer.putFloat("carRating", -1);
                extraContainer.putFloat("journeyExperienceRating", -1);

                //if(!reviewField.getText().toString().equalsIgnoreCase("")){
                extraContainer.putString("driverReview", null);
                //}

                //if(extraFromOtherActivity.getString("journeyExperienceReview") != null) {
                extraContainer.putString("carReview", null);
                //}

                //if(extraFromOtherActivity.getString("carReview") != null) {
                extraContainer.putString("journeyExperienceReview", null);
                // }

                intent.putExtras(extraContainer);
                startActivity(intent);
            }
        });

    }
}

