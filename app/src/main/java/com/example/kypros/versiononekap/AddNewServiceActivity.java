package com.example.kypros.versiononekap;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.google.firebase.auth.FirebaseAuth;

public class AddNewServiceActivity extends BaseActivity {


    private FirebaseAuth auth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ADD BURGER MENU DYNAMICALY START ---------------------------------------------------------
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_add_new_service, contentFrameLayout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(3).setChecked(true);
        //ADD BURGER MENU END ----------------------------------------------------------------------

        auth = FirebaseAuth.getInstance();

        //If user is not logged in
        if (auth.getCurrentUser() == null) {
            Intent myIntent = new Intent(getApplicationContext(), SignInActivity.class);
            startActivity(myIntent);
            finish();
        }else{}


    }//End OnCreate
}
