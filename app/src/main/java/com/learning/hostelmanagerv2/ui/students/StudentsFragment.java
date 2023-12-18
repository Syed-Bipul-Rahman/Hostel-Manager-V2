package com.learning.hostelmanagerv2.ui.students;

import static android.content.Context.MODE_PRIVATE;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.learning.hostelmanagerv2.R;
import com.learning.hostelmanagerv2.adapter.StudentAdapter;
import com.learning.hostelmanagerv2.databinding.FragmentStudentsBinding;
import com.learning.hostelmanagerv2.services.model.Students;

import java.util.ArrayList;
import java.util.List;

public class StudentsFragment extends Fragment implements StudentAdapter.OnItemClickListener {

    FragmentStudentsBinding binding;
    StudentViewModel viewModel;
    StudentAdapter adapter;
    String authToken;
    List<Students> mymodelList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentStudentsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        //prevent user to see studnet info if they are not logged in
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("login_pref", MODE_PRIVATE);
        authToken = sharedPreferences.getString("USER_AUTH_TOCKEN", "");

        if (authToken.isEmpty() || authToken.equals("")) {
            showpopupdialog();

        } else {
            binding.studentsRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

            viewModel = new ViewModelProvider(this).get(StudentViewModel.class);
            viewModel.getAllStudentList().observe(this, new Observer<List<Students>>() {
                @Override
                public void onChanged(List<Students> students) {
                    binding.totalstudents.setText("" + students.size());
                    mymodelList = students;

                    binding.progressBar.setVisibility(View.GONE);

                    // Use the modified constructor to pass the listener to the adapter
                    adapter = new StudentAdapter(getContext(), students, StudentsFragment.this);

                    binding.studentsRecycler.setAdapter(adapter);

                    binding.studentsRecycler.setVisibility(View.VISIBLE);

                    adapter.notifyDataSetChanged();

                    // animate recycler view items when scrolling
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
        }


        return root;
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
    public void onItemClick(Students student) {
        ///pass data to student details fragment and load it


        Bundle bundle = new Bundle();

        bundle.putString("name", student.getName());
        bundle.putString("phone", student.getPhone());
        bundle.putString("roll", student.getRoll());
        bundle.putString("district", student.getDist());
        bundle.putString("room", student.getRoomNo());
        bundle.putString("hostelid", student.getStudentId());
        bundle.putString("registration", student.getRegistration());
        bundle.putString("session", student.getSession());

        getParentFragmentManager().setFragmentResult("senddata", bundle);


        NavController navController = Navigation.findNavController(requireView());
        navController.navigate(R.id.nav_student_details);


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
