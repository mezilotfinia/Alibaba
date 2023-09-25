package com.example.myapplication.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class TrainTicket implements Parcelable {
    private String id;
    private String trainId;
    private String origin;
    private String destination;
    private String startTime;
    private String endTime;
    private String date;
    private String type;
    private String capacity;
    private String coupeCapacity;
    private String price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getCoupeCapacity() {
        return coupeCapacity;
    }

    public void setCoupeCapacity(String coupeCapacity) {
        this.coupeCapacity = coupeCapacity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.trainId);
        dest.writeString(this.origin);
        dest.writeString(this.destination);
        dest.writeString(this.startTime);
        dest.writeString(this.endTime);
        dest.writeString(this.date);
        dest.writeString(this.type);
        dest.writeString(this.capacity);
        dest.writeString(this.coupeCapacity);
        dest.writeString(this.price);
    }

    public TrainTicket() {
    }

    protected TrainTicket(Parcel in) {
        this.id = in.readString();
        this.trainId = in.readString();
        this.origin = in.readString();
        this.destination = in.readString();
        this.startTime = in.readString();
        this.endTime = in.readString();
        this.date = in.readString();
        this.type = in.readString();
        this.capacity = in.readString();
        this.coupeCapacity = in.readString();
        this.price = in.readString();
    }

    public static final Parcelable.Creator<TrainTicket> CREATOR = new Parcelable.Creator<TrainTicket>() {
        @Override
        public TrainTicket createFromParcel(Parcel source) {
            return new TrainTicket(source);
        }

        @Override
        public TrainTicket[] newArray(int size) {
            return new TrainTicket[size];
        }
    };
}
