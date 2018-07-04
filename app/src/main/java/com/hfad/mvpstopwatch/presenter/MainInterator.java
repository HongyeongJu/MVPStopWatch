package com.hfad.mvpstopwatch.presenter;

public interface MainInterator {
    void setSecond(int sec);
    int getSecond();
    void setIsRunning(boolean isRunning);
    boolean getIsRunning();
    void setWasRunning(boolean wasRunning);
    boolean getWasRunning();
}
