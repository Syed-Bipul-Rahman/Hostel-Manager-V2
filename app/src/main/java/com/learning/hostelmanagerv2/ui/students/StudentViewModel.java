package com.learning.hostelmanagerv2.ui.students;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.learning.hostelmanagerv2.services.model.Students;
import com.learning.hostelmanagerv2.services.repository.StudentRepository;

import java.util.List;

public class StudentViewModel extends AndroidViewModel {


    StudentRepository allStudentsRepository;


    public StudentViewModel(@NonNull Application application) {
        super(application);

        allStudentsRepository = StudentRepository.getInstance(application);
    }


    public LiveData<List<Students>> getAllStudentList() {
        return allStudentsRepository.getStudentList();
    }

}
