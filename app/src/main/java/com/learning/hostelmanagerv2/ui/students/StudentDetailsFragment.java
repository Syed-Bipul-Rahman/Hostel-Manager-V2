package com.learning.hostelmanagerv2.ui.students;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.learning.hostelmanagerv2.databinding.FragmentStudentDetailsBinding;


public class StudentDetailsFragment extends Fragment {
    FragmentStudentDetailsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentStudentDetailsBinding.inflate(inflater, container, false);

        View view = binding.getRoot();

        getParentFragmentManager().setFragmentResultListener("senddata", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {

                String name = result.getString("name", "");
                if (name != null) {

                    binding.showdemo.setText(name.toString());

                }


            }
        });
        return view;
    }
}