package com.learning.hostelmanagerv2.services.repository;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.learning.hostelmanagerv2.services.model.LoginModel;
import com.learning.hostelmanagerv2.services.network.ApiClient;
import com.learning.hostelmanagerv2.services.network.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {


    private static Context mContext;
    private static LoginRepository instance;
    private MutableLiveData<LoginModel> mLiveData;


    public static LoginRepository getInstance(Context context) {
        if (instance == null) {
            mContext = context;
            instance = new LoginRepository();
        }
        return instance;
    }

    public LiveData<LoginModel> loginUser(String username, String password) {
        if (mLiveData == null) {
            mLiveData = new MutableLiveData<>();
        }
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<LoginModel> call = apiService.getLogin(username, password);
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                if (response.isSuccessful()) {
                    LoginModel loginResponse = response.body();
                    mLiveData.setValue(loginResponse);
                } else {
                    // Handle API error here (e.g., parsing error response, logging, etc.)
                    mLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                mLiveData.setValue(null);
            }
        });

        return mLiveData;
    }
}
