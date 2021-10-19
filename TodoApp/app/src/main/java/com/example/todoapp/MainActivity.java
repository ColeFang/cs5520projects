package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Layout;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import android.widget.*;

import com.example.todoapp.data.Task;
import com.example.todoapp.data.TaskItemRepository;
import com.example.todoapp.data.TaskRecyclerViewAdapter;

//import com.example.todoapp.data.Task;
public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    public TaskRecyclerViewAdapter adapter = null;
    public TaskViewModel mTaskViewModel;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler);
        adapter = new TaskRecyclerViewAdapter(new TaskRecyclerViewAdapter.TodoDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setScrollingTouchSlop(0);

        mTaskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        mTaskViewModel.getAllToDos().observe(this, todos -> {
            adapter.submitList(todos);
        });

        mTaskViewModel.getAllToDos();
    }
    @Override
    protected void onStart() {
        super.onStart();
        mTaskViewModel.getAllToDos();
    }

    public void AddTask(View view) {
        //onCreateNewTaks(1);
        Intent add_task = new Intent(MainActivity.this, MainActivity3.class);
        startActivityForResult(add_task,1);
    }
}