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

public class RateCarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_car);
        final Button btn2 = (Button) findViewById(R.id.carButton);
        final EditText et2 = (EditText) findViewById(R.id.carEditText);
        final RatingBar r2 = (RatingBar) findViewById(R.id.carRatingBar);
        final TextView tv2 = (TextView) findViewById(R.id.carTextView);



        et2.setVisibility(View.INVISIBLE);
        et2.setEnabled(false);
        et2.setScroller(new Scroller(this));
        et2.setMaxLines(2);
        et2.setVerticalScrollBarEnabled(true);
        et2.setMovementMethod(new ScrollingMovementMethod());

        btn2.setVisibility(View.INVISIBLE);
        btn2.setEnabled(false);


        final DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        final double dpHeight = displayMetrics.heightPixels / displayMetrics.density;
        r2.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                int r1Height = (int) (dpHeight * 0.5);
                int viewRateCarHeight = (int) (dpHeight * 0.4);
                Toast.makeText(RateCarActivity.this, "You Rate This Car " + String.valueOf(r2.getRating()) , Toast.LENGTH_SHORT).show();


                r2.setY(r1Height);
                r2.animate();
                tv2.setY(viewRateCarHeight);
                et2.setVisibility(View.VISIBLE);
                et2.setEnabled(true);
                btn2.setVisibility(View.VISIBLE);
                btn2.setEnabled(true);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(RateCarActivity.this, RateDriverActivity.class);
                Intent intentFromMain = getIntent();
                Bundle extraFromMain = intentFromMain.getExtras();
                Bundle extra2 = new Bundle();
                String journeyReview = extraFromMain.getString("journeyExperienceReview");
                Float journeyRating = extraFromMain.getFloat("journeyExperienceRating");
                extra2.putFloat("carRating", r2.getRating());
                extra2.putFloat("journeyExperienceRating", journeyRating);
                if(!et2.getText().toString().equalsIgnoreCase("")){
                    extra2.putString("carReview", et2.getText().toString());
                    //Log.d("car Experience", "Input Successful");
                }
                if(journeyReview != null) {
                    extra2.putString("journeyExperienceReview", journeyReview);
                    //Log.d("journey exp","input for the second time");
                }
                intent2.putExtras(extra2);
                startActivity(intent2);
            }
        });
    }
}

