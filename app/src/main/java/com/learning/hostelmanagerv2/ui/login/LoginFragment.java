package com.learning.hostelmanagerv2.ui.login;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.learning.hostelmanagerv2.databinding.FragmentLoginBinding;


public class LoginFragment extends Fragment {
    FragmentLoginBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(getLayoutInflater(), container, false);

        View view = binding.getRoot();

//enable signup button while password and username is not empty and length is greater than 5
        // Add TextWatcher for userPhone EditText

        binding.userphone.addTextChangedListener(textWatcher);

        // Add TextWatcher for password EditText
        binding.password.addTextChangedListener(textWatcher);

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
            // Check if both usephone and password are not empty and have length greater than 5
            String username = binding.userphone.getText().toString().trim();
            String password = binding.password.getText().toString().trim();

            boolean isValid = !username.isEmpty() && !password.isEmpty() && username.length() == 11 && password.length() > 5;

            // Enable or disable the sign-up button based on the validation result
            binding.login.setEnabled(isValid);
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // Unused
        }
    };
}