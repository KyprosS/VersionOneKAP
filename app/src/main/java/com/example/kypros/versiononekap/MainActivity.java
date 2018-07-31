package com.example.kypros.versiononekap;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.melnykov.fab.FloatingActionButton;
import com.squareup.picasso.Picasso;
import android.support.design.widget.NavigationView;

public class MainActivity extends BaseActivity {

    boolean doubleBackToExitPressedOnce = false;
    FloatingActionButton floatingSearchIcon;
    SwipeRefreshLayout mySwipeRefreshLayout;
    private RecyclerView mBlogList;
    private DatabaseReference mDatabase;
    private static FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ADD BURGER MENU DYNAMICALY START ---------------------------------------------------------
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_main, contentFrameLayout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(0).setChecked(true); //Highlight selected item in menu
        //ADD BURGER MENU END ----------------------------------------------------------------------

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

        //OFFLINE FIREBASE
        if (firebaseDatabase == null) {
            firebaseDatabase = FirebaseDatabase.getInstance();
            firebaseDatabase.setPersistenceEnabled(true);
        }

        //Init Firebase
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Parent_categories");
        mDatabase.keepSynced(true);

        mBlogList = (RecyclerView) findViewById(R.id.myrecyclerview);
        mBlogList.setHasFixedSize(true);
        //mBlogList.setLayoutManager(new LinearLayoutManager(this));
        mBlogList.setLayoutManager(new GridLayoutManager(this, 2));

        //OnClick Search button jump to activity SearchResults START -------------------------------
        floatingSearchIcon = (FloatingActionButton) findViewById(R.id.floatingSearchIcon);

        floatingSearchIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent myIntent = new Intent(MainActivity.this, SearchResults.class);
                startActivity(myIntent);
            }
        });
        //On Search button click jump to activity SearchResults END --------------------------------


    }//onCreate ENDS HERE --------------------------------------------------------------------------


    //DISABLE BACK BUTTON TO GO TO WELCOME SCREEN START---------------------------------------------
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


    @Override
    protected void onStart(){
        super.onStart();

        FirebaseRecyclerAdapter<Parent_cats, ParentCatsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Parent_cats, ParentCatsViewHolder>
                (Parent_cats.class, R.layout.category_row, ParentCatsViewHolder.class, mDatabase) {

            @Override
            protected void populateViewHolder(ParentCatsViewHolder viewHolder, Parent_cats model, int position) {

                viewHolder.setTitle(model.getParent_category_name());
                viewHolder.setImage_parent(getApplicationContext(), model.getImage_parent());

                viewHolder.databaseReference = getRef(position);
            }
        };
        mBlogList.setAdapter(firebaseRecyclerAdapter);
    }


    public static class ParentCatsViewHolder extends RecyclerView.ViewHolder{

        View mView;

        DatabaseReference databaseReference; // <----- databaseReference holds the position of the view that is clicked;

        public ParentCatsViewHolder(final View itemView)
        {
            super(itemView);
            mView = itemView;

            //On click on the card views change activity!!!!!!
            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //GET parent_category_name value from firebase!!!
                    ValueEventListener parent_nameValueEventListener = new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            String parent_category_name = dataSnapshot.getValue(String.class);

                            Intent intent = new Intent(mView.getContext(), ChildCategoriesDynamicActivity.class);
                            intent.putExtra("Parent_category_Id", parent_category_name);
                            mView.getContext().startActivity(intent);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    };
                    databaseReference.child("parent_category_name").addValueEventListener(parent_nameValueEventListener);
                }//onClick END

            });

        }//ParentCatsViewHolder ENDS

        public void setTitle(String title){
            TextView post_title = (TextView) mView.findViewById(R.id.post_title);
            post_title.setText(title);
        }

       /* public void setDesc(String desc){
            TextView post_desc = (TextView) mView.findViewById(R.id.post_desc);
            post_desc.setText(desc);
        }*/

        public void setImage_parent(Context ctx, String image){
            ImageView post_Image = (ImageView) mView.findViewById(R.id.post_image);

            //If image column is empty
            if (image.isEmpty()) {
                post_Image.setImageResource(R.drawable.ic_add_white_24dp);
            } else{
                Picasso.with(ctx).load(image).into(post_Image);
            }
        }

    }

}//END MAIN CLASS HERE------------------------------------------------------------------------------