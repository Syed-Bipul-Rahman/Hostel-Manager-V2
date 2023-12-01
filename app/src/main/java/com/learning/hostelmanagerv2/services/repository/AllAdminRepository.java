package com.learning.hostelmanagerv2.services.repository;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.learning.hostelmanagerv2.services.model.AllAdmin;
import com.learning.hostelmanagerv2.services.model.SingleAdmin;
import com.learning.hostelmanagerv2.services.network.ApiClient;
import com.learning.hostelmanagerv2.services.network.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllAdminRepository {

    private static Context mContext;
    private static AllAdminRepository instance;
    private List<SingleAdmin> mAdmin;
    private AllAdmin allAdminModel;
    private MutableLiveData mLiveData;

    public static AllAdminRepository getInstance(Context context) {
        if (instance == null) {
            mContext = context;
            instance = new AllAdminRepository();

        }
        return instance;
    }




    public MutableLiveData<List<SingleAdmin>> getAdminList() {
        if (mLiveData == null) {
            mLiveData = new MutableLiveData();
        }

        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<AllAdmin> call = apiService.getAllAdmin();
        call.enqueue(new Callback<AllAdmin>() {
            @Override
            public void onResponse(Call<AllAdmin> call, Response<AllAdmin> response) {
                allAdminModel = response.body();

                mAdmin = allAdminModel.getData();

                mLiveData.setValue(mAdmin);


            }

            @Override
            public void onFailure(Call<AllAdmin> call, Throwable t) {

            }
        });


        return mLiveData;
    }

}
