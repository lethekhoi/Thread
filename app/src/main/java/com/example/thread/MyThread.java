package com.example.thread;

import android.util.Log;

public class MyThread implements Runnable {
    @Override
    public void run() {
        Log.d("BBB", "Thread start");
    }
}
