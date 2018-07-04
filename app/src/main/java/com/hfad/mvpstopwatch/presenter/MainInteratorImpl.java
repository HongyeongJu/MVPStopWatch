package com.hfad.mvpstopwatch.presenter;

import com.hfad.mvpstopwatch.model.Time;

public class MainInteratorImpl implements MainInterator {

    Time time;

    public MainInteratorImpl(Time time){
        this.time = time;
    }

    @Override
    public void setSecond(int sec) {

    }

    @Override
    public int getSecond() {
        return 0;
    }

    @Override
    public void setIsRunning(boolean isRunning) {

    }

    @Override
    public boolean getIsRunning() {
        return false;
    }

    @Override
    public void setWasRunning(boolean wasRunning) {

    }

    @Override
    public boolean getWasRunning() {
        return false;
    }
}
