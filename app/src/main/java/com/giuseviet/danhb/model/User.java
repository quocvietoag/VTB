package com.giuseviet.danhb.model;

public class User {

    private int mID;//id riêng của từng người
    private String mGender;//giới tính
    private String mName;//tên
    private String mPhoneNumber;//số điện thoại
    private String mEmail;
    private String mAddress;//địa chỉ

    public User() {
    }

    public User(int mID, String mName, String mGender, String mPhoneNumber, String mEmail, String mAddress) {
        this.mID = mID;
        this.mName = mName;
        this.mGender = mGender;
        this.mPhoneNumber = mPhoneNumber;
        this.mEmail = mEmail;
        this.mAddress = mAddress;
    }

    public User(String mName, String mGender,  String mPhoneNumber, String mEmail, String mAddress) {
        this.mName = mName;
        this.mGender = mGender;
        this.mPhoneNumber = mPhoneNumber;
        this.mEmail = mEmail;
        this.mAddress = mAddress;
    }

    public int getmID() {
        return mID;
    }

    public void setmID(int mID) {
        this.mID = mID;
    }

    public String getmGender() {
        return mGender;
    }

    public void setmGender(String mGender) {
        this.mGender = mGender;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmPhoneNumber() {
        return mPhoneNumber;
    }

    public void setmPhoneNumber(String mPhoneNumber) {
        this.mPhoneNumber = mPhoneNumber;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }
}
