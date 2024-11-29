package com.example.myapplicationlab2;

import android.os.Bundle;
import android.view.OrientationEventListener;
import android.widget.SeekBar;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private LinearLayout colorPanel;
    private SeekBar redSeekBar, greenSeekBar, blueSeekBar;
    private int red = 0, green = 0, blue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colorPanel = findViewById(R.id.colorPanel);
        redSeekBar = findViewById(R.id.redSeekBar);
        greenSeekBar = findViewById(R.id.greenSeekBar);
        blueSeekBar = findViewById(R.id.blueSeekBar);

        // Лістенери для зміни значень кольору
        redSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                red = progress;
                updateColor();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        greenSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                green = progress;
                updateColor();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        blueSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                blue = progress;
                updateColor();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // Оновлення орієнтації екрану
        final OrientationEventListener orientationEventListener = new OrientationEventListener(this) {
            @Override
            public void onOrientationChanged(int orientation) {
                if (getResources().getConfiguration().orientation == 1) { // портрет
                    // Розташування SeekBar у портретній орієнтації
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            0, 1f);
                    redSeekBar.setLayoutParams(params);
                    greenSeekBar.setLayoutParams(params);
                    blueSeekBar.setLayoutParams(params);
                } else { // ландшафт
                    // Розташування SeekBar у ландшафтній орієнтації
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            0, LinearLayout.LayoutParams.MATCH_PARENT, 1f);
                    redSeekBar.setLayoutParams(params);
                    greenSeekBar.setLayoutParams(params);
                    blueSeekBar.setLayoutParams(params);
                }
            }
        };
        orientationEventListener.enable();
    }

    // Оновлення кольору панелі
    private void updateColor() {
        colorPanel.setBackgroundColor(android.graphics.Color.rgb(red, green, blue));
    }
}