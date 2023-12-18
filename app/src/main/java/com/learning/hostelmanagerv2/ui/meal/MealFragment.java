package com.learning.hostelmanagerv2.ui.meal;

import static android.content.Context.MODE_PRIVATE;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.learning.hostelmanagerv2.R;
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
    String authToken;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMealBinding.inflate(inflater, container, false);

        View view = binding.getRoot();


        //prevent user to see meal info if they are not logged in
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("login_pref", MODE_PRIVATE);
        authToken = sharedPreferences.getString("USER_AUTH_TOCKEN", "");

        if (authToken.isEmpty() || authToken.equals("")) {
            showpopupdialog();

        } else {
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

        }


        return view;

    }

    private void showpopupdialog() {


        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popup_layout);

        // ImageView closeIcon = dialog.findViewById(R.id.closeIcon);
        TextView cancelBtn = dialog.findViewById(R.id.cancelButton);
        TextView loginBtn = dialog.findViewById(R.id.loginButton);
        dialog.setCancelable(false);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NavController navController = Navigation.findNavController(requireView());
                navController.navigate(R.id.nav_home);
                dialog.dismiss();
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(requireView());
                navController.navigate(R.id.nav_login);
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}