package com.learning.hostelmanagerv2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.learning.hostelmanagerv2.databinding.ShortNoticeLayoutBinding;
import com.learning.hostelmanagerv2.services.model.Notice;

import java.util.List;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeViewholder> {
    private Context context;
    private List<Notice> noticeList;

    public NoticeAdapter(Context context, List<Notice> noticeList) {
        this.context = context;
        this.noticeList = noticeList;
    }

    @NonNull
    @Override
    public NoticeViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ShortNoticeLayoutBinding noticeLayoutBinding = ShortNoticeLayoutBinding.inflate(layoutInflater, parent, false);
        return new NoticeViewholder(noticeLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeViewholder holder, int position) {

        //   holder.noticeLayoutBinding.noticeTitle.setText(noticeList.get(position).getNoticetitle());

        //get first 30 charecter
        String showtoDesc = noticeList.get(position).getDescrip().substring(0, Math.min(noticeList.get(position).getDescrip().length(), 50));

        holder.noticeLayoutBinding.noticetext.setText(showtoDesc + "...");


//        holder.noticeLayoutBinding.noticeContainer.setOnClickListener(v -> {
//            Intent intent = new Intent(context, NoticeDetails.class);
//            intent.putExtra("title", noticeList.get(position).getNoticetitle());
//            intent.putExtra("desc", noticeList.get(position).getDescrip());
//
//            context.startActivity(intent);
//        });


    }

    @Override
    public int getItemCount() {
        return noticeList.size();
    }

    public class NoticeViewholder extends RecyclerView.ViewHolder {
        ShortNoticeLayoutBinding noticeLayoutBinding;

        public NoticeViewholder(ShortNoticeLayoutBinding noticeLayoutBinding) {
            super(noticeLayoutBinding.getRoot());
            this.noticeLayoutBinding = noticeLayoutBinding;
        }
    }
}
