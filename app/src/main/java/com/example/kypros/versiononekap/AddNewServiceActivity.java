package com.example.kypros.versiononekap;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.kypros.versiononekap.Common.Common;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.reginald.editspinner.EditSpinner;
import com.rengwuxian.materialedittext.MaterialEditText;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import info.hoang8f.widget.FButton;

public class AddNewServiceActivity extends BaseActivity implements OnMapReadyCallback, GoogleMap.OnCameraMoveStartedListener,
        GoogleMap.OnCameraMoveListener,
        GoogleMap.OnCameraMoveCanceledListener,
        GoogleMap.OnCameraIdleListener {

    private String prnt_cats = null;
    private Uri filePath;
    private Uri filePath2;
    FirebaseStorage storage;
    StorageReference storageReference;
    String currentUserEmail = "";
    String service_id = "";

    String lat;
    String lng;


    EditSpinner parent_Category, child_Category, district_Spinner;
    MaterialEditText edt_Title, edt_Description, edt_Price, edt_PostalCode, edt_Address,
            edt_Timetable, edt_Name, edt_Email, edt_Phone, edt_Phone2, edt_Fax;

    private GoogleMap googleMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ADD BURGER MENU DYNAMICALY START ---------------------------------------------------------
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_add_new_service, contentFrameLayout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(3).setChecked(true);
        //ADD BURGER MENU END ----------------------------------------------------------------------


        //Google Maps-------------------------------------------------------------------------------
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        //Google Maps-------------------------------------------------------------------------------


        //Init Firebase Storage
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();


        FirebaseAuth auth = FirebaseAuth.getInstance();
        //If user is not logged in
        if (auth.getCurrentUser() == null) {

            Toast.makeText(AddNewServiceActivity.this, "You need an account to add a new service!", Toast.LENGTH_LONG).show();

            Intent myIntent = new Intent(getApplicationContext(), SignInActivity.class);
            startActivity(myIntent);
            finish();
        } else if (auth.getCurrentUser() != null) {
            for (UserInfo user : FirebaseAuth.getInstance().getCurrentUser().getProviderData()) {

                currentUserEmail = user.getEmail();
            }
        }

        //Hide keyboard when activity starts
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);


        //Init Firebase
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Parent_categories");
        mDatabase.keepSynced(true);


        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                final List<String> parnt_cats = new ArrayList<String>();

                for (DataSnapshot parentSnapshot : dataSnapshot.getChildren()) {
                    String parent_category_name = parentSnapshot.child("parent_category_name").getValue(String.class);
                    parnt_cats.add(parent_category_name);
                }

                EditSpinner parent_catsSpinner = (EditSpinner) findViewById(R.id.spn_Categories);
                ArrayAdapter<String> parent_catsAdapter = new ArrayAdapter<String>(AddNewServiceActivity.this, android.R.layout.simple_spinner_dropdown_item, parnt_cats);
                parent_catsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                parent_catsSpinner.setAdapter(parent_catsAdapter);


                //Triggered when one item in the list is clicked!!!!
                parent_catsSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        //Reset if parent category changes
                        ((EditSpinner) findViewById(R.id.spn_child_cat)).setText("*Select");

                        prnt_cats = parnt_cats.get(position);

                        Query list_child_cats = FirebaseDatabase.getInstance().getReference().child("Child_categories").orderByChild("parent_category").equalTo(prnt_cats);
                        list_child_cats.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                final List<String> child_name = new ArrayList<String>();

                                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                                    String child_category_name = childSnapshot.child("child_category_name").getValue(String.class);
                                    child_name.add(child_category_name);
                                }

                                EditSpinner childSpinner = (EditSpinner) findViewById(R.id.spn_child_cat);
                                ArrayAdapter<String> childAdapter = new ArrayAdapter<String>(AddNewServiceActivity.this, android.R.layout.simple_spinner_dropdown_item, child_name);
                                childAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                childSpinner.setAdapter(childAdapter);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }


                });
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

                for (DataSnapshot districtSnapshot : dataSnapshot.getChildren()) {
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


        //LOGO
        ((FButton) findViewById(R.id.btnUploadLogo)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                chooseLogo();
            }
        });

        //IMAGE
        ((FButton) findViewById(R.id.btnUploadImage)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                chooseImage();
            }
        });


        //SUBMIT to Firebase
        ((FButton) findViewById(R.id.btnSubmit)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean error_checker = false;

                parent_Category = (EditSpinner) findViewById(R.id.spn_Categories);
                child_Category = (EditSpinner) findViewById(R.id.spn_child_cat);
                edt_Title = (MaterialEditText) findViewById(R.id.edtTitle);
                edt_Description = (MaterialEditText) findViewById(R.id.edtDescription);
                edt_Price = (MaterialEditText) findViewById(R.id.edtPrice);
                district_Spinner = (EditSpinner) findViewById(R.id.spn_Districts);
                edt_PostalCode = (MaterialEditText) findViewById(R.id.edtPostalCode);
                edt_Address = (MaterialEditText) findViewById(R.id.edtAddress);
                edt_Timetable = (MaterialEditText) findViewById(R.id.edtTimetable);
                edt_Name = (MaterialEditText) findViewById(R.id.edtName);
                edt_Email = (MaterialEditText) findViewById(R.id.edtEmail);
                edt_Phone = (MaterialEditText) findViewById(R.id.edtPhone);
                edt_Phone2 = (MaterialEditText) findViewById(R.id.edtPhone2);
                edt_Fax = (MaterialEditText) findViewById(R.id.edtFax);


                //Logo------------------------------------------------------------------------------
                if (filePath == null) {
                    error_checker = true;
                } else {
                    error_checker = false;
                }
                //----------------------------------------------------------------------------------

                //Image-----------------------------------------------------------------------------
                if (filePath2 == null) {

                    error_checker = true;
                } else {
                    error_checker = false;
                }
                //----------------------------------------------------------------------------------

                //Parent category Spinner-----------------------------------------------------------
                if (parent_Category.getText().toString().equals("*Select")) {
                    error_checker = true;
                } else {
                    error_checker = false;
                }
                //----------------------------------------------------------------------------------

                //Child category Spinner------------------------------------------------------------
                if (child_Category.getText().toString().equals("*Select")) {
                    error_checker = true;
                } else {
                    error_checker = false;
                }
                //----------------------------------------------------------------------------------

                //Title EditText--------------------------------------------------------------------
                if (edt_Title.getText().toString().equals("")) {
                    error_checker = true;
                } else {
                    error_checker = false;


                }
                //----------------------------------------------------------------------------------

                //Description EditText--------------------------------------------------------------
                if (edt_Description.getText().toString().equals("")) {
                    error_checker = true;
                } else {
                    error_checker = false;

                }
                //----------------------------------------------------------------------------------

                //Price EditText--------------------------------------------------------------------
                if (edt_Price.getText().toString().equals("")) {
                    error_checker = true;
                } else {
                    error_checker = false;

                }
                //----------------------------------------------------------------------------------

                //District Spinner------------------------------------------------------------------
                if (district_Spinner.getText().toString().equals("*Select")) {
                    error_checker = true;
                } else {
                    error_checker = false;

                }
                //----------------------------------------------------------------------------------

                //Price EditText--------------------------------------------------------------------
                if (edt_PostalCode.getText().toString().equals("")) {
                    error_checker = true;
                } else {
                    error_checker = false;

                }
                //----------------------------------------------------------------------------------

                //Address EditText--------------------------------------------------------------------
                if (edt_Address.getText().toString().equals("")) {
                    error_checker = true;
                } else {
                    error_checker = false;

                }
                //----------------------------------------------------------------------------------

                //Timetable EditText----------------------------------------------------------------
                if (edt_Timetable.getText().toString().equals("")) {
                    error_checker = true;
                } else {
                    error_checker = false;

                }
                //----------------------------------------------------------------------------------

                //Name EditText---------------------------------------------------------------------
                if (edt_Name.getText().toString().equals("")) {
                    error_checker = true;
                } else {
                    error_checker = false;

                }
                //----------------------------------------------------------------------------------

                //Email EditText--------------------------------------------------------------------
                if (edt_Email.getText().toString().equals("")) {
                    error_checker = true;
                } else {
                    error_checker = false;

                }
                //----------------------------------------------------------------------------------

                //Phone EditText--------------------------------------------------------------------
                if (edt_Phone.getText().toString().equals("")) {
                    error_checker = true;
                } else {
                    error_checker = false;

                }
                //----------------------------------------------------------------------------------

                //Phone2 EditText-------------------------------------------------------------------
                if (edt_Phone2.getText().toString().equals("")) {
                    error_checker = true;
                } else {
                    error_checker = false;

                }
                //----------------------------------------------------------------------------------

                //Fax EditText----------------------------------------------------------------------
                if (edt_Fax.getText().toString().equals("")) {
                    error_checker = true;
                } else {
                    error_checker = false;

                }
                //----------------------------------------------------------------------------------


                //No Errors
                if (!error_checker) {
                    //Check internet Connectivity
                    if (Common.isConnectedToInternet(getBaseContext())) {

                        services_model Service = new services_model();

                        uploadLogo();
                        uploadImage();

                        Service.setTitle(edt_Title.getText().toString().trim());
                        Service.setParent_category(parent_Category.getText().toString().trim());
                        Service.setId_child_category(child_Category.getText().toString().trim());
                        Service.setDescription(edt_Description.getText().toString().trim());
                        Service.setPrice(edt_Price.getText().toString().trim());
                        Service.setId_district(district_Spinner.getText().toString().trim());
                        Service.setPostalcode(edt_PostalCode.getText().toString().trim());
                        Service.setAddress(edt_Address.getText().toString().trim());
                        Service.setId_timetable(edt_Timetable.getText().toString().trim());
                        Service.setName(edt_Name.getText().toString().trim());
                        Service.setEmail(edt_Email.getText().toString().trim());
                        Service.setPhone(edt_Phone.getText().toString().trim());
                        Service.setPhone2(edt_Phone2.getText().toString().trim());
                        Service.setFax(edt_Fax.getText().toString().trim());
                        Service.setUser_ID(currentUserEmail);
                        Service.setRating("0");
                        Service.setLatitude("0");
                        Service.setLongitude("0");
                        Service.setUser_ID(currentUserEmail);
                        Service.setImage("https://assetsv2.fiverrcdn.com/assets/v2_globals/fiverr-logo-new-green-9e65bddddfd33dfcf7e06fc1e51a5bc5.png");
                        Service.setLogo_image("http://www.inyourhands.org.au/wp-content/uploads/2017/06/image1.jpg");


                        //Init Firebase
                        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Services").push();
                        service_id = mDatabase.getKey();
                        mDatabase.setValue(Service);
                    } else {
                        Toast.makeText(AddNewServiceActivity.this, "Please check your Internet connection!", Toast.LENGTH_SHORT).show();

                    }

                }


            }
        });


    }//End OnCreate


    private void chooseLogo() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select your logo"), 0);
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select your image"), 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 0: {
                if (resultCode == RESULT_OK && data != null && data.getData() != null) {
                    filePath = data.getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                        ((ImageView) findViewById(R.id.imv_Logo)).setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            }
            case 1: {
                if (resultCode == RESULT_OK && data != null && data.getData() != null) {
                    filePath2 = data.getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath2);
                        ((ImageView) findViewById(R.id.imv_Image)).setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            }
        }

    }//End of onActivityResult


    private void uploadLogo() {

        if (filePath != null) {
            StorageReference ref = storageReference.child(currentUserEmail + "/" + service_id + "/" + "logo");
            ref.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(AddNewServiceActivity.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    private void uploadImage() {
        if (filePath2 != null) {
            System.out.println("THIS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!: " + service_id);


            StorageReference ref = storageReference.child(currentUserEmail + "/" + service_id + "/" + "cover");
            ref.putFile(filePath2).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(AddNewServiceActivity.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }


    @Override
    public void onMapReady(GoogleMap map) {

        googleMap = map;

        googleMap.setOnCameraIdleListener(this);
        googleMap.setOnCameraMoveStartedListener(this);
        googleMap.setOnCameraMoveListener(this);
        googleMap.setOnCameraMoveCanceledListener(this);

        // Show Cyprus on the map.
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(35.1264, 33.4299), 8));



    }

    @Override
    public void onCameraMoveStarted(int reason) {

        lat = Double.toString(googleMap.getCameraPosition().target.latitude);
        lng = Double.toString(googleMap.getCameraPosition().target.longitude);

        ((TextView)findViewById(R.id.tv_coordinates)).setText(lat + ", " + lng);









        //Default = 0.0, 0.0



        if (reason == GoogleMap.OnCameraMoveStartedListener.REASON_GESTURE) {

            //Toast.makeText(this, "The user gestured on the map.", Toast.LENGTH_SHORT).show();

        } else if (reason == GoogleMap.OnCameraMoveStartedListener.REASON_API_ANIMATION) {

            //Toast.makeText(this, "The user tapped something on the map.", Toast.LENGTH_SHORT).show();

        } else if (reason == GoogleMap.OnCameraMoveStartedListener.REASON_DEVELOPER_ANIMATION) {

            //Toast.makeText(this, "The app moved the camera.", Toast.LENGTH_SHORT).show();

        }
    }




    @Override
    public void onCameraMove () {

        //Toast.makeText(this, "The camera is moving.", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onCameraMoveCanceled () {

        //Toast.makeText(this, "Camera movement canceled.", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onCameraIdle () {

        //Toast.makeText(this, "The camera has stopped moving.", Toast.LENGTH_SHORT).show();


    }
    


}
