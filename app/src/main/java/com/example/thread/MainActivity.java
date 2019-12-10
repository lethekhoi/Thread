package com.example.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int a, b, c;
    Laco laco;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //thread : luồng phụ -> sinh ta bên ngoài luồng chính và ko ảnh hưởng luồng chính
        //main thread :luồng chính->
        laco = new Laco(0);
        Thread threada = new Thread(new Runnable() { // thread ko dc can thiệp giao diện của Main
            @Override
            public void run() {
                synchronized (laco) {
                    for (int i = 0; i <= 100; ) {
                        if (laco.position == 0) {
                            a = i;
                            Log.d("BBB", "A:" + a);
                            laco.position = 1;
                            laco.notifyAll();
                            i++;
                        } else {
                            try {
                                laco.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
//                count("A");
                }
            }
        });
        Thread threadb = new Thread(new Runnable() { // thread ko dc can thiệp giao diện của Main
            @Override
            public void run() {
//                count("B");
                synchronized (laco) {
                    for (int i = 0; i <= 100; ) {
                        if (laco.position == 1) {
                            b = i;
                            Log.d("BBB", "B:" + b);
                            laco.position = 2;
                            laco.notifyAll();
                            i++;
                        } else {
                            try {
                                laco.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
        Thread threadc = new Thread(new Runnable() { // thread ko dc can thiệp giao diện của Main
            @Override
            public void run() {
//                count("B");
                synchronized (laco) {
                    for (int i = 0; i <= 100; ) {
                        if (laco.position == 2) {
                            c = a + b;
                            Log.d("BBB", "C:" + c);
                            laco.position = 0;
                            laco.notifyAll();
                            i++;
                        } else {
                            try {
                                laco.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
        //synchronized  : đồng bộ
        //dead log: 2 thread  chạy có liên quan đến nhau
        threada.start();
        threadb.start();
        threadc.start();
//        MyThread myThread = new MyThread();
//        myThread.run();
    }

//    private synchronized void count(String key) {
//        for (int i = 0; i <= 10; i++) {
//            Log.d("BBB", key + ":" + i);
//        }
//    }

}
