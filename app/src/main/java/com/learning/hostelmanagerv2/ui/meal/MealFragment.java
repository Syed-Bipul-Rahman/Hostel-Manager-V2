package com.learning.hostelmanagerv2.ui.meal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.learning.hostelmanagerv2.adapter.MealAdapter;
import com.learning.hostelmanagerv2.databinding.FragmentMealBinding;
import com.learning.hostelmanagerv2.services.model.MealData;

import java.util.List;

public class MealFragment extends Fragment {

    FragmentMealBinding binding;
    MealAdapter adapter;
    List<MealData> mymodelList;
    MealListViewModel viewModel;
    int totalCost = 0;
    String totalcostString = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMealBinding.inflate(inflater, container, false);

        View view = binding.getRoot();


        binding.mealRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        viewModel = new ViewModelProvider(this).get(MealListViewModel.class);
        viewModel.getAllMealList().observe(getViewLifecycleOwner(), mealData -> {
            adapter = new MealAdapter(getContext(), mealData);
            binding.mealRecyclerView.setAdapter(adapter);

            //get cost from mealData and set it to total cost

            for (MealData data : mealData) {
                totalcostString = data.getCost();
                totalCost += Integer.parseInt(totalcostString);

            }
            binding.mealTotal.setText(totalCost + "");
            adapter.notifyDataSetChanged();
        });


        return view;

    }
}