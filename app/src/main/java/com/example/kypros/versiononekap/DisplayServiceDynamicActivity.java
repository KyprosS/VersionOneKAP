package com.example.kypros.versiononekap;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.melnykov.fab.FloatingActionButton;
import com.squareup.picasso.Picasso;
import info.hoang8f.widget.FButton;

public class DisplayServiceDynamicActivity extends BaseActivity implements OnMapReadyCallback {

    FloatingActionButton floatDirectionsIcon;
    public RatingBar ratingBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ADD BURGER MENU DYNAMICALY START ---------------------------------------------------------
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_display_service_dynamic, contentFrameLayout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(0).setChecked(true);
        //ADD BURGER MENU END ----------------------------------------------------------------------

        //GET service ID from ListServicesDynamicActivity
        Intent mIntent = getIntent();
        final String service_id = mIntent.getStringExtra("specific_service_id");

        //Init Firebase
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Services").child(service_id);
        databaseReference.keepSynced(true);

        ratingBar = (RatingBar) findViewById(R.id.tv_service_rating);

        //Google Maps
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(DisplayServiceDynamicActivity.this);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final services_model service = dataSnapshot.getValue(services_model.class);

                getSupportActionBar().setTitle(service.getParent_category() + " » " + service.getId_child_category());

                Picasso.with(DisplayServiceDynamicActivity.this).load(service.getImage()).fit().centerCrop().into((ImageView)findViewById(R.id.imv_service_cover_photo));
                Picasso.with(DisplayServiceDynamicActivity.this).load(service.getLogo_image()).fit().centerCrop().into((ImageButton)findViewById(R.id.imv_service_logo_photo));
                ((TextView)findViewById(R.id.tv_service_title)).setText(service.getTitle());
                ((TextView)findViewById(R.id.tv_service_description)).setText(service.getDescription());
                ((TextView)findViewById(R.id.tv_service_price)).setText("€ " + service.getPrice());
                ratingBar.setRating(Float.parseFloat(service.getRating()));
                ((TextView)findViewById(R.id.tv_service_timetable)).setText(service.getId_timetable());
                ((TextView)findViewById(R.id.tv_service_district)).setText(service.getId_district());
                ((TextView)findViewById(R.id.tv_service_address)).setText(service.getAddress() + ", " + service.getPostalcode() + ", " + service.getId_district() + ", Cyprus");

                //GoogleMap Marker - service location on map with Marker
                String address = ", " + service.getAddress() + ", " + service.getPostalcode() + ", " + service.getId_district() + ", Cyprus";
                double  latitude = Double.parseDouble(service.getLatitude());
                double  longitude = Double.parseDouble(service.getLongitude());
                LatLng coords = new LatLng(latitude, longitude);
                googleMap.addMarker(new MarkerOptions().position(coords).title(service.getTitle() + address));
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 15.0f));

                ((TextView)findViewById(R.id.tv_service_name)).setText(service.getName());














                //Phone Buttons----------
                ((FButton)findViewById(R.id.tv_service_phone)).setText(service.getPhone());
                ((FButton)findViewById(R.id.tv_service_phone2)).setText(service.getPhone2());
                ((FButton)findViewById(R.id.tv_service_phone)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", service.getPhone(), null)));
                    }
                });

                ((FButton)findViewById(R.id.tv_service_phone2)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", service.getPhone2(), null)));
                    }
                });





























                ((TextView)findViewById(R.id.tv_service_email)).setText(service.getEmail());
                ((TextView)findViewById(R.id.tv_service_fax)).setText(service.getFax());

                //BottomTVUser
                ((TextView)findViewById(R.id.user_name_email)).setText(service.getUser_ID());



                //OnClick Directions button jump START -----------------------------------------------------------
                floatDirectionsIcon = (FloatingActionButton) findViewById(R.id.floatingDirectionsIcon);

                floatDirectionsIcon.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View arg0) {

                        // Create a Uri from an intent string. Use the result to create an Intent.
                        Uri gmmIntentUri = Uri.parse("https://maps.google.com/maps?daddr=" + service.getLatitude() + "," + service.getLongitude());
                        // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                        // Make the Intent explicit by setting the Google Maps package
                        mapIntent.setPackage("com.google.android.apps.maps");
                        // Attempt to start an activity that can handle the Intent
                        startActivity(mapIntent);
                    }
                });
                //On Directions button click END -----------------------------------------------------------------


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });








    }//onCreate END




    GoogleMap googleMap;//make it global
    @Override
    public void onMapReady(final GoogleMap map) {

        googleMap = map;
    }



}
