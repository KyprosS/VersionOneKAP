package com.example.kypros.versiononekap;

import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.internal.NavigationMenu;
import android.support.design.widget.NavigationView;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import io.github.yavski.fabspeeddial.FabSpeedDial;

public class DisplayServiceDynamicActivity extends BaseActivity implements OnMapReadyCallback {

    FabSpeedDial floatingCallIcon;
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



                ((TextView)findViewById(R.id.tv_service_latitude)).setText(service.getLatitude());
                ((TextView)findViewById(R.id.tv_service_longitude)).setText(service.getLongitude());


                //Google Maps
                SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.map);
                mapFragment.getMapAsync(DisplayServiceDynamicActivity.this);




                ((TextView)findViewById(R.id.tv_service_name)).setText(service.getName());
                ((TextView)findViewById(R.id.tv_service_phone)).setText(service.getPhone());
                ((TextView)findViewById(R.id.tv_service_phone2)).setText(service.getPhone2());
                ((TextView)findViewById(R.id.tv_service_email)).setText(service.getEmail());
                ((TextView)findViewById(R.id.tv_service_fax)).setText(service.getFax());



                //OnClick Call button jump START -----------------------------------------------------------
                floatingCallIcon = (FabSpeedDial) findViewById(R.id.floatingCallIcon);

                floatingCallIcon.setMenuListener(new FabSpeedDial.MenuListener() {
                    @Override
                    public boolean onPrepareMenu(NavigationMenu navigationMenu) {
                        return true;//false: dont show menu
                    }

                    @Override
                    public boolean onMenuItemSelected(MenuItem menuItem) {

                        int item_id = menuItem.getItemId();

                        if(item_id == 2131230735)
                        {
                            startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", service.getPhone(), null)));
                        }else if(item_id == 2131230740)
                        {
                            String to = service.getEmail();
                            Intent mail = new Intent(Intent.ACTION_SEND);
                            mail.putExtra(Intent.EXTRA_EMAIL,new String[]{to});
                            mail.setType("message/rfc822");
                            startActivity(Intent.createChooser(mail, "Send email via:"));
                        }
                        else if(item_id == 2131230738)
                        {
                            // Create a Uri from an intent string. Use the result to create an Intent.
                            Uri gmmIntentUri = Uri.parse("https://maps.google.com/maps?daddr=" + service.getLatitude() + "," + service.getLongitude());
                            // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
                            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                            // Make the Intent explicit by setting the Google Maps package
                            mapIntent.setPackage("com.google.android.apps.maps");
                            // Attempt to start an activity that can handle the Intent
                            startActivity(mapIntent);
                        }
                        return true;
                    }

                    @Override
                    public void onMenuClosed() {

                    }
                });
                //On Call button click END -----------------------------------------------------------------


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });








    }//onCreate END









    @Override
    public void onMapReady(final GoogleMap map) {




        map.addMarker(new MarkerOptions()
                .position(new LatLng(0,0))
                .title("Hello"));




    }












}
