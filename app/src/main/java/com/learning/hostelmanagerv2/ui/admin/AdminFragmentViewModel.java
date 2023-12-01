package com.learning.hostelmanagerv2.ui.admin;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.learning.hostelmanagerv2.services.model.SingleAdmin;
import com.learning.hostelmanagerv2.services.repository.AllAdminRepository;

import java.util.List;

public class AdminFragmentViewModel extends AndroidViewModel {
    AllAdminRepository allAdminRepository;

    public AdminFragmentViewModel(@NonNull Application application) {
        super(application);

        allAdminRepository = AllAdminRepository.getInstance(application);


    }

    public LiveData<List<SingleAdmin>> getAllAdmin() {
        return allAdminRepository.getAdminList();
    }

}
