package com.learning.hostelmanagerv2.ui.profile;

import static android.content.Context.MODE_PRIVATE;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.learning.hostelmanagerv2.R;
import com.learning.hostelmanagerv2.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    String userName, userPhone, isAdmin, isVerified, authToken, roll;
    Integer userId;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProfileViewModel profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("login_pref", MODE_PRIVATE);
        userName = sharedPreferences.getString("USER_NAME", "");
        userPhone = sharedPreferences.getString("USER_PHONE", "");
        userId = sharedPreferences.getInt("USER_ID", 0);
        roll = sharedPreferences.getString("USER_ROLL", "");
        authToken = sharedPreferences.getString("USER_AUTH_TOCKEN", "");
        isAdmin = sharedPreferences.getString("USER_IS_ADMIN", "");
        isVerified = sharedPreferences.getString("USER_IS_VERIFIED", "");


        if (userName.equals("") || userPhone.equals("") || userId.equals(0)) {
            showPopupDialog();
        } else {

            switch (isVerified) {
                case "1":
                    binding.username.setText(userName);
                    binding.studentMobile.setText(userPhone);
                    binding.studentRoll.setText(roll);
                    break;
                case "0":
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                    // Set the title and messages
                    builder.setTitle("Warning")
                            .setIcon(R.drawable.ic_baseline_warning_amber_24)
                            .setMessage("Your account is not verified yet. Please contact admin to verify your account.")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // Do something if the user clicks OK
                                    dialog.dismiss();
                                }
                            });

                    AlertDialog alert = builder.create();
                    alert.show();
                    break;
                default:
                    Toast.makeText(getContext(), "You are not authorized", Toast.LENGTH_LONG).show();
                    break;
            }


        }

        //  showPopupDialog();

        binding.studentLogout.setOnClickListener(v -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();
            NavController navController = Navigation.findNavController(requireView());
            navController.navigate(R.id.nav_home);
            Toast.makeText(getContext(), "User Logout", Toast.LENGTH_SHORT).show();
        });
        return root;
    }

    private void showPopupDialog() {

        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popup_layout);

        // ImageView closeIcon = dialog.findViewById(R.id.closeIcon);
        TextView cancelBtn = dialog.findViewById(R.id.cancelButton);
        TextView loginBtn = dialog.findViewById(R.id.loginButton);
        dialog.setCancelable(false);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NavController navController = Navigation.findNavController(requireView());
                navController.navigate(R.id.nav_home);
                dialog.dismiss();
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(requireView());
                navController.navigate(R.id.nav_login);
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}