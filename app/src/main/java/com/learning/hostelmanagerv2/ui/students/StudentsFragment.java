package com.learning.hostelmanagerv2.ui.students;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.learning.hostelmanagerv2.adapter.StudentAdapter;
import com.learning.hostelmanagerv2.databinding.FragmentStudentsBinding;
import com.learning.hostelmanagerv2.services.model.Students;

import java.util.ArrayList;
import java.util.List;


public class StudentsFragment extends Fragment {

    FragmentStudentsBinding binding;
    StudentViewModel viewModel;
    StudentAdapter adapter;

    List<Students> mymodelList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentStudentsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        binding.studentsRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        viewModel = new ViewModelProvider(this).get(StudentViewModel.class);
        viewModel.getAllStudentList().observe(this, new Observer<List<Students>>() {
            @Override
            public void onChanged(List<Students> students) {
                binding.totalstudents.setText("" + students.size());
                mymodelList = students;
//               Log.d("change", students.get(1).getFatherphone());

                binding.progressBar.setVisibility(View.GONE);
                adapter = new StudentAdapter(getContext(), students);
                binding.studentsRecycler.setAdapter(adapter);

                binding.studentsRecycler.setVisibility(View.VISIBLE);

                adapter.notifyDataSetChanged();

                binding.studentsRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);
                        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                            // The RecyclerView is not scrolling
                            adapter.disableAnimationOnScroll();
                        } else {
                            // The RecyclerView is scrolling
                            adapter.enableAnimationOnScroll();
                        }
                    }
                });

                binding.searchveiw.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {

                        filter(newText);

                        return true;
                    }
                });


            }
        });

        return root;
    }

    private void filter(String newText) {

        List<Students> studentsList = new ArrayList<>();

        for (Students item : mymodelList) {
            if (item.getRoll().toLowerCase().contains(newText.toLowerCase()) || item.getName().toLowerCase().contains(newText.toLowerCase())) {
                studentsList.add(item);
            }
        }

        adapter.filterlist(studentsList);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}