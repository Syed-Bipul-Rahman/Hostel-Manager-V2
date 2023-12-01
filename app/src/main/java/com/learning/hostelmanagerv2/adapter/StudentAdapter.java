package com.learning.hostelmanagerv2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.learning.hostelmanagerv2.databinding.StudentListLayoutBinding;
import com.learning.hostelmanagerv2.services.model.Students;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private Context context;
    private List<Students> results;

    public StudentAdapter(Context context, List<Students> results) {
        this.context = context;
        this.results = results;
    }


    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        StudentListLayoutBinding studenRecyclerLayoutBinding = StudentListLayoutBinding.inflate(layoutInflater, parent, false);
        return new StudentViewHolder(studenRecyclerLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.StudentViewHolder holder, int position) {
        String studentName = results.get(position).getName();
        String studentphonenumbr = results.get(position).getPhone();
        String studentmother = results.get(position).getMother();
        String studentfather = results.get(position).getFather();
        String studentfatgerphonenumbr = results.get(position).getFatherphone();
        String studentDistrict = results.get(position).getDist();
        String studentUpazila = results.get(position).getUpzila();
        String studentRoll = results.get(position).getRoll();
        String studentRegistration = results.get(position).getRegistration();
        String studentSession = results.get(position).getSession();


        holder.studenRecyclerLayoutBinding.studentname.setText(studentName);
        holder.studenRecyclerLayoutBinding.rommno.setText(studentphonenumbr);
        holder.studenRecyclerLayoutBinding.session.setText(studentSession);
//        holder.studenRecyclerLayoutBinding.studentshortdesc.setOnClickListener(v -> {
//
//            //starts details info activity
//            Intent intent = new Intent(context, StudentDetails.class);
//            intent.putExtra("name", studentName);
//            intent.putExtra("studentphonenumbr", studentphonenumbr);
//            intent.putExtra("studentmother", studentmother);
//            intent.putExtra("studentfather", studentfather);
//            intent.putExtra("studentfatgerphonenumbr", studentfatgerphonenumbr);
//            intent.putExtra("studentDistrict", studentDistrict);
//            intent.putExtra("studentUpazila", studentUpazila);
//            intent.putExtra("studentRoll", studentRoll);
//            intent.putExtra("studentRegistration", studentRegistration);
//            intent.putExtra("studentSession", studentSession);
//
//
//            context.startActivity(intent);
//
//
//        });

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder {

        StudentListLayoutBinding studenRecyclerLayoutBinding;

        public StudentViewHolder(StudentListLayoutBinding studenRecyclerLayoutBinding) {
            super(studenRecyclerLayoutBinding.getRoot());


            this.studenRecyclerLayoutBinding = studenRecyclerLayoutBinding;
        }
    }


    public void filterlist(List<Students> filterlist) {

        results = filterlist;
        notifyDataSetChanged();

    }

}
