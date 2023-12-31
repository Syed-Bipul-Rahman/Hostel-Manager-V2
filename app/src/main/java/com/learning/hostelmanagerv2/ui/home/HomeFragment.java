package com.learning.hostelmanagerv2.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.learning.hostelmanagerv2.R;
import com.learning.hostelmanagerv2.adapter.HostelSuperHomeAdapter;
import com.learning.hostelmanagerv2.adapter.NoticeAdapter;
import com.learning.hostelmanagerv2.databinding.FragmentHomeBinding;
import com.learning.hostelmanagerv2.services.model.Notice;
import com.learning.hostelmanagerv2.ui.admin.AdminFragmentViewModel;
import com.learning.hostelmanagerv2.ui.notice.NoticeViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements NoticeAdapter.OnItemClickListener {

    private FragmentHomeBinding binding;
    NoticeAdapter noticeAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //last three notice
        binding.noticeRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        //noticeviewmodel is used to get notice list
        NoticeViewModel noticeViewModel = new ViewModelProvider(this).get(NoticeViewModel.class);


        noticeViewModel.getAllNotice().observe(getViewLifecycleOwner(), notices -> {
//only three notice from list
            if (notices.size() > 3) {
                //notices = notices.subList(0, 3);

                List<Notice> firstThreeNotices = notices.subList(0, Math.min(notices.size(), 3));


                noticeAdapter = new NoticeAdapter(getContext(), firstThreeNotices, HomeFragment.this);
                binding.noticeRecycler.setAdapter(noticeAdapter);
                binding.noticeRecycler.setVisibility(View.VISIBLE);
                binding.progresbarNoticesHome.setVisibility(View.GONE);

            } else {
                noticeAdapter = new NoticeAdapter(getContext(), notices, HomeFragment.this);
                binding.noticeRecycler.setAdapter(noticeAdapter);
                binding.noticeRecycler.setVisibility(View.VISIBLE);
                binding.progresbarNoticesHome.setVisibility(View.GONE);

            }


        });

        //navigate to all notice
        binding.seemoreNotice.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.nav_slideshow);


        });
        //navigate to gallery
        binding.seeAllGallery.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.nav_galler_original);


        });
        //navigate to all admin
        binding.seeAllAdmin.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.nav_hostelsupar);


        });

        //slider
        ImageSlider imageSlider = binding.imgSlid;

        ArrayList<SlideModel> imageList = new ArrayList<>();


        imageList.add(new SlideModel(R.drawable.hostel, ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.hostelimage, ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.hostelsite, ScaleTypes.CENTER_CROP));


        imageSlider.setImageList(imageList);

        //set horizontal recycler view for hostel supar

        binding.personRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        AdminFragmentViewModel adminFragmentViewModel = new ViewModelProvider(this).get(AdminFragmentViewModel.class);

        adminFragmentViewModel.getAllAdmin().observe(getViewLifecycleOwner(), admins -> {
            binding.personRecycler.setAdapter(new HostelSuperHomeAdapter(getContext(), admins));
            binding.personRecycler.setVisibility(View.VISIBLE);
            binding.progressbarPersonHome.setVisibility(View.GONE);
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemClick(Notice notice) {


        Bundle bundle = new Bundle();

        bundle.putString("noticedetails", notice.getDescrip());

        getParentFragmentManager().setFragmentResult("sendNotice", bundle);

        NavController navController = Navigation.findNavController(requireView());
        navController.navigate(R.id.nav_notice_details);
    }
}