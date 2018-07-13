package com.example.kypros.versiononekap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class WelcomeScreen extends AppCompatActivity {

    ProgressBar progressBarWelcome; //Progress Bar on WelcomeScreen declaration.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

/*
        //PROGRESS BAR ANIMATION STARTS-------------------------------------------------------------
        progressBarWelcome = (ProgressBar) findViewById(R.id.progressBarWelcome);

        ValueAnimator animator = ValueAnimator.ofInt(0, progressBarWelcome.getMax());
        animator.setDuration(3000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation){
                progressBarWelcome.setProgress((Integer)animation.getAnimatedValue());
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

            }
        });
        animator.start();
        //PROGRESS BAR ANIMATION END----------------------------------------------------------------
*/

        //ACTIVITY TIMER STARTS---------------------------------------------------------------------
        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(1000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent intent = new Intent(WelcomeScreen.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        };
        timerThread.start();
        //ACTIVITY TIMER END------------------------------------------------------------------------

    }
}
