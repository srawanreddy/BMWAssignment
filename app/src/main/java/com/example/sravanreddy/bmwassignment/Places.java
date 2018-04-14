package com.example.sravanreddy.bmwassignment;

import android.os.Parcel;
import android.os.Parcelable;

public class Places implements Parcelable {
private String Name, Address, ArrivalTime;
private long ID;
private double Latitude, Longitude;
    public Places(long ID, String name, double latitude, double longitude, String address, String arrivalTime) {
        this.ID = ID;
        Name = name;
        Latitude = latitude;
        Longitude = longitude;
        Address = address;
        ArrivalTime = arrivalTime;
    }

    protected Places(Parcel in) {
        Name = in.readString();
        Address = in.readString();
        ArrivalTime = in.readString();
        ID = in.readLong();
        Latitude = in.readDouble();
        Longitude = in.readDouble();
    }

    public static final Creator<Places> CREATOR = new Creator<Places>() {
        @Override
        public Places createFromParcel(Parcel in) {
            return new Places(in);
        }

        @Override
        public Places[] newArray(int size) {
            return new Places[size];
        }
    };

    public long getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public double getLatitude() {
        return Latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public String getAddress() {
        return Address;
    }

    public String getArrivalTime() {
        return ArrivalTime;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Name);
        parcel.writeString(Address);
        parcel.writeString(ArrivalTime);
        parcel.writeLong(ID);
        parcel.writeDouble(Latitude);
        parcel.writeDouble(Longitude);
    }

}
