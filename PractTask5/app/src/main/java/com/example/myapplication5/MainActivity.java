package com.example.myapplication5;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor selectedSensor;
    private TextView xValue, yValue, zValue;
    private Spinner sensorSelector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ініціалізація UI компонентів
        xValue = findViewById(R.id.xValue);
        yValue = findViewById(R.id.yValue);
        zValue = findViewById(R.id.zValue);
        sensorSelector = findViewById(R.id.sensorSelector);

        // Ініціалізація SensorManager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        // Заповнення Spinner для вибору сенсора
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.sensors_array,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sensorSelector.setAdapter(adapter);

        sensorSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = parent.getItemAtPosition(position).toString();
                switch (selected) {
                    case "Акселерометр":
                        selectedSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
                        break;
                    case "Гіроскоп":
                        selectedSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
                        break;
                    case "Магнітометр":
                        selectedSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
                        break;
                }
                registerSensor();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Нічого не робити
            }
        });
    }

    private void registerSensor() {
        sensorManager.unregisterListener(this); // Відключаємо попередній сенсор
        if (selectedSensor != null) {
            sensorManager.registerListener(this, selectedSensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            xValue.setText("Сенсор недоступний");
            yValue.setText("");
            zValue.setText("");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerSensor();
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        xValue.setText("X: " + event.values[0]);
        yValue.setText("Y: " + event.values[1]);
        zValue.setText("Z: " + event.values[2]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Можна залишити порожнім
    }
}