package com.learning.hostelmanagerv2.ui.notice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.learning.hostelmanagerv2.databinding.FragmentNoticeDetailsBinding;


public class NoticeDetailsFragment extends Fragment {

    FragmentNoticeDetailsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentNoticeDetailsBinding.inflate(inflater, container, false);

        View view = binding.getRoot();


        getParentFragmentManager().setFragmentResultListener("sendNotice", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String noticeDetails = result.getString("noticedetails", "");

                binding.fullNotice.setText(noticeDetails);

            }
        });

        return view;

    }
}