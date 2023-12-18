package com.learning.hostelmanagerv2.ui.login;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.learning.hostelmanagerv2.services.model.LoginModel;
import com.learning.hostelmanagerv2.services.repository.LoginRepository;

public class LoginViewModel extends AndroidViewModel {

    private LoginRepository loginRepository;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        loginRepository = LoginRepository.getInstance(application);

    }

    public LiveData<LoginModel> loginUser(String username, String password) {
        if (loginRepository != null) {
            return loginRepository.loginUser(username, password);
        }
        return null;
    }
}
