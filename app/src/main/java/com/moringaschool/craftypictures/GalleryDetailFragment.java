package com.moringaschool.craftypictures;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GalleryDetailFragment extends Fragment {
    @BindView(R.id.galleryImageView) ImageView mImageLabel;
    @BindView(R.id.galleryNameTextView) TextView mNameLabel;
    @BindView(R.id.categoryTextView) TextView mCategoriesLabel;
    @BindView(R.id.websiteTextView) TextView mWebsiteLabel;
    @BindView(R.id.phoneTextView) TextView mPhoneLabel;
    @BindView(R.id.addressTextView) TextView mAddressLabel;
    @BindView(R.id.saveGalleryButton) TextView mSaveRestaurantButton;

    private Business mRestaurant;

    public GalleryDetailFragment() {
        // Required empty public constructor
    }

    public static GalleryDetailFragment newInstance(Business restaurant) {
        GalleryDetailFragment restaurantDetailFragment = new GalleryDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("restaurant", Parcels.wrap(restaurant));
        restaurantDetailFragment.setArguments(args);
        return restaurantDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRestaurant = Parcels.unwrap(getArguments().getParcelable("restaurant"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.get().load(mRestaurant.getImageUrl()).into(mImageLabel);

        List<String> categories = new ArrayList<>();
        for (Category category: mRestaurant.getCategories()) {
            categories.add(category.getTitle());
        }

        mNameLabel.setText(mRestaurant.getName());
        mCategoriesLabel.setText(android.text.TextUtils.join(", ", categories));
        mPhoneLabel.setText(mRestaurant.getPhone());
        mAddressLabel.setText(mRestaurant.getLocation().toString());

        return view;
    }
}
