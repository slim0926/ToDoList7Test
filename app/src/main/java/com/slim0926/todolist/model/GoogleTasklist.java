package com.slim0926.todolist.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sue on 1/16/17.
 */

public class GoogleTasklist implements Parcelable {
    private String mTitle;
    private String mID;
    private boolean mIsSelected;

    public GoogleTasklist() {

    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getID() {
        return mID;
    }

    public void setID(String ID) {
        mID = ID;
    }

    public boolean isSelected() {
        return mIsSelected;
    }

    public void setSelected(boolean selected) {
        mIsSelected = selected;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mTitle);
        parcel.writeString(mID);
        boolean[] isSelected = new boolean[1];
        isSelected[0] = mIsSelected;
        parcel.writeBooleanArray(isSelected);
    }

    public static final Creator<GoogleTasklist> CREATOR = new Creator<GoogleTasklist>() {
        @Override
        public GoogleTasklist createFromParcel(Parcel parcel) {
            GoogleTasklist tasklists = new GoogleTasklist();
            tasklists.mTitle = parcel.readString();
            tasklists.mID = parcel.readString();
            boolean[] isSelectedArray = new boolean[1];
            parcel.readBooleanArray(isSelectedArray);
            tasklists.mIsSelected = isSelectedArray[0];
            return tasklists;
        }

        @Override
        public GoogleTasklist[] newArray(int i) {
            return new GoogleTasklist[i];
        }
    };
}
