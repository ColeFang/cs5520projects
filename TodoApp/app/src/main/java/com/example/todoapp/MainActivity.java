package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.Settings;
import android.text.Layout;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import android.widget.*;

import com.example.todoapp.data.Task;
import com.example.todoapp.data.TaskItemRepository;
import com.example.todoapp.data.TaskRecyclerViewAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

//import com.example.todoapp.data.Task;
public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    public TaskRecyclerViewAdapter adapter = null;
    public TaskViewModel mTaskViewModel;
    public ConstraintLayout uiLayout;
    public FloatingActionButton mButton;
    public int length;
    public ImageView read;
    public ImageView exam;
    public ImageView presentation;
    public ImageView define;
    public ImageView meet;
    public ImageView code;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enableNotice();
        setContentView(R.layout.activity_main);
        uiLayout=(ConstraintLayout) findViewById(R.id.uis);
        uiLayout.setVisibility(View.GONE);
        mButton=(FloatingActionButton) findViewById(R.id.floatingActionButton);
        read=(ImageView) findViewById(R.id.read);
        define=(ImageView) findViewById(R.id.define);
        code=(ImageView) findViewById(R.id.coding);
        presentation=(ImageView) findViewById(R.id.presentation);
        exam=(ImageView) findViewById(R.id.exam);
        meet=(ImageView) findViewById(R.id.meeting);

        mTaskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        recyclerView = findViewById(R.id.recycler);
        adapter = new TaskRecyclerViewAdapter(new TaskRecyclerViewAdapter.TodoDiff(),mTaskViewModel);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setScrollingTouchSlop(0);
        mTaskViewModel.getAllToDos().observe(this, todos -> {
            adapter.submitList(todos);
            length=todos.size();
        });

        SimpleDateFormat   formatter   =   new SimpleDateFormat("yyyy/MM/dd   HH:mm:ss");
        define.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add_task = new Intent(MainActivity.this, MainActivity3.class);
                add_task.putExtra("IND", length);
                startActivityForResult(add_task,1);
            }
        });
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date curDate =  new Date(System.currentTimeMillis());
                mTaskViewModel.createTodo(new Task(length,"",0,"reading",formatter.format(curDate)));
            }
        });
        code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date curDate =  new Date(System.currentTimeMillis());
                mTaskViewModel.createTodo(new Task(length,"",1,"coding",formatter.format(curDate)));
            }
        });
        meet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date curDate =  new Date(System.currentTimeMillis());
                mTaskViewModel.createTodo(new Task(length,"",2,"meeting",formatter.format(curDate)));
            }
        });
        presentation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date curDate =  new Date(System.currentTimeMillis());
                mTaskViewModel.createTodo(new Task(length,"",3,"presentation",formatter.format(curDate)));
            }
        });
        exam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date curDate =  new Date(System.currentTimeMillis());
                mTaskViewModel.createTodo(new Task(length,"",4,"exam",formatter.format(curDate)));
            }
        });


        mTaskViewModel.getAllToDos();
    }
    @Override
    protected void onStart() {
        super.onStart();
        mTaskViewModel.getAllToDos();
    }

    public void open(View view){
        uiLayout.setVisibility(View.VISIBLE);
        mButton.setVisibility(View.GONE);
    }
    public void close(View view){
        uiLayout.setVisibility(View.GONE);
        mButton.setVisibility(View.VISIBLE);
    }


    public void enableNotice(){
        NotificationManagerCompat notification = NotificationManagerCompat.from(this);
        boolean isEnabled = notification.areNotificationsEnabled();
        if (!isEnabled) {
            AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setTitle("Notice")
                    .setMessage("Please turn on notification permissions in \"Notifications\"")
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    })
                    .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            Intent intent = new Intent();
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
                                intent.putExtra("android.provider.extra.APP_PACKAGE", MainActivity.this.getPackageName());
                            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {  //5.0
                                intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
                                intent.putExtra("app_package", MainActivity.this.getPackageName());
                                intent.putExtra("app_uid", MainActivity.this.getApplicationInfo().uid);
                                startActivity(intent);
                            } else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {  //4.4
                                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                intent.addCategory(Intent.CATEGORY_DEFAULT);
                                intent.setData(Uri.parse("package:" + MainActivity.this.getPackageName()));
                            } else if (Build.VERSION.SDK_INT >= 15) {
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                                intent.setData(Uri.fromParts("package", MainActivity.this.getPackageName(), null));
                            }
                            startActivity(intent);

                        }
                    })
                    .create();
            alertDialog.show();
            alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
        }
    }
}