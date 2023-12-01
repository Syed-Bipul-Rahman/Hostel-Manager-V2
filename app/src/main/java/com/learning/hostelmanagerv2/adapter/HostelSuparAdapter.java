package com.learning.hostelmanagerv2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.learning.hostelmanagerv2.databinding.PersonHomeLayoutBinding;
import com.learning.hostelmanagerv2.services.model.SingleAdmin;

import java.util.List;

public class HostelSuparAdapter extends RecyclerView.Adapter<HostelSuparAdapter.HostelViewholder> {

    Context context;
    List<SingleAdmin> singleAdminList;

    public HostelSuparAdapter(Context context, List<SingleAdmin> singleAdminList) {
        this.context = context;
        this.singleAdminList = singleAdminList;
    }


    @NonNull
    @Override
    public HostelViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        PersonHomeLayoutBinding adminRecyclerLayoutBinding = PersonHomeLayoutBinding.inflate(layoutInflater, parent, false);
        return new HostelViewholder(adminRecyclerLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull HostelViewholder holder, int position) {
        holder.adminRecyclerLayoutBinding.nameofperson.setText(singleAdminList.get(position).getHallSuparName());
        holder.adminRecyclerLayoutBinding.mobilenoofman.setText(singleAdminList.get(position).getHallSuparPhone());
        holder.adminRecyclerLayoutBinding.titleofman.setText(singleAdminList.get(position).getPosition());
    }

    @Override
    public int getItemCount() {
        return singleAdminList.size();
    }

    public class HostelViewholder extends RecyclerView.ViewHolder {
        PersonHomeLayoutBinding adminRecyclerLayoutBinding;

        public HostelViewholder(PersonHomeLayoutBinding adminRecyclerLayoutBinding) {
            super(adminRecyclerLayoutBinding.getRoot());

            this.adminRecyclerLayoutBinding = adminRecyclerLayoutBinding;
        }
    }
}
