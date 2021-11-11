package com.example.todoapp.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Calendar;

@Entity(tableName = "tasks")
public class Task {
    @PrimaryKey
    @NonNull
    private String title;
    private String detail;
    private int tags;
    private String ddl;
    private boolean ifRemind;
    public Task(int id,String detail, int tags, String title, String ddl, boolean ifRemind){
        this.detail=detail;
        this.ddl=ddl;
        this.tags=tags;
        this.ifRemind=ifRemind;
        this.title=title;
    }
    public Task(){};

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTags(int tags) {
        this.tags = tags;
    }

    public void setIfRemind(boolean ifRemind) {
        this.ifRemind = ifRemind;
    }


    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setDdl(String ddl) {
        this.ddl = ddl;
    }

    public String getTitle() {
        return title;
    }


    public String getDetail() {
        return detail;
    }

    public String getDdl() {
        return ddl;
    }

    public boolean isIfRemind() {
        return ifRemind;
    }

    public int getTags() {
        return tags;
    }


}
