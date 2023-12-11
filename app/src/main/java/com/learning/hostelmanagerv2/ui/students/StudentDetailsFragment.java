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


        //getting value send from another fragment
        getParentFragmentManager().setFragmentResultListener("senddata", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {

                String name = result.getString("name", "");
                String district = result.getString("district", "");
                String hostelid = result.getString("hostelid", "");
                String room = result.getString("room", "");
                String registration = result.getString("registration", "");
                String roll = result.getString("roll", "");
                String phone = result.getString("phone", "");
                String session = result.getString("session", "");
                if (name != null) {
//set text
                    binding.showdemo.setText(name.toString());
                    binding.hostelId.setText(hostelid);
                    binding.studentDistrict.setText(district);
                    binding.hostelRoomNo.setText(room);
                    binding.studentRegistration.setText(registration);
                    binding.studentRoll.setText(roll);
                    binding.studentMobile.setText(phone);
                    binding.studentSession.setText(session);


                }


            }
        });
        return view;
    }
}