package com.example.kypros.versiononekap;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class BaseActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.Open, R.string.Close);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.main_categories:
                        Intent myIntent0 = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(myIntent0);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.profile:
                        Intent myIntent = new Intent(getApplicationContext(), ProfileActivity.class);
                        startActivity(myIntent);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.my_services:
                        Intent myIntent2 = new Intent(getApplicationContext(), MyServicesActivity.class);
                        startActivity(myIntent2);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.add_new_services:
                        Intent myIntent3 = new Intent(getApplicationContext(), AddNewServiceActivity.class);
                        startActivity(myIntent3);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.favorites:
                        Intent myIntent4 = new Intent(getApplicationContext(), FavoritesActivity.class);
                        startActivity(myIntent4);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.settings:
                        Intent myIntent5 = new Intent(getApplicationContext(), SettingsActivity.class);
                        startActivity(myIntent5);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.sign_in:
                        Intent myIntent6 = new Intent(getApplicationContext(), SignInActivity.class);
                        startActivity(myIntent6);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.sign_up:
                        Intent myIntent7 = new Intent(getApplicationContext(), SignUpActivity.class);
                        startActivity(myIntent7);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.help_feedback:
                        Intent myIntent8 = new Intent(getApplicationContext(), HelpFeedbackActivity.class);
                        startActivity(myIntent8);
                        drawerLayout.closeDrawers();
                        break;
                }
                return false;
            }
        });
    }//END onCreate

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }
}