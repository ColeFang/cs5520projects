package com.example.todoapp.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.todoapp.data.TaskDataDao;

import java.util.List;


public class TaskDataSource{

    private TaskDataDao mTaskDataDao;

    public TaskDataSource(Application application) {
        TaskRoomDatabase db = TaskRoomDatabase.getDatabase(application);
        mTaskDataDao = db.TaskDao();
    }

    public void insert(Task task) {
        TaskRoomDatabase.databaseWriteExecutor.execute(() -> {
            mTaskDataDao.insert(task);
        });
    }

    public void delete(Task task) {
        TaskRoomDatabase.databaseWriteExecutor.execute(() -> {
            mTaskDataDao.delete(task);
        });

    }
    public LiveData<Task> getTask(int id){return mTaskDataDao.loadTask(id);}
    public LiveData<List<Task>> getTasks() {
        return mTaskDataDao.loadAll();
    }

}
