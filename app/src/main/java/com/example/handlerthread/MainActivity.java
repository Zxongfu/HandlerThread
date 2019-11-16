package com.example.handlerthread;

import android.os.Bundle;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.handlerthread.ExampleHandlerThread.E;

public class MainActivity extends AppCompatActivity  {
    private static final String TAG = "MainActivity";
    private ExampleHandlerThread exampleHandlerThread = new ExampleHandlerThread();
    //private HandlerThread handlerThread = new HandlerThread("HandlerThread");
    //private Handler handler;

    private Example1 example1 = new Example1();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //handlerThread.start();
        exampleHandlerThread.start();
//         handler = new Handler(handlerThread.getLooper()){
//             @Override
//             public void handleMessage(@NonNull Message msg) {
//                 switch (msg.what) {
//                     case 1:
//                         Log.d(TAG, "handleMessage: "+msg);
//                 }
//
//             }
//         };
    }

    public void work(View view) {
//        handler.post(new Example1());
//        handler.post(new Example1());
//        handler.postAtFrontOfQueue(new Example2());

        Message message = Message.obtain(exampleHandlerThread.getHandler());
        message.what = E;
        message.arg1 = 23;
        message.obj = "obj 1";

        message.sendToTarget();
//      handler.sendMessage(message);
//      exampleHandlerThread.getHandler().sendMessage(message);

//      exampleHandlerThread.getHandler().sendEmptyMessage(E);

//      exampleHandlerThread.getHandler().post(new Example1());
        exampleHandlerThread.getHandler().post(example1);
//      exampleHandlerThread.getHandler().postAtFrontOfQueue(new Example2());

    }

    public void remove(View view) {
        exampleHandlerThread.getHandler().removeCallbacks(example1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //handlerThread.quit();
        exampleHandlerThread.quit();
    }

    static class Example1 implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i <= 4; i++) {
                Log.d(TAG, "run1: " + i);
                SystemClock.sleep(1000);
            }
        }
    }

    static class Example2 implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i <= 4; i++) {
                Log.d(TAG, "run2: " + i);
                SystemClock.sleep(1000);
            }
        }
    }
}
