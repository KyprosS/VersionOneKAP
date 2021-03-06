package com.example.kypros.versiononekap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WelcomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        //ACTIVITY TIMER STARTS---------------------------------------------------------------------
        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(500);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent intent = new Intent(WelcomeScreen.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        timerThread.start();
        //ACTIVITY TIMER END------------------------------------------------------------------------

    }
}
