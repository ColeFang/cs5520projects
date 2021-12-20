package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import com.example.todoapp.data.Task;


public class MainActivity3 extends AppCompatActivity implements AdapterView.OnItemSelectedListener, TimePickerDialog.OnTimeSetListener{
    public EditText mTitle;
    public EditText mDetail;
    public TextView mDdl;
    public Button mDtr;
    public Spinner mTag;
    private boolean isAlarm = false;
    private int ind;
    public Task newTask=new Task();
    public static final String LOG_TAG = MainActivity2.class.getSimpleName();
    String[] tags = new String[]{"reading","coding","meeting","presentation","exam"};
    public static final String EXTRA_TASK ="Task";
    private TaskViewModel mTaskViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Intent intent = getIntent();
        ind = intent.getIntExtra("IND",0);
        mTitle = findViewById(R.id.titles);
        mDetail = findViewById(R.id.detail);
        mDdl = findViewById(R.id.ddl);
        mDtr = findViewById(R.id.dtr);
        mTag = (Spinner) findViewById(R.id.tags);
        mTag.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,tags);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mTag.setAdapter(aa);
        mTaskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        mDdl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePicker();
                timePicker.show(getSupportFragmentManager(), "time picker1");
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
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd   HH:mm:ss");
        if (isAlarm){
            startAlarm(c);
            this.isAlarm=false;
        }
        else {
            mDdl.setText(formatter.format(c.getTime()));
        }
    }
    private void startAlarm(Calendar c) {
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        String title=String.valueOf(mTitle.getText());
        intent.putExtra("TITLE",String.valueOf(mTitle.getText()));
        intent.putExtra("IND",this.ind);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, ind, intent, 0);
        if (c.before(Calendar.getInstance())) {
            c.add(Calendar.DATE, 1);
        }
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
    }
    private void cancelAlarm() {
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, ind, intent, 0);
        alarmManager.cancel(pendingIntent);
    }
    public void Cancel(View view) {
        mTitle.setText("");
        mDetail.setText("");
        mDdl.setText("");
        mTag.setSelection(0);
        cancelAlarm();
    }

    public void Save(View view) {
        newTask.setId(ind);
        newTask.setDdl(String.valueOf(mDdl.getText()));
        newTask.setTitle(String.valueOf(mTitle.getText()));
        newTask.setTags(Arrays.binarySearch(tags, String.valueOf(mTag.getSelectedItem())));
        newTask.setDetail(String.valueOf(mDetail.getText()));
        Log.d(LOG_TAG, "Save Edit");
        mTaskViewModel.createTodo(newTask);
        Intent replyIntent = new Intent();
        setResult(2,replyIntent);
        finish();
    }
    public void AddAlarm(View view) {
        this.isAlarm=true;
        DialogFragment timePicker = new TimePicker();
        timePicker.show(getSupportFragmentManager(), "time picker");
    }

}