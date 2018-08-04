package com.example.kypros.versiononekap;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.reginald.editspinner.EditSpinner;

import java.util.ArrayList;
import java.util.List;

public class AddNewServiceActivity extends BaseActivity {

    private FirebaseAuth auth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

            Toast.makeText(AddNewServiceActivity.this, "You need an account to add a new service!", Toast.LENGTH_LONG).show();

            Intent myIntent = new Intent(getApplicationContext(), SignInActivity.class);
            startActivity(myIntent);
            finish();
        }

        //Hide keyboard when activity starts
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //Init Firebase
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Parent_categories");
        mDatabase.keepSynced(true);





        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                final List<String> parnt_cats = new ArrayList<String>();

                for (DataSnapshot parentSnapshot: dataSnapshot.getChildren()) {
                    String parent_category_name = parentSnapshot.child("parent_category_name").getValue(String.class);
                    parnt_cats.add(parent_category_name);
                }

                EditSpinner parent_catsSpinner = (EditSpinner) findViewById(R.id.spn_Categories);
                ArrayAdapter<String> parent_catsAdapter = new ArrayAdapter<String>(AddNewServiceActivity.this, android.R.layout.simple_spinner_dropdown_item, parnt_cats);
                parent_catsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                parent_catsSpinner.setAdapter(parent_catsAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Districts");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                final List<String> district_name = new ArrayList<String>();

                for (DataSnapshot districtSnapshot: dataSnapshot.getChildren()) {
                    String parent_category_name = districtSnapshot.child("district_name").getValue(String.class);
                    district_name.add(parent_category_name);
                }

                EditSpinner districtsSpinner = (EditSpinner) findViewById(R.id.spn_Districts);
                ArrayAdapter<String> districtsAdapter = new ArrayAdapter<String>(AddNewServiceActivity.this, android.R.layout.simple_spinner_dropdown_item, district_name);
                districtsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                districtsSpinner.setAdapter(districtsAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });







    }//End OnCreate









}
