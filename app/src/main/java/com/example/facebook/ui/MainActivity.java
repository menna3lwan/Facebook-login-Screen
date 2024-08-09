package com.example.facebook.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.facebook.R;
import com.example.facebook.databinding.ActivityMainBinding;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button loginButton;
    private Button createAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = findViewById(R.id.btn_login);
        createAccountButton = findViewById(R.id.btn_create_account);

        // Create a common OnClickListener
        View.OnClickListener navigateToHome = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to the same page or perform the same action
                Intent intent = new Intent(MainActivity.this, Calculator.class); // Assuming HomeActivity is the destination
                startActivity(intent);
            }
        };

        // Set the same listener for both buttons
        loginButton.setOnClickListener(navigateToHome);
        createAccountButton.setOnClickListener(navigateToHome);
    }
}
