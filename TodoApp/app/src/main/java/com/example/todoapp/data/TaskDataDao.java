package com.example.todoapp.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TaskDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Task... tasks);
    @Delete
    public void delete(Task... tasks);
    @Query("SELECT * FROM tasks")
    LiveData<List<Task>> loadAll();
    @Query("DELETE FROM tasks")
    public void deleteAll();
    @Query("SELECT * FROM tasks WHERE id==:taskID")
    public LiveData<Task> loadTask(int taskID);

}
