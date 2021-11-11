package com.example.todoapp.data;

import android.app.Application;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;

import com.example.todoapp.data.Task;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class TaskItemRepository implements Iterable<Task>{

    private TaskDataSource mTaskDataSource;

    private static TaskItemRepository singleton;

    private TaskItemRepository(Application application) {
//        mTaskDataSource = new ToDoInMemoryDataSource();
        mTaskDataSource = new TaskDataSource(application);
        this.createFakeData();
    }

    public static TaskItemRepository getSingleton(Application application) {
        if (singleton == null) {
            singleton = new TaskItemRepository(application);
        }
        return singleton;
    }
    public void createFakeData() {
        addToDo(new Task(1,"do something, already",3,"Task todo 1","",true ));
        addToDo(new Task(2,"and another thign!",0, "Task todo 2","",false ));
    }


    public LiveData<List<Task>> getAllTasks() {
        return mTaskDataSource.getTasks();
    }

    public void addToDo(Task newTask) {
        mTaskDataSource.insert(newTask);
    }
    public void delToDo(Task newTask) {
        mTaskDataSource.delete(newTask);
    }
    public LiveData<Task> getTask(String title){return mTaskDataSource.getTask(title); }

    @NonNull
    @Override
    public Iterator<Task> iterator() {
        return mTaskDataSource.getTasks().getValue().iterator();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void forEach(@NonNull Consumer<? super Task> action) {
        mTaskDataSource.getTasks().getValue().forEach(action);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @NonNull
    @Override
    public Spliterator<Task> spliterator() {
        return mTaskDataSource.getTasks().getValue().spliterator();
    }
}
