package com.hfad.mvpstopwatch.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hfad.mvpstopwatch.R;
import com.hfad.mvpstopwatch.model.Time;
import com.hfad.mvpstopwatch.presenter.MainContractor;
import com.hfad.mvpstopwatch.presenter.MainPresenterImpl;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MainContractor.MainView {
    public final String SECOND = "second";
    public final String ISRUNNING = "isRunning";
    public final String WASRUNNING = "wasRunning";

    TextView textView;
    Button startButton;
    Button stopButton;
    Button resetButton;

    MainPresenterImpl presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState !=null){
            presenter = (MainPresenterImpl)savedInstanceState.getSerializable("presenter");
            presenter.attachView(this);                 // 원래 가지고 있던 MainActivity는 사라졌으므로. 주소를 새로 받고
            presenter.playThread();                     // 다시 스레드를 실행시킨다.
        }else {
            presenter = new MainPresenterImpl(this, new Time());
        }

        //텍스트 버튼 findViewById적용
        textView = (TextView)findViewById(R.id.textView);
        startButton  = (Button)findViewById(R.id.startButton);
        stopButton = (Button)findViewById(R.id.stopButton);
        resetButton = (Button)findViewById(R.id.resetButton);

        startButton.setOnClickListener(this);
        stopButton.setOnClickListener(this);
        resetButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.startButton){
            presenter.onClickStartButton();
        }else if(v.getId() == R.id.stopButton){
            presenter.onClickStopButton();
        }else if(v.getId() == R.id.resetButton) {
            presenter.onClickResetButton();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("presenter", presenter);
    }

    @Override
    public void setTextView(String time) {
        textView.setText(time);
    }
}

