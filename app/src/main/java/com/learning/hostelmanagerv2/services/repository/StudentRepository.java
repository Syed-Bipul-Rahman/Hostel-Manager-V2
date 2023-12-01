package com.learning.hostelmanagerv2.services.repository;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.learning.hostelmanagerv2.services.model.AllStudents;
import com.learning.hostelmanagerv2.services.model.Students;
import com.learning.hostelmanagerv2.services.network.ApiClient;
import com.learning.hostelmanagerv2.services.network.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentRepository {
    private static Context mContext;
    private static StudentRepository instance;
    private List<Students> mStudents;
    private AllStudents allStudentsModel;
    private MutableLiveData mLiveData;


    public static StudentRepository getInstance(Context context) {
        mContext = context;
        if (instance == null) {
            instance = new StudentRepository();
        }
        return instance;
    }


    public MutableLiveData<List<Students>> getStudentList() {


        if (mLiveData == null) {
            mLiveData = new MutableLiveData();
        }


        ApiService apiService = ApiClient.getClient().create(ApiService.class);

        Call<AllStudents> call = apiService.getAllStudentsLists();


        call.enqueue(new Callback<AllStudents>() {
            @Override
            public void onResponse(Call<AllStudents> call, Response<AllStudents> response) {
                allStudentsModel = response.body();
                mStudents = allStudentsModel.getData();
                mLiveData.setValue(mStudents);


            }

            @Override
            public void onFailure(Call<AllStudents> call, Throwable t) {

                Toast.makeText(mContext, "Error in fetching data", Toast.LENGTH_SHORT).show();


            }
        });

        return mLiveData;
    }
}
