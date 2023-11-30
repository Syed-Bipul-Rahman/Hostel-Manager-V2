package com.learning.hostelmanagerv2.ui.notice;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.learning.hostelmanagerv2.services.model.Notice;
import com.learning.hostelmanagerv2.services.repository.AllNoticeRepository;

import java.util.List;

public class NoticeViewModel extends AndroidViewModel {

    AllNoticeRepository allNoticeRepository;

    public NoticeViewModel(@NonNull Application application) {
        super(application);
        allNoticeRepository = AllNoticeRepository.getInstance(application);
    }

    public LiveData<List<Notice>> getAllNotice() {
        return allNoticeRepository.getNoticeList();
    }
}