package com.moringaschool.craftypictures;

import android.content.Context;
import android.widget.ArrayAdapter;

public class GalleriesArrayAdapter extends ArrayAdapter {

    private Context mContext;
    private String[] mGalleries;
    private String[] mCategories;

    public GalleriesArrayAdapter(Context mContext, int resource, String[] mGalleries, String[] mCategories) {
        super(mContext, resource);
        this.mContext = mContext;
        this.mGalleries = mGalleries;
        this.mCategories = mCategories;
    }
    @Override
    public Object getItem(int position) {
        String gallery = mGalleries[position];
        String category = mCategories[position];
        return String.format("%s \n Category: %s", gallery, category);
    }
    @Override
    public int getCount() {
        return mGalleries.length;
    }
}