package com.learning.hostelmanagerv2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.learning.hostelmanagerv2.databinding.GalleryLayoutItemBinding;
import com.learning.hostelmanagerv2.services.model.Gallery;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GalleryItemsAdapter extends RecyclerView.Adapter<GalleryItemsAdapter.NoticeViewholder> {

    private Context context;
    private List<Gallery> galleryList;

    public GalleryItemsAdapter(Context context, List<Gallery> galleryList) {
        this.context = context;
        this.galleryList = galleryList;
    }

    @NonNull
    @Override
    public GalleryItemsAdapter.NoticeViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        GalleryLayoutItemBinding galleryLayoutItemBinding = GalleryLayoutItemBinding.inflate(layoutInflater, parent, false);
        return new GalleryItemsAdapter.NoticeViewholder(galleryLayoutItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryItemsAdapter.NoticeViewholder holder, int position) {
        //load image with picasso
        Picasso.get().load(galleryList.get(position).getImageUrl()).into(holder.galleryLayoutItemBinding.galleryImages);


    }

    @Override
    public int getItemCount() {
        return galleryList.size();
    }

    public class NoticeViewholder extends RecyclerView.ViewHolder {
        GalleryLayoutItemBinding galleryLayoutItemBinding;

        public NoticeViewholder(GalleryLayoutItemBinding galleryLayoutItemBinding) {
            super(galleryLayoutItemBinding.getRoot());
            this.galleryLayoutItemBinding = galleryLayoutItemBinding;
        }
    }
}
