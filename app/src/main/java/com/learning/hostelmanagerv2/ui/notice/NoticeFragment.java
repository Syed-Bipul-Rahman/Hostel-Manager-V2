package com.learning.hostelmanagerv2.ui.notice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.learning.hostelmanagerv2.R;
import com.learning.hostelmanagerv2.adapter.NoticeAdapter;
import com.learning.hostelmanagerv2.databinding.FragmentNoticeBinding;
import com.learning.hostelmanagerv2.services.model.Notice;

public class NoticeFragment extends Fragment implements NoticeAdapter.OnItemClickListener {

    private FragmentNoticeBinding binding;
    NoticeAdapter noticeAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NoticeViewModel noticeViewModel =
                new ViewModelProvider(this).get(NoticeViewModel.class);

        binding = FragmentNoticeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.noticeListRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));

//show get notice list from viewmodel
        noticeViewModel.getAllNotice().observe(getViewLifecycleOwner(), notices -> {

            noticeAdapter = new NoticeAdapter(getContext(), notices, NoticeFragment.this);
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

    @Override
    public void onItemClick(Notice notice) {


        Bundle bundle = new Bundle();

        bundle.putString("noticedetails", notice.getDescrip());

        getParentFragmentManager().setFragmentResult("sendNotice", bundle);

        NavController navController = Navigation.findNavController(requireView());
        navController.navigate(R.id.nav_notice_details);
    }
}