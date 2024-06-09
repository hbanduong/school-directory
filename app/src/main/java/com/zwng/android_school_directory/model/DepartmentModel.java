package com.zwng.android_school_directory.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class DepartmentModel implements Parcelable {
    private String id, name, email, website, logo, address, phoneNumber;

    public DepartmentModel() {
    }

    public DepartmentModel(String name) {
        this.name = name;
    }

    public DepartmentModel(String id, String name, String email, String website, String logo, String address, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.website = website;
        this.logo = logo;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    protected DepartmentModel(Parcel in) {
        id = in.readString();
        name = in.readString();
        email = in.readString();
        website = in.readString();
        logo = in.readString();
        address = in.readString();
        phoneNumber = in.readString();
    }

    public static final Creator<DepartmentModel> CREATOR = new Creator<DepartmentModel>() {
        @Override
        public DepartmentModel createFromParcel(Parcel in) {
            return new DepartmentModel(in);
        }

        @Override
        public DepartmentModel[] newArray(int size) {
            return new DepartmentModel[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(website);
        dest.writeString(logo);
        dest.writeString(address);
        dest.writeString(phoneNumber);
    }
}
