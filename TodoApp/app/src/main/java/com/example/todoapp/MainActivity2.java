package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import com.example.todoapp.data.Task;

public class MainActivity2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener, TimePickerDialog.OnTimeSetListener{
    public TextView mTitle;
    public EditText mDetail;
    public EditText mDdl;
    public Button mDtr;
    public Spinner mTag;
    public CheckBox mCheck;
    public Task task;
    public static final String LOG_TAG = MainActivity2.class.getSimpleName();
    String[] tags = new String[]{"courses","coding","contest","resume","interview","exam"};
    private TaskViewModel mTaskViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mTaskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        Intent intent = getIntent();
        String ind = intent.getStringExtra("IND");

        mTaskViewModel.getTask(ind);
        mTitle = findViewById(R.id.titles);
        mDetail = findViewById(R.id.detail);
        mDdl = findViewById(R.id.ddl);
        mDtr = findViewById(R.id.dtr);
        mTag = (Spinner) findViewById(R.id.tags);
        mTag.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,tags);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mTag.setAdapter(aa);
        mCheck = findViewById(R.id.checkBox1);
        mTaskViewModel.getTask(ind).observe(this, tasks -> {
            mTitle.setText(tasks.getTitle());
            mDetail.setText(tasks.getDetail());
            mDdl.setText(tasks.getDdl());
            mCheck.setChecked(tasks.isIfRemind());
            mTag.setSelection(tasks.getTags());
        });
        mTaskViewModel.getTask(ind);
        task=new Task();
        task.setDdl(String.valueOf(mDdl.getText()));
        task.setTitle(String.valueOf(mTitle.getText()));
        task.setTags(Arrays.binarySearch(tags, String.valueOf(mTag.getSelectedItem())));
        task.setDetail(String.valueOf(mDetail.getText()));
        task.setIfRemind(mCheck.isChecked());
        mDtr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePicker();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(getApplicationContext(), tags[i], Toast.LENGTH_LONG).show();
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
// TODO Auto-generated method stub

    }
    @Override
    public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);
        updateTimeText(c);
        startAlarm(c);
    }
    public void Cancel(View view) {
        mDetail.setText("");
        mDdl.setText("");
        mTag.setSelection(0);
        mCheck.setChecked(false);
        cancelAlarm();
    }

    private void updateTimeText(Calendar c) {
        String timeText = "Alarm set for: ";
        timeText += DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());
    }
    private void startAlarm(Calendar c) {
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        String title=this.task.getTitle();
        intent.putExtra("title",title);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);
        if (c.before(Calendar.getInstance())) {
            c.add(Calendar.DATE, 1);
        }
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
    }
    private void cancelAlarm() {
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);
        alarmManager.cancel(pendingIntent);
    }

    public void Save(View view) {
        Task newTask = new Task();
        newTask.setDdl(String.valueOf(mDdl.getText()));
        newTask.setTitle(String.valueOf(mTitle.getText()));
        newTask.setTags(Arrays.binarySearch(tags, String.valueOf(mTag.getSelectedItem())));
        newTask.setDetail(String.valueOf(mDetail.getText()));
        newTask.setIfRemind(mCheck.isChecked());
        Log.d(LOG_TAG, "Save Edit");
        Intent replyIntent = new Intent();
        mTaskViewModel.deleteTodo(task);
        mTaskViewModel.createTodo(newTask);
        setResult(1,replyIntent);
        finish();
    }
}