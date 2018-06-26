package com.example.kypros.versiononekap;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;
import com.melnykov.fab.FloatingActionButton;
import android.app.Activity;


public class MainActivity extends Activity {

    boolean doubleBackToExitPressedOnce = false;
    FloatingActionButton floatingSearchIcon;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Grid View declaration
        GridView gridView = (GridView) findViewById(R.id.grid_view);
        gridView.setAdapter(new ImageAdapter(this));




        //On Search button click jump to activity SearchResults START ------------------------------
        floatingSearchIcon = (FloatingActionButton) findViewById(R.id.floatingSearchIcon);

        floatingSearchIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent myIntent = new Intent(MainActivity.this,
                        SearchResults.class);
                startActivity(myIntent);
            }
        });
        //On Search button click jump to activity SearchResults END --------------------------------




    }




//DISABLE BACK BUTTON TO GO TO WELCOME SCREEN START-------------------------------------------------
    @Override
    public void onBackPressed() {

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Press Back again if you want to exit.", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
//DISABLE BACK BUTTON TO GO TO WELCOME SCREEN END---------------------------------------------------



}