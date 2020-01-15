package com.example.myapplicationlearn;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button buttonStart;
    private Button buttonPause;
    private Button buttonStop;
    private TextView textView;
    private int seconds;
    private boolean isRunning = false;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonStart = findViewById(R.id.buttonStart);
        buttonPause = findViewById(R.id.buttonPause);
        buttonStop = findViewById(R.id.buttonStop);
        textView = findViewById(R.id.textView);

        timer();

        View.OnClickListener start = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRunning = true;
            }
        };

        View.OnClickListener pause = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRunning = false;
            }
        };

        View.OnClickListener stop = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seconds = 0;
                isRunning = false;
            }
        };

        buttonStart.setOnClickListener(start);
        buttonPause.setOnClickListener(pause);
        buttonStop.setOnClickListener(stop);

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("secs",seconds);
        outState.putBoolean("isRunning", isRunning);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        seconds = savedInstanceState.getInt("secs");
        isRunning = savedInstanceState.getBoolean("isRunning");
    }

    private void timer() {
        final Handler h = new Handler();
        h.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / (3600);
                int min = (seconds % 3600) / 60;
                int sec = seconds % 60;
                String ans = String.format("%d:%02d:%02d", hours, min, sec);
                textView.setText(ans);
                if (isRunning) {
                    seconds++;
                }
                Log.d(TAG, "run: Running again");
                h.postDelayed(this, 1000);
            }
        });
    }

}

