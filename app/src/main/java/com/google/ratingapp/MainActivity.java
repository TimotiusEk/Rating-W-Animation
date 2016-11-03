package com.google.ratingapp;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.media.Rating;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //final TranslateAnimation translate = new TranslateAnimation(Animation.RELATIVE_TO_SELF, Animation.RELATIVE_TO_SELF, Animation.RELATIVE_TO_SELF, Animation.RELATIVE_TO_SELF,Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, (float) (Animation.RELATIVE_TO_PARENT*0.9));

       // translate.setDuration(1000);
        final Animation animTranslate = AnimationUtils.loadAnimation(this, R.anim.anim_translate);

        final Button btn1 = (Button) findViewById(R.id.journeyExpButton);
        final EditText et1 = (EditText) findViewById(R.id.journeyExpEditText);
        final RatingBar r1 = (RatingBar) findViewById(R.id.journeyExpRatingBar);
        final TextView tv1 = (TextView) findViewById(R.id.journeyExpTextView);
        final Bundle extra = new Bundle();


        et1.setVisibility(View.INVISIBLE);
        et1.setEnabled(false);
        et1.setScroller(new Scroller(this));
        et1.setMaxLines(2);
        et1.setVerticalScrollBarEnabled(true);
        et1.setMovementMethod(new ScrollingMovementMethod());

        btn1.setVisibility(View.INVISIBLE);
        btn1.setEnabled(false);


        final DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        final double dpHeight = displayMetrics.heightPixels / displayMetrics.density;
        r1.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {



            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                int r1Height = (int) (dpHeight * 0.5);
                int viewRateCarHeight = (int) (dpHeight * 0.4);
                Toast.makeText(MainActivity.this, "You Rate This Journey Experience " + String.valueOf(r1.getRating()) , Toast.LENGTH_SHORT).show();

                /*ObjectAnimator anim = ObjectAnimator.ofFloat(ratingBar, "rating", current, 5f);
                anim.setDuration(1000);
                anim.start();*/
                //if(ratingBar.getY() != r1Height) {
                    //ratingBar.startAnimation(translate);
                //}


                    //ratingBar.animate().setDuration(100000).y(r1Height).start();



               //r1.startAnimation(animTranslate);


                r1.setY(r1Height);


                tv1.setY(viewRateCarHeight);
                et1.setVisibility(View.VISIBLE);
                et1.setEnabled(true);
                btn1.setVisibility(View.VISIBLE);
                btn1.setEnabled(true);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RateCarActivity.class);
                extra.putFloat("journeyExperienceRating", r1.getRating());
                if(!et1.getText().toString().equalsIgnoreCase("")){
                    extra.putString("journeyExperienceReview", et1.getText().toString());
                    //Log.d("Str Journey Experience", "Input Successful");
                }
                intent.putExtras(extra);
                startActivity(intent);
            }
        });
    }
}
