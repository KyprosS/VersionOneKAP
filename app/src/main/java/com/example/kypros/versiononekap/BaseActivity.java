package com.example.kypros.versiononekap;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class BaseActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    private final Context mContext = this;

    private TextView mUserTextView, mEmailTextView;
    private CircleImageView mProfileImageView;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();


        //Display Navigation Drawer (Burger Menu)
        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.Open, R.string.Close);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);





        //Navigation Drawer Account picture email and name-----------------------------------------------------------------
        mUserTextView = (TextView) navigationView.getHeaderView(0).findViewById(R.id.userName);
        mEmailTextView = (TextView) navigationView.getHeaderView(0).findViewById(R.id.email);
        mProfileImageView = (CircleImageView) navigationView.getHeaderView(0).findViewById(R.id.profileImage);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {

            mUserTextView.setText(user.getDisplayName());
            mEmailTextView.setText(user.getEmail());
            Uri uri = user.getPhotoUrl();
            Picasso.with(mContext)
                    .load(uri)
                    .placeholder(android.R.drawable.sym_def_app_icon)
                    .error(android.R.drawable.sym_def_app_icon)
                    .into(mProfileImageView);
        }else{
            mUserTextView.setText("Greetings!");
            mEmailTextView.setText("Feel free to explore our app");
        }
        //Navigation Drawer Account picture email and name-----------------------------END----------------------------------



































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
                case R.id.log_out:

                    auth.signOut();

                    Intent myIntent9 = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(myIntent9);
                    drawerLayout.closeDrawers();


                    Toast.makeText(BaseActivity.this, "See you soon!", Toast.LENGTH_SHORT).show();
                    break;
            }
            return false;
        }
    });


    //HIDE Sign In, Sign Up, Favorites, My Services if user is not logged in
    //Get Firebase auth instance
    auth = FirebaseAuth.getInstance();

    //If user is logged in
        if(auth.getCurrentUser()!= null){
        navigationView.getMenu().findItem(R.id.sign_in).setVisible(false);
        navigationView.getMenu().findItem(R.id.sign_up).setVisible(false);

        navigationView.getMenu().findItem(R.id.log_out).setVisible(true);

        navigationView.getMenu().findItem(R.id.profile).setVisible(true);
        navigationView.getMenu().findItem(R.id.my_services).setVisible(true);
        navigationView.getMenu().findItem(R.id.favorites).setVisible(true);

    }else{
        navigationView.getMenu().findItem(R.id.sign_in).setVisible(true);
        navigationView.getMenu().findItem(R.id.sign_up).setVisible(true);

        navigationView.getMenu().findItem(R.id.log_out).setVisible(false);
        navigationView.getMenu().findItem(R.id.profile).setVisible(false);
        navigationView.getMenu().findItem(R.id.my_services).setVisible(false);
        navigationView.getMenu().findItem(R.id.favorites).setVisible(false);
    }


}//END onCreate


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }



}