package com.google.ratingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
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
        final Button btn3 = (Button) findViewById(R.id.driverButton);
        final EditText et3 = (EditText) findViewById(R.id.driverEditText);
        final RatingBar r3 = (RatingBar) findViewById(R.id.driverRatingBar);
        final TextView tv3 = (TextView) findViewById(R.id.driverTextView);
        final Intent intentFromMain = getIntent();
        final Bundle extraFromMain = intentFromMain.getExtras();
        final Bundle extra3 = new Bundle();

        et3.setVisibility(View.INVISIBLE);
        et3.setEnabled(false);
        et3.setScroller(new Scroller(this));
        et3.setMaxLines(2);
        et3.setVerticalScrollBarEnabled(true);
        et3.setMovementMethod(new ScrollingMovementMethod());

        btn3.setVisibility(View.INVISIBLE);
        btn3.setEnabled(false);

        final DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        final double dpHeight = displayMetrics.heightPixels / displayMetrics.density;
        r3.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                int r1Height = (int) (dpHeight * 0.5);
                int viewRateCarHeight = (int) (dpHeight * 0.4);
                Toast.makeText(RateDriverActivity.this, "You Rate This Driver " + String.valueOf(r3.getRating()) , Toast.LENGTH_SHORT).show();


                r3.setY(r1Height);
                tv3.setY(viewRateCarHeight);
                et3.setVisibility(View.VISIBLE);
                et3.setEnabled(true);
                btn3.setVisibility(View.VISIBLE);
                btn3.setEnabled(true);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RateDriverActivity.this, ShowResult.class);
                extra3.putFloat("driverRating", r3.getRating());
                extra3.putFloat("carRating", extraFromMain.getFloat("carRating"));
                extra3.putFloat("journeyExperienceRating", extraFromMain.getFloat("journeyExperienceRating"));

                if(!et3.getText().toString().equalsIgnoreCase("")){
                    extra3.putString("driverReview", et3.getText().toString());
                }

                if(extraFromMain.getString("journeyExperienceReview") != null) {
                    extra3.putString("carReview", extraFromMain.getString("carReview"));
                }

                if(extraFromMain.getString("carReview") != null) {
                    extra3.putString("journeyExperienceReview", extraFromMain.getString("journeyExperienceReview"));
                }

                intent.putExtras(extra3);
                startActivity(intent);
            }
        });
    }
}

