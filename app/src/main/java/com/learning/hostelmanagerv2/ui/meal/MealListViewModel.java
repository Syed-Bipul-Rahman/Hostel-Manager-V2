package com.learning.hostelmanagerv2.ui.meal;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.learning.hostelmanagerv2.services.model.MealData;
import com.learning.hostelmanagerv2.services.repository.MealListRepository;

import java.util.List;

public class MealListViewModel extends AndroidViewModel {

    MealListRepository mealListRepository;

    public MealListViewModel(@NonNull Application application) {
        super(application);
        mealListRepository = MealListRepository.getInstance(application);
    }

    public LiveData<List<MealData>> getAllMealList() {
        return mealListRepository.getMealList();
    }

}
