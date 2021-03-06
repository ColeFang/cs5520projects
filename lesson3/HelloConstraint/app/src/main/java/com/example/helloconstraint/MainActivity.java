package com.example.helloconstraint;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int mCount = 0;
    private TextView mShowCount;
    private Button mButtonCount;
    private Button mButtonZero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShowCount = (TextView) findViewById(R.id.show_count);
        mButtonCount = (Button) findViewById(R.id.button_count);
        mButtonZero = (Button) findViewById(R.id.button_zero);
    }

    public void showToast(View view) {
        Toast toast = Toast.makeText(this, R.string.toast_message,
                Toast.LENGTH_SHORT);
        toast.show();
    }

    public void countUp(View view) {
        mCount++;
        if (mShowCount != null)
            mShowCount.setText(Integer.toString(mCount));
        if(mCount != 0)
            mButtonZero.setBackgroundColor(Color.parseColor("#FFC0CB"));
        if(mCount%2 == 1)
            mButtonCount.setBackgroundColor(Color.parseColor("#00FF00"));
        else
            mButtonCount.setBackgroundColor(Color.parseColor("#FF3700B3"));


    }

    public void clear(View view) {
        mCount=0;
        if (mShowCount != null)
            mShowCount.setText(Integer.toString(mCount));
        mButtonZero.setBackgroundColor(Color.parseColor("#808080"));
    }
}