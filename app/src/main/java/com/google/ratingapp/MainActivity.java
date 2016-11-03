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
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
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

        final Animation animationFadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        final Animation animationFadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);

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

        final Counter counter = new Counter();
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







                if(counter.getCounter() == 0) {

                    tv1.animate().setDuration(1000).translationY((float) (-2.5 * (et1.getHeight()))).start();

                    ratingBar.animate().setDuration(1000).translationY(-2*(et1.getHeight())).start();

                    tv1.startAnimation(animationFadeOut);
                    Log.d("get Y Before Set In IF", String.valueOf(tv1.getY()));
                    //tv1.setY(viewRateCarHeight);
                    Log.d("get Y After Set In IF", String.valueOf(tv1.getY()));
                    tv1.startAnimation(animationFadeIn);

                    //r1.startAnimation(animTranslate);


                    //r1.startAnimation(animationFadeOut);
                    // startAlphaAnimation(r1, 2000, View.INVISIBLE);
                    r1.startAnimation(animationFadeOut);
                   // r1.setY(r1Height);
                    //startAlphaAnimation(r1, 2000, View.VISIBLE);
                    r1.startAnimation(animationFadeIn);



                    et1.setVisibility(View.VISIBLE);
                    et1.startAnimation(animationFadeIn);
                    et1.setEnabled(true);
                    btn1.setVisibility(View.VISIBLE);
                    btn1.startAnimation(animationFadeIn);
                    btn1.setEnabled(true);
                    counter.increament();
                }


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

    public static void startAlphaAnimation(View v, long duration, int visibility){
        AlphaAnimation alphaAnimation = (visibility == View.VISIBLE)
                ? new AlphaAnimation(0f, 1f)
                : new AlphaAnimation(1f,0f);
        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);
        v.startAnimation(alphaAnimation);
    }

}
