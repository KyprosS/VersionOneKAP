package com.example.kypros.versiononekap;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ChildCategoriesDynamicActivity extends BaseActivity {

    SwipeRefreshLayout mySwipeRefreshLayoutSearch;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //ADD BURGER MENU DYNAMICALY START ---------------------------------------------------------
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_child_categories_dynamic, contentFrameLayout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(0).setChecked(true);
        //ADD BURGER MENU END ----------------------------------------------------------------------



        Intent mIntent = getIntent();
        Integer parent_id = mIntent.getIntExtra("parentID", 0);
        Log.d("THIS!!!!!!!!!!!!!!!!!!!", "Value: " + parent_id);



























/*

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

*/




    }//onCreate Ends here







}
