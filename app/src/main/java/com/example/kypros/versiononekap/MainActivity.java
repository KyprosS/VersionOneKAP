package com.example.kypros.versiononekap;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;
import com.melnykov.fab.FloatingActionButton;
import android.support.design.widget.NavigationView;

public class MainActivity extends BaseActivity {

    boolean doubleBackToExitPressedOnce = false;
    FloatingActionButton floatingSearchIcon;
    SwipeRefreshLayout mySwipeRefreshLayout;

    ImageView imVtechnicians_cat;
    ImageView imVemergencies_cat;
    ImageView imVhousehold_cat;
    ImageView imVothers_cat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //ADD BURGER MENU DYNAMICALY START ---------------------------------------------------------
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_main, contentFrameLayout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(0).setChecked(true); //Highlight selected item in menu
        //ADD BURGER MENU END ----------------------------------------------------------------------


        //BEGIN SET SESSION ID AND IMAGEVIEWS TO ANOTHER ACTIVITY-------------------------------------------
        imVtechnicians_cat = (ImageView) findViewById(R.id.technicians_cat);
        imVtechnicians_cat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                int sessionId = 1;

                Intent myIntent = new Intent(getBaseContext(), ChildCategoriesDynamicActivity.class);
                myIntent.putExtra("PARENT_ID", sessionId);

                startActivity(myIntent);
            }
        });

        imVemergencies_cat = (ImageView) findViewById(R.id.emergencies_cat);
        imVemergencies_cat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                int sessionId = 2;

                Intent myIntent = new Intent(getBaseContext(), ChildCategoriesDynamicActivity.class);
                myIntent.putExtra("PARENT_ID", sessionId);

                startActivity(myIntent);
            }
        });

        imVhousehold_cat = (ImageView) findViewById(R.id.household_cat);
        imVhousehold_cat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                int sessionId = 3;

                Intent myIntent = new Intent(getBaseContext(), ChildCategoriesDynamicActivity.class);
                myIntent.putExtra("PARENT_ID", sessionId);

                startActivity(myIntent);
            }
        });

        imVothers_cat = (ImageView) findViewById(R.id.others_cat);
        imVothers_cat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                int sessionId = 4;

                Intent myIntent = new Intent(getBaseContext(), ChildCategoriesDynamicActivity.class);
                myIntent.putExtra("PARENT_ID", sessionId);

                startActivity(myIntent);
            }
        });

        //END SET SESSION ID AND IMAGEVIEWS TO ANOTHER ACTIVITY-------------------------------------------





        //OnClick Search button jump to activity SearchResults START ------------------------------
        floatingSearchIcon = (FloatingActionButton) findViewById(R.id.floatingSearchIcon);

        floatingSearchIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent myIntent = new Intent(MainActivity.this, SearchResults.class);
                startActivity(myIntent);
            }
        });
        //On Search button click jump to activity SearchResults END --------------------------------



        //DRAG DOWN TO REFRESH LAYOUT STARTS--------------------------------------------------------
        mySwipeRefreshLayout = (SwipeRefreshLayout)this.findViewById(R.id.swipeContainer);

        mySwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        finish();
                        startActivity(getIntent());
                    }
                }
        );
        //DRAG DOWN TO REFRESH LAYOUT ENDS----------------------------------------------------------



    }//onCreate ENDS HERE




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



}//END MAIN CLASS HERE