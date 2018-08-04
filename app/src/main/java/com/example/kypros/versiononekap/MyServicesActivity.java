package com.example.kypros.versiononekap;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.google.firebase.database.FirebaseDatabase;
import com.melnykov.fab.FloatingActionButton;

public class MyServicesActivity extends BaseActivity {

    SwipeRefreshLayout mySwipeRefreshLayoutSearch;
    FloatingActionButton floatingAddIcon;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ADD BURGER MENU DYNAMICALY START ---------------------------------------------------------
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_my_services, contentFrameLayout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(2).setChecked(true);
        //ADD BURGER MENU END ----------------------------------------------------------------------


        //DRAG DOWN TO REFRESH LAYOUT STARTS--------------------------------------------------------
        mySwipeRefreshLayoutSearch = (SwipeRefreshLayout) this.findViewById(R.id.swipeContainer);

        mySwipeRefreshLayoutSearch.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        finish();
                        startActivity(getIntent());
                    }
                }
        );
        //DRAG DOWN TO REFRESH LAYOUT ENDS----------------------------------------------------------

        //OnClick Search button jump to activity SearchResults START -------------------------------
        floatingAddIcon = (FloatingActionButton) findViewById(R.id.floatingSearchIcon);

        floatingAddIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent myIntent = new Intent(MyServicesActivity.this, AddNewServiceActivity.class);
                startActivity(myIntent);
            }
        });
        //On Search button click jump to activity SearchResults END --------------------------------


    }//onCreate END





}
