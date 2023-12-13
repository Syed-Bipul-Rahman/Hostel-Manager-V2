package com.learning.hostelmanagerv2.services.repository;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.learning.hostelmanagerv2.services.model.AllGallery;
import com.learning.hostelmanagerv2.services.model.Gallery;
import com.learning.hostelmanagerv2.services.network.ApiClient;
import com.learning.hostelmanagerv2.services.network.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllGalleryRepository {
    private static Context mContext;
    private static AllGalleryRepository instance;
    private List<Gallery> mGallery;
    private AllGallery allGalleryModel;
    private MutableLiveData mLiveData;

    public static AllGalleryRepository getInstance(Context context) {
        if (instance == null) {
            mContext = context;
            instance = new AllGalleryRepository();

        }
        return instance;
    }


    public MutableLiveData<List<Gallery>> getGalleryList() {
        if (mLiveData == null) {
            mLiveData = new MutableLiveData();
        }
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<AllGallery> call = apiService.getAllGallery();

        call.enqueue(new Callback<AllGallery>() {
            @Override
            public void onResponse(Call<AllGallery> call, Response<AllGallery> response) {
                allGalleryModel = response.body();

                mGallery = allGalleryModel.getData();

                mLiveData.setValue(mGallery);
            }

            @Override
            public void onFailure(Call<AllGallery> call, Throwable t) {
                Toast.makeText(mContext, "Failed to Load data", Toast.LENGTH_SHORT).show();
            }
        });

        return mLiveData;
    }
}
