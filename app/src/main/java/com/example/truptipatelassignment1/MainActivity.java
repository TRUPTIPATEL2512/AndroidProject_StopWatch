package com.example.truptipatelassignment1;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView timerText;
    private Button btnStart, btnStop, btnReset;
    private Handler handler = new Handler();
    private boolean isRunning = false;
    private long startTime = 0, elapsedTime = 0;

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            long time = System.currentTimeMillis() - startTime + elapsedTime;
            int seconds = (int) (time / 1000);
            int minutes = seconds / 60;
            int millis = (int) (time % 1000) / 10;
            seconds = seconds % 60;
            timerText.setText(String.format("%02d:%02d:%02d", minutes, seconds, millis));
            handler.postDelayed(this, 10); // Re-run this every 10ms
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Remove the title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);  // This line hides the title bar
        getSupportActionBar().hide();  // Hide the ActionBar (if using it)

        setContentView(R.layout.activity_main);

        // Initializing UI components
        timerText = findViewById(R.id.txtTimer);
        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);
        btnReset = findViewById(R.id.btnReset);

        // Start button click event
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isRunning) {
                    startTime = System.currentTimeMillis();
                    handler.post(runnable); // Start the timer update
                    isRunning = true;
                    btnStart.setEnabled(false); // Disable Start button while running
                    btnStop.setEnabled(true);  // Enable Stop button
                }
            }
        });

        // Stop button click event
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRunning) {
                    handler.removeCallbacks(runnable); // Stop updating the timer
                    elapsedTime += System.currentTimeMillis() - startTime; // Save elapsed time
                    isRunning = false; // Set state to stopped
                    btnStart.setEnabled(true); // Enable Start button
                    btnStop.setEnabled(false); // Disable Stop button
                }
            }
        });

        // Reset button click event
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isRunning) { // Can only reset if the stopwatch is stopped
                    elapsedTime = 0; // Reset the elapsed time
                    timerText.setText("00:00:00"); // Reset the display
                    btnStart.setEnabled(true); // Enable Start button
                    btnStop.setEnabled(false); // Disable Stop button
                }
            }
        });

        // Initial button states
        btnStop.setEnabled(false); // Stop should be disabled initially
    }
}
