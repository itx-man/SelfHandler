package com.vivi.selfdefinedhandler;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    class MyThrad extends Thread {
        public Handler handler;

        @Override
        public void run() {
            Looper.prepare();
            handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    System.out.println("子线程---currentThread---->" + Thread.currentThread());
                }
            };
            Looper.loop();
        }
    }

    private MyThrad myThrad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        textView.setText("hello handler");
        System.out.println("MainThread---currentThread------>" + Thread.currentThread());
        myThrad = new MyThrad();
        myThrad.start();
        myThrad.handler.sendEmptyMessage(1);
    }
}
