package com.example.kypros.versiononekap;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;
import com.melnykov.fab.FloatingActionButton;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;

public class MainActivity extends AppCompatActivity {

    boolean doubleBackToExitPressedOnce = false;
    FloatingActionButton floatingSearchIcon;
    SwipeRefreshLayout mySwipeRefreshLayout;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Grid View declaration
        GridView gridView = (GridView) findViewById(R.id.grid_view);
        gridView.setAdapter(new ImageAdapter(this));


        //OnClick Search button jump to activity SearchResults START ------------------------------
        floatingSearchIcon = (FloatingActionButton) findViewById(R.id.floatingSearchIcon);

        floatingSearchIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent myIntent = new Intent(MainActivity.this,
                        SearchResults.class);
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












//BURGER MENU DRAWER STARTS-------------------------------------------------------------------------
        dl = (DrawerLayout)findViewById(R.id.activity_main);
        t = new ActionBarDrawerToggle(this, dl, R.string.Open, R.string.Close);

        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView)findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                switch(id)
                {
                    case R.id.profile:
                        Intent myIntent = new Intent(MainActivity.this,
                                ProfileActivity.class);
                        startActivity(myIntent);

                        return true;

                    case R.id.my_services:
                        Intent myIntent2 = new Intent(MainActivity.this,
                                MyServicesActivity.class);
                        startActivity(myIntent2);

                        return true;

                    case R.id.add_new_services:
                        Intent myIntent3 = new Intent(MainActivity.this,
                                AddNewServiceActivity.class);
                        startActivity(myIntent3);

                        return true;

                    case R.id.favorites:
                        Intent myIntent4 = new Intent(MainActivity.this,
                                FavoritesActivity.class);
                        startActivity(myIntent4);

                        return true;

                    case R.id.settings:
                        Intent myIntent5 = new Intent(MainActivity.this,
                                SettingsActivity.class);
                        startActivity(myIntent5);

                        return true;

                    case R.id.sign_in:
                        Intent myIntent6 = new Intent(MainActivity.this,
                                SignInActivity.class);
                        startActivity(myIntent6);

                        return true;

                    case R.id.sign_up:
                        Intent myIntent7 = new Intent(MainActivity.this,
                                SignUpActivity.class);
                        startActivity(myIntent7);

                        return true;

                    case R.id.help_feedback:
                        Intent myIntent8 = new Intent(MainActivity.this,
                                HelpFeedbackActivity.class);
                        startActivity(myIntent8);

                        return true;

                    default:
                        return true;
                }
            }
        });
        //BURGER MENU DRAWER ENDS-------------------------------------------------------------------




    }//onCreate ENDS HERE





    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
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



}//END MAIN CLASS HERE