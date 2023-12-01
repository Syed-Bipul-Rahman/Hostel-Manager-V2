package com.learning.hostelmanagerv2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.learning.hostelmanagerv2.databinding.PersonLayoutRecyclerForHomeBinding;
import com.learning.hostelmanagerv2.services.model.SingleAdmin;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HostelSuperHomeAdapter extends RecyclerView.Adapter<HostelSuperHomeAdapter.HostelViewholder> {

    Context context;
    List<SingleAdmin> singleAdminList;

    public HostelSuperHomeAdapter(Context context, List<SingleAdmin> singleAdminList) {
        this.context = context;
        this.singleAdminList = singleAdminList;
    }


    @NonNull
    @Override
    public HostelSuperHomeAdapter.HostelViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        PersonLayoutRecyclerForHomeBinding adminRecyclerLayoutBinding = PersonLayoutRecyclerForHomeBinding.inflate(layoutInflater, parent, false);
        return new HostelSuperHomeAdapter.HostelViewholder(adminRecyclerLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull HostelSuperHomeAdapter.HostelViewholder holder, int position) {

        //setting image to imageview using picasso
        Picasso.get().load(singleAdminList.get(position).getImage()).into(holder.adminRecyclerLayoutBinding.personImage);
//set other data

        holder.adminRecyclerLayoutBinding.nameofperson.setText(singleAdminList.get(position).getHallSuparName());
        holder.adminRecyclerLayoutBinding.mobilenoofman.setText(singleAdminList.get(position).getHallSuparPhone());
        holder.adminRecyclerLayoutBinding.titleofman.setText(singleAdminList.get(position).getPosition());
    }

    @Override
    public int getItemCount() {
        return singleAdminList.size();
    }

    public class HostelViewholder extends RecyclerView.ViewHolder {
        PersonLayoutRecyclerForHomeBinding adminRecyclerLayoutBinding;

        public HostelViewholder(PersonLayoutRecyclerForHomeBinding adminRecyclerLayoutBinding) {
            super(adminRecyclerLayoutBinding.getRoot());

            this.adminRecyclerLayoutBinding = adminRecyclerLayoutBinding;
        }
    }
}
