package com.hfad.mvpstopwatch.presenter;

import com.hfad.mvpstopwatch.model.Time;

//contractor
public interface MainContractor {

    interface MainPresenter {

        void playThread();

        void attachView(MainView view);

        public void detachView();

        public void onClickStartButton();

        public void onClickStopButton();

        public void onClickResetButton();

        public void setData(Time time);

        public Time getData();

        public void onResume();

        public void onPause();

    }

    interface MainView {
        public void setTextView(String time);
    }
}
