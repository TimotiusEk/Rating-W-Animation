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


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Animation animationFadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        final Button rateBtn = (Button) findViewById(R.id.journeyExpButton);
        final EditText reviewField = (EditText) findViewById(R.id.journeyExpEditText);
        final RatingBar ratingBar = (RatingBar) findViewById(R.id.journeyExpRatingBar);
        final TextView textView = (TextView) findViewById(R.id.journeyExpTextView);
        final Bundle extra = new Bundle();


        reviewField.setVisibility(View.INVISIBLE);
        reviewField.setEnabled(false);
        reviewField.setScroller(new Scroller(this));
        reviewField.setMaxLines(2);
        reviewField.setVerticalScrollBarEnabled(true);
        reviewField.setMovementMethod(new ScrollingMovementMethod());

        rateBtn.setVisibility(View.INVISIBLE);
        rateBtn.setEnabled(false);

        final Counter counter = new Counter();

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {


            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {


                Toast.makeText(MainActivity.this, "You Rate This Journey Experience " + String.valueOf(ratingBar.getRating()) , Toast.LENGTH_SHORT).show();

                if(counter.getCounter() == 0) {

                    textView.animate().setDuration(1000).translationY((float) (-2*(reviewField.getHeight()))).start();

                    ratingBar.animate().setDuration(1000).translationY(-2*(reviewField.getHeight())).start();

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
                Intent intent = new Intent(MainActivity.this, RateCarActivity.class);
                extra.putFloat("journeyExperienceRating", ratingBar.getRating());
                if(!reviewField.getText().toString().equalsIgnoreCase("")){
                    extra.putString("journeyExperienceReview", reviewField.getText().toString());
                }
                intent.putExtras(extra);
                startActivity(intent);
            }
        });
    }

}
