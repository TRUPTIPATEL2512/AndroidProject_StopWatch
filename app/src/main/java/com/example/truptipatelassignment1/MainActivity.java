package com.example.truptipatelassignment1;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.example.truptipatelassignment1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    // ViewBinding instance for accessing UI components
    private ActivityMainBinding binding;

    // Handler for updating the stopwatch UI
    private Handler handler = new Handler();

    // Variables to track stopwatch state
    private boolean isRunning = false;  // Indicates if the stopwatch is running
    private long startTime = 0, elapsedTime = 0; // Start time and elapsed time tracking

    // Runnable to update the stopwatch display at 10ms intervals
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            long time = System.currentTimeMillis() - startTime + elapsedTime; // Calculate elapsed time
            int seconds = (int) (time / 1000);
            int minutes = seconds / 60;
            int millis = (int) (time % 1000) / 10;
            seconds = seconds % 60;

            // Update the timer display in HH:MM:SS format
            binding.txtTimer.setText(String.format("%02d:%02d:%02d", minutes, seconds, millis));

            // Schedule the next update after 10 milliseconds
            handler.postDelayed(this, 10);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate the layout using ViewBinding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Start button click event: Starts the stopwatch
        binding.btnStart.setOnClickListener(v -> {
            if (!isRunning) {
                startTime = System.currentTimeMillis(); // Set start time
                handler.post(runnable); // Start updating the UI
                isRunning = true;

                // Update button states
                binding.btnStart.setEnabled(false);  // Disable Start button while running
                binding.btnStop.setEnabled(true);   // Enable Stop button
                binding.btnReset.setEnabled(true);  // Enable Reset button while running
            }
        });

        // Stop button click event: Pauses the stopwatch
        binding.btnStop.setOnClickListener(v -> {
            if (isRunning) {
                handler.removeCallbacks(runnable); // Stop updating the UI
                elapsedTime += System.currentTimeMillis() - startTime; // Save elapsed time
                isRunning = false;

                // Update button states
                binding.btnStart.setEnabled(true); // Enable Start button
                binding.btnStop.setEnabled(false); // Disable Stop button
                binding.btnReset.setEnabled(true); // Enable Reset button
            }
        });

        // Reset button click event: Resets the stopwatch
        binding.btnReset.setOnClickListener(v -> {
            if (!isRunning) {
                elapsedTime = 0; // Reset elapsed time
                binding.txtTimer.setText("00:00:00"); // Reset timer display

                // Reset button states
                binding.btnStart.setEnabled(true);   // Enable Start button
                binding.btnStop.setEnabled(false);  // Disable Stop button
                binding.btnReset.setEnabled(false); // Disable Reset button
            }
        });

        // Initial button states at app launch
        binding.btnStop.setEnabled(false); // Stop button should be disabled initially
        binding.btnReset.setEnabled(false); // Reset button should be disabled initially
    }
}
