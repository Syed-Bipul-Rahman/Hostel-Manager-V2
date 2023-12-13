package com.learning.hostelmanagerv2.services.network;

import com.learning.hostelmanagerv2.services.model.AllAdmin;
import com.learning.hostelmanagerv2.services.model.AllGallery;
import com.learning.hostelmanagerv2.services.model.AllNotice;
import com.learning.hostelmanagerv2.services.model.AllStudents;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    //student list
    @GET("verifed-students.php")
    Call<AllStudents> getAllStudentsLists();

    //all notice
    @GET("notice.php")
    Call<AllNotice> getAllNotice();

    //all admin
    @GET("hall-admin.php")
    Call<AllAdmin> getAllAdmin();

    //gallery image url
    @GET("all-gallery.php")
    Call<AllGallery> getAllGallery();


    //    get result
//    @GET("individualResult?roll=476734&regulation=2016")
//    Call<AllResult> getAllResult();


    //dynamic url main
    //  @GET()
//    Call<AllResult> getAllResult(@Url String url);
//
//    @GET("meal-coast.php")
//    Call<MealList> getMealList();


//    //test
//    @GET()
//    Call<ApiResponse> getAllResult(@Url String url);

//
//    @FormUrlEncoded
//    @POST("login.php")
//    Call<LoginModel> getLogin(@Field("phone") String phone,
//                              @Field("password") String password);

//    @FormUrlEncoded
//    @POST("register.php")
//    Call<LoginModel> registerUser(@Field("name") String name,
//                                  @Field("roll") String roll,
//                                  @Field("registration") String registration,
//                                  @Field("phone") String phone,
//                                  @Field("father") String father,
//                                  @Field("fatherphone") String fatherphone,
//                                  @Field("mother") String mother,
//                                  @Field("dist") String dist,
//                                  @Field("upzila") String upzila,
//                                  @Field("session") String session,
//                                  @Field("password") String password);
//

}


