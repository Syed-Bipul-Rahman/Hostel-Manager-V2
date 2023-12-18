package com.learning.hostelmanagerv2.services.repository;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;


import com.learning.hostelmanagerv2.services.model.MealData;
import com.learning.hostelmanagerv2.services.model.MealList;
import com.learning.hostelmanagerv2.services.network.ApiClient;
import com.learning.hostelmanagerv2.services.network.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MealListRepository {

    private static Context mContext;
    private static MealListRepository instance;
    private List<MealData> mMealData;
    private MealList allMealModel;
    private MutableLiveData mLiveData;


    public static MealListRepository getInstance(Context context) {
        if (instance == null) {
            mContext = context;
            instance = new MealListRepository();

        }
        return instance;
    }


    public MutableLiveData<List<MealData>> getMealList() {
        if (mLiveData == null) {
            mLiveData = new MutableLiveData();
        }

        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<MealList> call = apiService.getMealList();

        call.enqueue(new Callback<MealList>() {
            @Override
            public void onResponse(Call<MealList> call, Response<MealList> response) {
                allMealModel = response.body();

                mMealData = allMealModel.getData();

                mLiveData.setValue(mMealData);
            }

            @Override
            public void onFailure(Call<MealList> call, Throwable t) {
                Toast.makeText(mContext, "Cannot fetch connect with server", Toast.LENGTH_SHORT).show();
            }
        });


        return mLiveData;


    }


}
