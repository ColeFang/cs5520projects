package com.example.todoapp;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.todoapp.data.Task;

import java.util.List;

import com.example.todoapp.data.TaskItemRepository;

public class TaskViewModel extends AndroidViewModel {

    private MutableLiveData<Boolean> todoCreated = new MutableLiveData<>();

    private TaskItemRepository repository;

    private final LiveData<List<Task>> mAllToDos;

    public TaskViewModel(Application application) {
        super(application);
        repository = TaskItemRepository.getSingleton(application);
        repository.createFakeData();
        mAllToDos = repository.getAllTasks();
        todoCreated.setValue(Boolean.FALSE);
    }
    public LiveData<Boolean> getTodoCreated() {
        return todoCreated;
    }

    public void createTodo(Task task) {
        repository.addToDo(task);
        todoCreated.setValue(Boolean.TRUE);
    }
    public void deleteTodo(Task task) {
        repository.delToDo(task);
        todoCreated.setValue(Boolean.FALSE);
    }
    public LiveData<Task> getTask(String title){
        return repository.getTask(title);
    }

    public LiveData<List<Task>> getAllToDos() {
        return mAllToDos;
    }
}