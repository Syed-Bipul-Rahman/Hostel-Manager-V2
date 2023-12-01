package com.learning.hostelmanagerv2.services.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SingleAdmin {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("hall_supar_name")
    @Expose
    private String hallSuparName;
    @SerializedName("hall_supar_phone")
    @Expose
    private String hallSuparPhone;
    @SerializedName("position")
    @Expose
    private String position;

    public SingleAdmin(String id, String hallSuparName, String hallSuparPhone, String position) {
        this.id = id;
        this.hallSuparName = hallSuparName;
        this.hallSuparPhone = hallSuparPhone;
        this.position = position;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHallSuparName() {
        return hallSuparName;
    }

    public void setHallSuparName(String hallSuparName) {
        this.hallSuparName = hallSuparName;
    }

    public String getHallSuparPhone() {
        return hallSuparPhone;
    }

    public void setHallSuparPhone(String hallSuparPhone) {
        this.hallSuparPhone = hallSuparPhone;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
