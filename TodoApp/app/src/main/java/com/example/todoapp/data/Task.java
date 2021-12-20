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
    private int id;
    public Task(int id,String detail, int tags, String title, String ddl){
        this.detail=detail;
        this.ddl=ddl;
        this.tags=tags;
        this.title=title;
        this.id=id;
    }
    public Task(){};

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTags(int tags) {
        this.tags = tags;
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


    public int getTags() {
        return tags;
    }


}
