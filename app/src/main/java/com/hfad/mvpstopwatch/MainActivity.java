package com.hfad.mvpstopwatch;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    final String SECOND = "second";
    final String ISRUNNING = "isRunning";
    final String WASRUNNING = "wasRunning";

    TextView textView;
    Button startButton;
    Button stopButton;
    Button resetButton;

    int second = 0;

    boolean isRunning = false;
    boolean wasRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState !=null){
            second = savedInstanceState.getInt(SECOND);
            isRunning = savedInstanceState.getBoolean(ISRUNNING);
            wasRunning = savedInstanceState.getBoolean(WASRUNNING);
        }

        //텍스트 버튼 findViewById적용
        textView = (TextView)findViewById(R.id.textView);
        startButton  = (Button)findViewById(R.id.startButton);
        stopButton = (Button)findViewById(R.id.stopButton);
        resetButton = (Button)findViewById(R.id.resetButton);

        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(isRunning){
                    second++;
                }
                int hour = (second / 3600);
                int minute = (second % 3600) / 60;
                int sec = second % 60;

                String str = String.format(Locale.getDefault(), "%d:%2d:%2d", hour, minute, sec);
                textView.setText(str);
                handler.postDelayed(this, 1000);
            }
        });

        startButton.setOnClickListener(this);
        stopButton.setOnClickListener(this);
        resetButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.startButton){
            isRunning = true;
        }else if(v.getId() == R.id.stopButton){
            isRunning = false;
        }else if(v.getId() == R.id.resetButton) {
            isRunning  = false;
            second = 0;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        isRunning = wasRunning;
    }

    @Override
    protected void onPause() {
        super.onPause();
        wasRunning = isRunning;
        isRunning = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SECOND, second);
        outState.putBoolean(ISRUNNING, isRunning);
        outState.putBoolean(WASRUNNING, wasRunning);
    }
}

