package com.hfad.mvpstopwatch.presenter;

import android.os.Handler;

import com.hfad.mvpstopwatch.model.Time;

import java.io.Serializable;
import java.util.Locale;

public class MainPresenterImpl implements MainContractor.MainPresenter , Serializable{

    MainContractor.MainView mainView;
    Time time;
    Handler handler;
    Runnable runnable;

    @Override
    public void playThread() {
        stopThread();
        this.runnable = new Runnable() {
            @Override
            public void run() {
                if(time.isRunning){
                    time.second++;
                }
                int hour = (time.second / 3600);
                int minute = (time.second % 3600) / 60;
                int sec = time.second % 60;

                String str = String.format(Locale.getDefault(), "%d:%2d:%2d", hour, minute, sec);
                mainView.setTextView(str);
                handler.postDelayed(this, 1000);
            }
        };
        handler.post(runnable);
    }

    public void stopThread() {
        if(runnable !=null){
            handler.removeCallbacks(runnable);
        }
    }

    @Override
    public void attachView(MainContractor.MainView view) {
        this.mainView = view;
    }

    public MainPresenterImpl(final MainContractor.MainView mainView, final Time time){
        handler = new Handler();
        this.mainView = mainView;
        this.time = time;
        playThread();
    }

    @Override
    public void onResume() {
        time.isRunning = time.wasRunning;
        playThread();
    }

    @Override
    public void onPause() {
        time.wasRunning = time.isRunning;
        time.isRunning = false;
        stopThread();
    }

    @Override
    public void detachView() {
        mainView = null;
    }

    @Override
    public void onClickStartButton() {
        time.isRunning = true;
    }

    @Override
    public void onClickStopButton() {
        time.isRunning = false;
    }

    @Override
    public void onClickResetButton() {
        time.isRunning = false;
        time.second = 0;
    }

    @Override
    public void setData(Time time) {
        this.time = time;
    }

    @Override
    public Time getData() {
        return time;
    }


}
