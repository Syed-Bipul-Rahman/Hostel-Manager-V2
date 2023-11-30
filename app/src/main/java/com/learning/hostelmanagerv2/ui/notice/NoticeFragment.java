package com.learning.hostelmanagerv2.ui.notice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.learning.hostelmanagerv2.adapter.NoticeAdapter;
import com.learning.hostelmanagerv2.databinding.FragmentNoticeBinding;

public class NoticeFragment extends Fragment {

    private FragmentNoticeBinding binding;
    NoticeAdapter noticeAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NoticeViewModel noticeViewModel =
                new ViewModelProvider(this).get(NoticeViewModel.class);

        binding = FragmentNoticeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.noticeListRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));


        noticeViewModel.getAllNotice().observe(getViewLifecycleOwner(), notices -> {

            noticeAdapter = new NoticeAdapter(getContext(), notices);
            binding.noticeListRecyclerview.setAdapter(noticeAdapter);
            binding.noticeListRecyclerview.setVisibility(View.VISIBLE);
            binding.progressbarNotices.setVisibility(View.GONE);


        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}