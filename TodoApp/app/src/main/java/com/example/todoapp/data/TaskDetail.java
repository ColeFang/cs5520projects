package com.example.todoapp.data;

import androidx.room.DatabaseView;

@DatabaseView("SELECT  tasks.title,tasks.ddl, tasks.detail, tasks.tags,  tasks.ifRemind," +
        "tasks.title AS title FROM tasks ")
public class TaskDetail {
}
