package com.learning.hostelmanagerv2.ui.admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.learning.hostelmanagerv2.adapter.HostelSuparAdapter;
import com.learning.hostelmanagerv2.databinding.FragmentAdminBinding;
import com.learning.hostelmanagerv2.services.model.SingleAdmin;

import java.util.List;

public class AdminFragment extends Fragment {

    FragmentAdminBinding binding;
    HostelSuparAdapter adapter;
    List<SingleAdmin> mymodelList;
    AdminFragmentViewModel viewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentAdminBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //get admin from viewmodel

        binding.recyclerAdmin.setLayoutManager(new GridLayoutManager(getContext(), 2));

        viewModel = new ViewModelProvider(this).get(AdminFragmentViewModel.class);
        viewModel.getAllAdmin().observe(this, singleAdmins -> {
            mymodelList = singleAdmins;
            binding.progressbarAdminLoading.setVisibility(View.GONE);
            adapter = new HostelSuparAdapter(getContext(), singleAdmins);
            binding.recyclerAdmin.setAdapter(adapter);
            binding.recyclerAdmin.setVisibility(View.VISIBLE);

            adapter.notifyDataSetChanged();
        });


        return root;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}