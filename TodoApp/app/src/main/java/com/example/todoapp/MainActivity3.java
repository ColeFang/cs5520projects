package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity3 extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    public EditText mTitle;
    public EditText mDetail;
    public EditText mDdl;
    public EditText mDtr;
    public Spinner mTag;
    public CheckBox mCheck;
    public Task newTask = new Task();
    public static final String LOG_TAG = MainActivity2.class.getSimpleName();
    String[] tags = new String[]{"courses","coding","contest","resume","interview","exam"};
    public static final String EXTRA_TASK ="Task";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

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

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(getApplicationContext(), tags[i], Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
// TODO Auto-generated method stub

    }
    public void Cancel(View view) {
        mTitle.setText("");
        mDetail.setText("");
        mDdl.setText("");
        mDtr.setText("");
        mTag.setSelection(0);
        mCheck.setChecked(false);
    }

    public void Save(View view) {
        newTask.setDdl(String.valueOf(mDdl.getText()));
        newTask.setTitle(String.valueOf(mTitle.getText()));
        newTask.setTags(Arrays.binarySearch(tags, String.valueOf(mTag.getSelectedItem())));
        newTask.setDetail(String.valueOf(mDetail.getText()));
        newTask.setDtr(String.valueOf(mDtr.getText()));
        newTask.setIfRemind(mCheck.isChecked());
        Log.d(LOG_TAG, "Save Edit");
        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_TASK, newTask);
        setResult(2,replyIntent);
        finish();
    }

}