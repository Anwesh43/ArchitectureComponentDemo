package com.anwesome.ui.archcomponentdemo;

import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.widget.Toast;

/**
 * Created by anweshmishra on 24/05/17.
 */

public class CustomLifecyleObserver implements LifecycleObserver{
    private Activity activity;
    public CustomLifecyleObserver(Activity activity) {
        this.activity = activity;
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        Toast.makeText(activity, "Started", Toast.LENGTH_SHORT).show();
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        Toast.makeText(activity, "Stoped", Toast.LENGTH_SHORT).show();
    }
}
