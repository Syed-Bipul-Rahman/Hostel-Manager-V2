package com.learning.hostelmanagerv2.ui.gallery;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.learning.hostelmanagerv2.services.model.Gallery;
import com.learning.hostelmanagerv2.services.repository.AllGalleryRepository;

import java.util.List;

public class GalleryFragmentViewModel extends AndroidViewModel {
    AllGalleryRepository allGalleryRepository;

    public GalleryFragmentViewModel(@NonNull Application application) {
        super(application);
        allGalleryRepository = AllGalleryRepository.getInstance(application);

    }

    public LiveData<List<Gallery>> getAllGallery() {
        return allGalleryRepository.getGalleryList();
    }

}
