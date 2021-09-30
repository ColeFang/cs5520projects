package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

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

public class MainActivity extends AppCompatActivity {
    List<Task> task_list = new ArrayList<Task>();
    public LinearLayout layout;
    public static final String EXTRA_TASK ="Task";
    public static final String EXTRA_IND ="Ind";
    public static final String LOG_TAG = MainActivity.class.getSimpleName();
    CheckBox[] check_list = new CheckBox[100];
    public void onCreateNewTaks(int x){
        CheckBox cbx = new CheckBox(this);
        cbx.setId(x);
        cbx.setText(task_list.get(x).getTitle()+"   :   "+ task_list.get(x).getDdl());
        cbx.setTextSize(41);
        layout.addView(cbx, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        cbx.setBackgroundColor(Color.parseColor("#FFC0CB"));
        cbx.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent task_intend = new Intent(MainActivity.this, MainActivity2.class);
                task_intend.putExtra(EXTRA_TASK, task_list.get(cbx.getId()));
                task_intend.putExtra(EXTRA_IND, cbx.getId());
                startActivityForResult(task_intend,1);
            }
        });
        check_list[x]=cbx;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout=findViewById(R.id.tasklist);


        if (savedInstanceState != null) {
            boolean isVisible =
                    savedInstanceState.getBoolean("Existing tasks");
            if (isVisible) {
                task_list = savedInstanceState.getParcelableArrayList("reply_text");
            }
        }
        for (int x = 0; x < task_list.size(); x = x+1){
            onCreateNewTaks(x);
            Log.d(LOG_TAG, "Save Edit");
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == 1) {
                Task edited_task=data.getParcelableExtra(MainActivity2.EXTRA_TASK);
                int ind = data.getIntExtra(MainActivity2.EXTRA_IND,0);
                task_list.set(ind, edited_task);
                check_list[ind].setText(edited_task.getTitle()+"   :   "+ edited_task.getDdl());
            }
            if (resultCode == 2) {
                Task edited_task=data.getParcelableExtra(MainActivity3.EXTRA_TASK);
                task_list.add(edited_task);
                onCreateNewTaks(task_list.size()-1);
            }
        }
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (task_list.size()>0) {
            outState.putInt("Existing tasks", task_list.size());
            outState.putParcelableArrayList("list", (ArrayList<? extends Parcelable>)task_list);
        }
    }

    public void AddTask(View view) {
        //onCreateNewTaks(1);
        Intent add_task = new Intent(MainActivity.this, MainActivity3.class);
        startActivityForResult(add_task,1);
    }
}