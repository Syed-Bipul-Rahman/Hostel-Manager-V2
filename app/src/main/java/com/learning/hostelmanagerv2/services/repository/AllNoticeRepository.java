package com.learning.hostelmanagerv2.services.repository;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;


import com.learning.hostelmanagerv2.services.model.AllNotice;
import com.learning.hostelmanagerv2.services.model.Notice;
import com.learning.hostelmanagerv2.services.network.ApiClient;
import com.learning.hostelmanagerv2.services.network.ApiService;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllNoticeRepository {
    private static Context mContext;
    private static AllNoticeRepository instance;
    private List<Notice> mNotice;
    private AllNotice allNoticeModel;
    private MutableLiveData mLiveData;


    public static AllNoticeRepository getInstance(Context context) {
        if (instance == null) {
            mContext = context;
            instance = new AllNoticeRepository();

        }
        return instance;
    }

    public MutableLiveData<List<Notice>> getNoticeList() {
        if (mLiveData == null) {
            mLiveData = new MutableLiveData();
        }

        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<AllNotice> call = apiService.getAllNotice();
        call.enqueue(new Callback<AllNotice>() {
            @Override
            public void onResponse(Call<AllNotice> call, Response<AllNotice> response) {
                allNoticeModel = response.body();

                mNotice = allNoticeModel.getData();

                mLiveData.setValue(mNotice);

            }

            @Override
            public void onFailure(Call<AllNotice> call, Throwable t) {



            }
        });


        return mLiveData;
    }

}
