package com.anwesome.ui.archcomponentdemo;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by anweshmishra on 24/05/17.
 */

public class UserData extends LiveData<User> {
    private boolean isRunning = false;
    private Thread countingThread;
    private Activity activity;
    private CounterRunner counterRunner;
    public UserData(Activity activity) {
        this.activity = activity;
    }
    public void onActive() {
        if(counterRunner == null) {
            counterRunner = new CounterRunner();
        }
        if(!isRunning) {
            countingThread = new Thread(counterRunner);
            isRunning = true;
            countingThread.start();
        }
    }
    public void onInactive() {
        if(isRunning) {
            isRunning = false;
            while(true) {
                try {
                    countingThread.join();
                    break;
                }
                catch (Exception ex) {

                }
            }
        }
    }
    private class CounterRunner implements Runnable {
        private int counter = 0;
        public void run() {
            while(isRunning) {
                counter++;
                activity.runOnUiThread(new Runnable(){
                    public void run() {
                        setValue(new User("A"+counter,counter));
                    }
                });
                try {
                    Thread.sleep(1000);
                }
                catch (Exception ex) {

                }
            }
        }
    }
}
