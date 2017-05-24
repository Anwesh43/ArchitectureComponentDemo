package com.anwesome.ui.archcomponentdemo;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.support.annotation.MainThread;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by anweshmishra on 24/05/17.
 */

public class UserData extends LiveData<User> {
    private boolean isRunning = false;
    private Thread countingThread;
    private Activity activity;
    private CounterRunner counterRunner = new CounterRunner();
    public UserData(Activity activity) {
        this.activity = activity;
        //startThread();
    }
    private void startThread() {
        isRunning = true;
        countingThread = new Thread(counterRunner);
        countingThread.start();
    }
    public void onActive() {
        if(counterRunner == null) {
            counterRunner = new CounterRunner();
        }
        if(!isRunning) {
            startThread();
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
    @MainThread
    public void setUser(User user) {
        setValue(user);
    }
    private class CounterRunner implements Runnable {
        private int counter = 0;
        public void run() {
            while(isRunning) {
                counter++;
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setUser(new User("A"+counter,counter));
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
