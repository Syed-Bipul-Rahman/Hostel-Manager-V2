package com.learning.hostelmanagerv2.services.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Students {
    @SerializedName("uid")
    @Expose
    private String uid;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("roll")
    @Expose
    private String roll;
    @SerializedName("registration")
    @Expose
    private String registration;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("father")
    @Expose
    private String father;
    @SerializedName("fatherphone")
    @Expose
    private String fatherphone;
    @SerializedName("mother")
    @Expose
    private String mother;
    @SerializedName("dist")
    @Expose
    private String dist;
    @SerializedName("upzila")
    @Expose
    private String upzila;
    @SerializedName("session")
    @Expose
    private String session;
    @SerializedName("room_no")
    @Expose
    private String roomNo;
    @SerializedName("student_id")
    @Expose
    private String studentId;
    @SerializedName("is_admin")
    @Expose
    private String isAdmin;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("is_verified")
    @Expose
    private String isVerified;

    public Students(String uid, String name, String roll, String registration, String phone, String father, String fatherphone, String mother, String dist, String upzila, String session, String roomNo, String studentId, String isAdmin, String password, String isVerified) {
        this.uid = uid;
        this.name = name;
        this.roll = roll;
        this.registration = registration;
        this.phone = phone;
        this.father = father;
        this.fatherphone = fatherphone;
        this.mother = mother;
        this.dist = dist;
        this.upzila = upzila;
        this.session = session;
        this.roomNo = roomNo;
        this.studentId = studentId;
        this.isAdmin = isAdmin;
        this.password = password;
        this.isVerified = isVerified;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public String getFatherphone() {
        return fatherphone;
    }

    public void setFatherphone(String fatherphone) {
        this.fatherphone = fatherphone;
    }

    public String getMother() {
        return mother;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }

    public String getDist() {
        return dist;
    }

    public void setDist(String dist) {
        this.dist = dist;
    }

    public String getUpzila() {
        return upzila;
    }

    public void setUpzila(String upzila) {
        this.upzila = upzila;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(String isVerified) {
        this.isVerified = isVerified;
    }
}
