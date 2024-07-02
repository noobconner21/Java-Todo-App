package com.sslablk.mytodo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DISPLAY_LENGTH = 1000; // Duration of splash screen display in milliseconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Setup ImageView
        ImageView imageView = findViewById(R.id.image_view);
        // Set the image resource (replace 'logo' with your image resource name)
        imageView.setImageResource(R.drawable.logo);

        // Display a toast message
        showToast("Dev : ShayC");

        // Delay for a few seconds before starting the main activity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start the main activity
                Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(mainIntent);
                finish(); // Finish this activity so it doesn't remain in the back stack
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    // Method to show a toast message
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
