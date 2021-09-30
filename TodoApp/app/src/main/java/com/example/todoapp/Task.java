package com.example.todoapp;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import java.io.Serializable;

public class Task implements Parcelable {
    private String detail;
    private int tags;
    private String title;
    private String ddl;
    private String dtr;
    private boolean ifRemind;
    public boolean isIfRemind() {
        return ifRemind;
    }

    public String getDdl() {
        return ddl;
    }

    public String getDetail() {
        return detail;
    }

    public String getDtr() {
        return dtr;
    }

    public int getTags() {
        return tags;
    }

    public String getTitle() {
        return title;
    }

    public void setDdl(String ddl) {
        this.ddl = ddl;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setDtr(String dtr) {
        this.dtr = dtr;
    }

    public void setIfRemind(boolean ifRemind) {
        this.ifRemind = ifRemind;
    }

    public void setTags(int tags) {
        this.tags = tags;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.title);
        parcel.writeString(this.detail);
        parcel.writeInt(this.tags);
        parcel.writeString(this.ddl);
        parcel.writeString(this.dtr);
        parcel.writeBoolean(this.ifRemind);


    }
    public static final Parcelable.Creator<Task> CREATOR  = new Creator<Task>(){
        @RequiresApi(api = Build.VERSION_CODES.Q)
        @Override
        public Task createFromParcel(Parcel source) {
            Task app=  new Task();
            app.title = source.readString();
            app.detail = source.readString();
            app.tags= source.readInt();
            app.ddl = source.readString();
            app.dtr= source.readString();
            app.ifRemind = source.readBoolean();
            return app;
        }
        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };
}
