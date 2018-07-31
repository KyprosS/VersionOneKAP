package com.example.kypros.versiononekap;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.melnykov.fab.FloatingActionButton;
import com.squareup.picasso.Picasso;

public class ChildCategoriesDynamicActivity extends BaseActivity {

    SwipeRefreshLayout mySwipeRefreshLayout;
    private RecyclerView mBlogList;
    private DatabaseReference databaseReference;
    FloatingActionButton floatingSearchIcon;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ADD BURGER MENU DYNAMICALY START ---------------------------------------------------------
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_child_categories_dynamic, contentFrameLayout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(0).setChecked(true);
        //ADD BURGER MENU END ----------------------------------------------------------------------

        //DRAG DOWN TO REFRESH LAYOUT STARTS--------------------------------------------------------
        mySwipeRefreshLayout = (SwipeRefreshLayout)this.findViewById(R.id.swipeContainer);

        //Check internet Connectivity
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

        //Init Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Child_categories");
        databaseReference.keepSynced(true);

        mBlogList = (RecyclerView) findViewById(R.id.myrecyclerview);
        mBlogList.setHasFixedSize(true);
        //mBlogList.setLayoutManager(new LinearLayoutManager(this));
        mBlogList.setLayoutManager(new GridLayoutManager(this, 2));

        //OnClick Search button jump to activity SearchResults START ------------------------------
        floatingSearchIcon = (FloatingActionButton) findViewById(R.id.floatingSearchIcon);

        floatingSearchIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent myIntent = new Intent(ChildCategoriesDynamicActivity.this, SearchResults.class);
                startActivity(myIntent);
            }
        });
        //On Search button click jump to activity SearchResults END --------------------------------

    }//onCreate Ends here



    @Override
    protected void onStart(){
        super.onStart();

        //GET parent_id from MainActivity
        Intent mIntent = getIntent();
        String parent_id = mIntent.getStringExtra("Parent_category_Id");

        FirebaseRecyclerAdapter<Cld_cats, CldCatsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Cld_cats, CldCatsViewHolder>
                (Cld_cats.class, R.layout.category_row, CldCatsViewHolder.class, databaseReference.orderByChild("parent_category").equalTo(parent_id)) {

            @Override
            protected void populateViewHolder(CldCatsViewHolder viewHolder, Cld_cats model, int position) {

                viewHolder.setTitle(model.getChild_category_name());
                viewHolder.setDesc(model.getParent_category());
                viewHolder.setImage(getApplicationContext(), model.getImage());

                viewHolder.databaseReference = getRef(position);
            }
        };
        mBlogList.setAdapter(firebaseRecyclerAdapter);
    }


    public static class CldCatsViewHolder extends RecyclerView.ViewHolder{

        View mView;
        DatabaseReference databaseReference;

        public CldCatsViewHolder(View itemView)
        {
            super(itemView);
            mView = itemView;






            //On click on the card views change activity!!!!!!
            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //GET parent_category_name value from firebase!!!
                    ValueEventListener child_nameValueEventListener = new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            String child_category_name = dataSnapshot.getValue(String.class);

                            Intent intent = new Intent(mView.getContext(), ListServicesDynamicActivity.class);
                            intent.putExtra("Child_category_Id", child_category_name);
                            mView.getContext().startActivity(intent);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    };
                    databaseReference.child("child_category_name").addValueEventListener(child_nameValueEventListener);
                }//onClick END

            });
        }

        public void setTitle(String title){
            TextView post_title = (TextView) mView.findViewById(R.id.post_title);
            post_title.setText(title);
        }

        public void setDesc(String desc){
            TextView post_desc = (TextView) mView.findViewById(R.id.post_desc);
            post_desc.setText(desc);
        }

        public void setImage(Context ctx, String image){
            ImageView post_Image = (ImageView) mView.findViewById(R.id.post_image);


            //If image column is empty
            if (image.isEmpty()) {
                post_Image.setImageResource(R.drawable.ic_add_white_24dp);
            } else{
                Picasso.with(ctx).load(image).into(post_Image);
            }
        }
    }

}
