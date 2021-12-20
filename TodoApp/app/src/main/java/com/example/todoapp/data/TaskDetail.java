package com.example.todoapp.data;

import androidx.room.DatabaseView;

@DatabaseView("SELECT  tasks.id, tasks.title,tasks.ddl, tasks.detail, tasks.tags," +
        "tasks.id AS id FROM tasks ")
public class TaskDetail {
}
