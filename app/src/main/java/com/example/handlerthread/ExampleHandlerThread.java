package com.example.handlerthread;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.Process;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.NonNull;

public class ExampleHandlerThread extends HandlerThread {
    public static final String TAG = "ExampleHandlerThread";
    public static final int E = 1;
    private Handler handler;


    public ExampleHandlerThread() {
        super(TAG, Process.THREAD_PRIORITY_BACKGROUND);
    }

    @SuppressLint("HandlerLeak")
    @Override
    protected void onLooperPrepared() {
        handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                switch (msg.what) {
                    case E:
                        Log.d(TAG, "arg: " + msg.arg1 + "obj :" + msg.obj);
                        for (int i = 0; i <= 4; i++) {
                            Log.d(TAG, "handleMessage: " + i);
                            SystemClock.sleep(1000);
                        }
                        break;
                }

            }
        };

    }

    public Handler getHandler() {
        return handler;
    }
}
