package com.example.kypros.versiononekap;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

//CLASS TO FILL THE GRID VIEW AS ADAPTER AT activity_main.xml WITH IMAGES OF OUR CATEGORIES

public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    // Keep all Images in array
    public Integer[] mThumbIds = {
            R.drawable.test_icon_1, R.drawable.test_icon_2,
            R.drawable.test_icon_3, R.drawable.test_icon_4,
            R.drawable.test_icon_1, R.drawable.test_icon_2,
            R.drawable.test_icon_3, R.drawable.test_icon_4,
            R.drawable.test_icon_1, R.drawable.test_icon_2,
            R.drawable.test_icon_3, R.drawable.test_icon_4,

           /* R.drawable.pic_5, R.drawable.pic_6,
            R.drawable.pic_7, R.drawable.pic_8,
            R.drawable.pic_9, R.drawable.pic_10,
            R.drawable.pic_11, R.drawable.pic_12,
            R.drawable.pic_13, R.drawable.pic_14,
            R.drawable.pic_15 */
    };

    // Constructor
    public ImageAdapter(Context c){
        mContext = c;
    }

    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return mThumbIds[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(mThumbIds[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        return imageView;
    }

}