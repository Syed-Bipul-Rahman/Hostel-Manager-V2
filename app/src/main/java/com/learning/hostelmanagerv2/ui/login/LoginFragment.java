package com.learning.hostelmanagerv2.ui.login;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.learning.hostelmanagerv2.R;
import com.learning.hostelmanagerv2.databinding.FragmentLoginBinding;
import com.learning.hostelmanagerv2.services.model.LoginModel;


public class LoginFragment extends Fragment {
    FragmentLoginBinding binding;

    private LoginViewModel loginViewModel;
    private LiveData<LoginModel> loginLiveData;


    private SharedPreferences sharedPreferences;
    private static final String PREF_NAME = "login_pref";
    private static final String IS_LOGGED_IN = "is_logged_in";

    // data
    String userName, userPhone, isAdmin, isVerified, authToken, roll;
    Integer userId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(getLayoutInflater(), container, false);

        View view = binding.getRoot();

        //navigate to register fragment after click on create account
        binding.createAccount.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(requireView());
            navController.navigate(R.id.nav_register);

        });


        //viewmodel
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);


//enable signup button while password and username is not empty and length is greater than 5
        // Add TextWatcher for userPhone EditText

        binding.userphone.addTextChangedListener(textWatcher);

        // Add TextWatcher for password EditText
        binding.password.addTextChangedListener(textWatcher);

        binding.login.setOnClickListener(v -> {
            String username = binding.userphone.getText().toString().trim();
            String password = binding.password.getText().toString().trim();
            binding.loading.setVisibility(View.VISIBLE);
            //do login here
            loginLiveData = loginViewModel.loginUser(username, password);

            loginLiveData.observe(this, loginModel -> {

                if (loginModel != null) {
                    if (loginModel.getStatus().equals("1")) {

                        sharedPreferences = requireContext().getSharedPreferences(PREF_NAME, MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean(IS_LOGGED_IN, true);
                        //loginModel.getLoginData().getName();

                        //save logged user data into sharedpreferences
                        userName = loginModel.getLoginData().getName();
                        userPhone = loginModel.getLoginData().getPhone();
                        userId = loginModel.getLoginData().getUid();
                        roll = loginModel.getLoginData().getRoll();
                        isVerified = loginModel.getLoginData().getIsVerified();
                        isAdmin = loginModel.getLoginData().getIsVerified();
                        authToken = loginModel.getAuthToken();


                        editor.putString("USER_NAME", userName);
                        editor.putString("USER_PHONE", userPhone);
                        editor.putString("USER_ROLL", roll);
                        editor.putString("USER_IS_VERIFIED", isVerified);
                        editor.putString("USER_IS_ADMIN", isAdmin);
                        editor.putString("USER_AUTH_TOCKEN", authToken);
                        editor.putInt("USER_ID", userId);

                        editor.apply();

                        //navigate to home fragment
                        NavController navController = Navigation.findNavController(requireView());
                        navController.navigate(R.id.nav_home);
                        binding.loading.setVisibility(View.GONE);
                        Toast.makeText(getContext(), "SuccessFully Logged In", Toast.LENGTH_SHORT).show();
                        // Remove the observer after successful login
                        loginLiveData.removeObservers(this);

                    } else if (loginModel.getStatus().equals("0")) {
                        binding.loading.setVisibility(View.GONE);
                        Toast.makeText(getContext(), "Invalid Credentials", Toast.LENGTH_LONG).show();
                        loginLiveData.removeObservers(this);
                    }
                } else {
                    Toast.makeText(getContext(), "Failed to Login ", Toast.LENGTH_LONG).show();
                    binding.loading.setVisibility(View.GONE);
                    loginLiveData.removeObservers(this);
                }

            });


        });


        return view;
    }

    // TextWatcher for userPhone and password EditText
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            // Unused
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            // Check if both userphone and password are not empty and have length greater than 5
            //aslo check if userphone is valid bangladeshi phone number
            boolean isPhoneValid = false;
            String username = binding.userphone.getText().toString().trim();
            String password = binding.password.getText().toString().trim();
            if (username.startsWith("017") || username.startsWith("018") || username.startsWith("019") || username.startsWith("016") || username.startsWith("015") || username.startsWith("013") || username.startsWith("014")) {

                if (username.length() == 11) {
                    isPhoneValid = true;
                    binding.errormsg.setVisibility(View.GONE);
                } else {
                    binding.errormsg.setText("Invalid Phone Number");
                    isPhoneValid = false;
                    binding.errormsg.setVisibility(View.VISIBLE);
                }

            } else {
                binding.errormsg.setText("Invalid Phone Number");
                isPhoneValid = false;
                binding.errormsg.setVisibility(View.VISIBLE);
            }
            boolean isValid = !username.isEmpty() && !password.isEmpty() && username.length() == 11 && password.length() > 5 && isPhoneValid == true;
            // Enable or disable the sign-up button based on the validation result
            binding.login.setEnabled(isValid);
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // Unused
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}