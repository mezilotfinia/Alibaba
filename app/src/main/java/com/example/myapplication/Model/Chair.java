package com.example.myapplication.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Chair implements Parcelable {
    private String left;
    private String situationLeft;
    private String right;
    private String situationRight;
    private String rightOne;
    private String situationOne;

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public String getSituationLeft() {
        return situationLeft;
    }

    public void setSituationLeft(String situationLeft) {
        this.situationLeft = situationLeft;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }

    public String getSituationRight() {
        return situationRight;
    }

    public void setSituationRight(String situationRight) {
        this.situationRight = situationRight;
    }

    public String getRightOne() {
        return rightOne;
    }

    public void setRightOne(String rightOne) {
        this.rightOne = rightOne;
    }

    public String getSituationOne() {
        return situationOne;
    }

    public void setSituationOne(String situationOne) {
        this.situationOne = situationOne;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.left);
        dest.writeString(this.situationLeft);
        dest.writeString(this.right);
        dest.writeString(this.situationRight);
        dest.writeString(this.rightOne);
        dest.writeString(this.situationOne);
    }

    public Chair() {
    }

    protected Chair(Parcel in) {
        this.left = in.readString();
        this.situationLeft = in.readString();
        this.right = in.readString();
        this.situationRight = in.readString();
        this.rightOne = in.readString();
        this.situationOne = in.readString();
    }

    public static final Parcelable.Creator<Chair> CREATOR = new Parcelable.Creator<Chair>() {
        @Override
        public Chair createFromParcel(Parcel source) {
            return new Chair(source);
        }

        @Override
        public Chair[] newArray(int size) {
            return new Chair[size];
        }
    };
}
