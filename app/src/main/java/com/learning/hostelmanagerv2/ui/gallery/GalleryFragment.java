package com.learning.hostelmanagerv2.ui.gallery;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.google.android.material.snackbar.Snackbar;
import com.learning.hostelmanagerv2.adapter.GalleryItemsAdapter;
import com.learning.hostelmanagerv2.databinding.FragmentGalleryBinding;
import com.learning.hostelmanagerv2.services.model.Gallery;

import java.util.List;

public class GalleryFragment extends Fragment {
    FragmentGalleryBinding binding;
    GalleryItemsAdapter adapter;
    List<Gallery> mymodelList;
    GalleryFragmentViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.galleryItemsImageRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
        viewModel = new ViewModelProvider(this).get(GalleryFragmentViewModel.class);
        viewModel.getAllGallery().observe(this, galleries -> {
            mymodelList = galleries;
            binding.galleryProgressBar.setVisibility(View.GONE);
            adapter = new GalleryItemsAdapter(getContext(), galleries);
            binding.galleryItemsImageRecycler.setAdapter(adapter);
            binding.galleryItemsImageRecycler.setVisibility(View.VISIBLE);

            adapter.notifyDataSetChanged();
        });

// Create a Snackbar with a custom view and position it at the top
        Snackbar snackbar = Snackbar.make(view, "Due to network issues, image loading may be delayed. Please wait or check your internet.", Snackbar.LENGTH_LONG);
        View snackbarView = snackbar.getView();
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) snackbarView.getLayoutParams();
        params.gravity = Gravity.TOP;
        snackbarView.setLayoutParams(params);

        snackbar.show();
    }
}