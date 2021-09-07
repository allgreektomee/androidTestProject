package com.devcation.project.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;

public class ParcelData implements Parcelable {

    public String phoneNumber;
    public String name;
    public String userName;
    public String password;


    public ParcelData(String name, String userName, String phoneNumber, String password) {
        this.name = name;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    protected ParcelData(Parcel in) {
        name = in.readString();
        userName = in.readString();
        phoneNumber = in.readString();
        password =  in.readString();;
    }



    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(userName);
        dest.writeString(phoneNumber);
        dest.writeString(password);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ParcelData> CREATOR = new Creator<ParcelData>() {
        @Override
        public ParcelData createFromParcel(Parcel in) {
            return new ParcelData(in);
        }

        @Override
        public ParcelData[] newArray(int size) {
            return new ParcelData[size];
        }
    };
}
