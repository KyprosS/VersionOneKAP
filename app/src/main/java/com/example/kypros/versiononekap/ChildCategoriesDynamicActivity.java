package com.example.kypros.versiononekap;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class ChildCategoriesDynamicActivity extends BaseActivity {

    private RecyclerView mBlogList;
    private DatabaseReference mDatabase;



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


        mDatabase = FirebaseDatabase.getInstance().getReference().child("Child_categories");
        mDatabase.keepSynced(true);


        mBlogList = (RecyclerView) findViewById(R.id.myrecyclerview);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(new LinearLayoutManager(this));









    }//onCreate Ends here




    @Override
    protected void onStart(){
        super.onStart();

        FirebaseRecyclerAdapter<Cld_cats, CldCatsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Cld_cats, CldCatsViewHolder>
                (Cld_cats.class, R.layout.blog_row, CldCatsViewHolder.class, mDatabase) {

            @Override
            protected void populateViewHolder(CldCatsViewHolder viewHolder, Cld_cats model, int position) {

                viewHolder.setTitle(model.getChild_category_name());
                viewHolder.setDesc(model.getId_parent_category());
                viewHolder.setImage(getApplicationContext(), model.getImage());





            }
        };


        mBlogList.setAdapter(firebaseRecyclerAdapter);


    }

    public static class CldCatsViewHolder extends RecyclerView.ViewHolder{

        View mView;

        public CldCatsViewHolder(View itemView)
        {
            super(itemView);
            mView = itemView;
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


            if (image.isEmpty()) {
                post_Image.setImageResource(R.drawable.ic_add_white_24dp);
            } else{
                Picasso.with(ctx).load(image).into(post_Image);
            }





        }






    }







}
