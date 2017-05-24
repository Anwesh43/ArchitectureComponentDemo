package com.anwesome.ui.archcomponentdemo;

import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity implements LifecycleRegistryOwner {
    private UserData userData;
    private TextView myTextView;
    private Observer<User> userUIObserver;
    private CustomLifecyleObserver customLifecyleObserver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customLifecyleObserver = new CustomLifecyleObserver(this);
        getLifecycle().addObserver(customLifecyleObserver);
        myTextView = (TextView)findViewById(R.id.user_desc);
        userUIObserver = new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                myTextView.setText(user.toString());
            }
        };
        if(userData == null) {
            userData = new UserData(this);
            userData.observeForever(userUIObserver);
        }
    }
    public void onStop() {
        super.onStop();
        if(userData!=null && userUIObserver!=null) {
            userData.removeObserver(userUIObserver);
        }

    }
    public LifecycleRegistry getLifecycle() {
        return new LifecycleRegistry(this);
    }
}
