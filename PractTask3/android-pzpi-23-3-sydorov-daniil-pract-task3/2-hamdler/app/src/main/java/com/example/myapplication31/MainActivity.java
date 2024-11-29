package com.example.myapplication31;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.HandlerThread;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startHandlerButton = findViewById(R.id.startHandlerButton);
        TextView handlerMessageTextView = findViewById(R.id.handlerMessageTextView);

        startHandlerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        handlerMessageTextView.setText("Handler executed after delay");
                    }
                }, 2000);
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        handlerMessageTextView.setText("Updated from background thread");
                    }
                });
            }
        }).start();


        Handler messageHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                handlerMessageTextView.setText("Message received: " + msg.what);
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Message msg = messageHandler.obtainMessage();
                msg.what = 1;
                messageHandler.sendMessage(msg);
            }
        }).start();

        HandlerThread handlerThread = new HandlerThread("BackgroundThread");
        handlerThread.start();
        Handler backgroundHandler = new Handler(handlerThread.getLooper());

        backgroundHandler.post(new Runnable() {
            @Override
            public void run() {
            }
        });
    }
}