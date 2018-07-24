package com.example.kypros.versiononekap;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.Toast;
import com.melnykov.fab.FloatingActionButton;
import android.support.design.widget.NavigationView;

public class MainActivity extends BaseActivity {

    boolean doubleBackToExitPressedOnce = false;
    FloatingActionButton floatingSearchIcon;
    SwipeRefreshLayout mySwipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ADD BURGER MENU DYNAMICALY START ---------------------------------------------------------
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_main, contentFrameLayout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(0).setChecked(true); //Highlight selected item in menu
        //ADD BURGER MENU END ----------------------------------------------------------------------


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










        //Grid View declaration
        GridView gridView = (GridView) findViewById(R.id.grid_view);
        gridView.setAdapter(new ImageAdapter(this));


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                Intent i = new Intent(getApplicationContext(), ChildCategoriesDynamicActivity.class);

                i.putExtra("parentID", position);

                startActivity(i);
            }
        });












        //OnClick Search button jump to activity SearchResults START ------------------------------
        floatingSearchIcon = (FloatingActionButton) findViewById(R.id.floatingSearchIcon);

        floatingSearchIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent myIntent = new Intent(MainActivity.this, SearchResults.class);
                startActivity(myIntent);
            }
        });
        //On Search button click jump to activity SearchResults END --------------------------------






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