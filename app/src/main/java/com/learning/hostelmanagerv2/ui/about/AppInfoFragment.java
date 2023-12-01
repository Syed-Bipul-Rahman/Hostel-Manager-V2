package com.learning.hostelmanagerv2.ui.about;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.learning.hostelmanagerv2.databinding.FragmentAppInfoBinding;

public class AppInfoFragment extends Fragment {
    FragmentAppInfoBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAppInfoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;

    }
}