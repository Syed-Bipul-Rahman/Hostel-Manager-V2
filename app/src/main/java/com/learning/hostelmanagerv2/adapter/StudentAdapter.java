package com.learning.hostelmanagerv2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.learning.hostelmanagerv2.databinding.StudentListLayoutBinding;
import com.learning.hostelmanagerv2.services.model.Students;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {


    //for click
    public interface OnItemClickListener {
        void onItemClick(Students student);
    }


    private Context context;
    private List<Students> results;
    private boolean animateOnScroll = false;
    private OnItemClickListener listener;

    public StudentAdapter(Context context, List<Students> results, OnItemClickListener listener) {
        this.context = context;
        this.results = results;
        this.listener = listener;
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
        String studentRoomNo = results.get(position).getRoomNo();


        holder.studenRecyclerLayoutBinding.studentname.setText(studentName);
        holder.studenRecyclerLayoutBinding.rommno.setText(studentphonenumbr);
        holder.studenRecyclerLayoutBinding.session.setText(studentRoomNo);

        if (animateOnScroll) {
            setAnimation(holder.itemView, position);
        }
//click listener
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(results.get(position));
            }
        });


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

    public void setAnimation(View viewToAnimate, int position) {
        Animation slide_in = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
        viewToAnimate.startAnimation(slide_in);


    }

    public void filterlist(List<Students> filterlist) {

        results = filterlist;
        notifyDataSetChanged();

    }

    public void enableAnimationOnScroll() {
        animateOnScroll = true;
    }

    public void disableAnimationOnScroll() {
        animateOnScroll = false;
    }
}