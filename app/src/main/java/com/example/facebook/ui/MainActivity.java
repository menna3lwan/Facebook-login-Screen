package com.example.facebook.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.facebook.R;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private Button loginButton;
    private Button createAccountButton;
    private EditText emailEditText;
    private EditText passwordEditText;

    private boolean isPhoneInput;
    private int failedAttempts = 0;
    private final int MAX_FAILED_ATTEMPTS = 100;

    // Example list of commonly used or compromised passwords
    private Set<String> compromisedPasswords = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = findViewById(R.id.btn_login);
        createAccountButton = findViewById(R.id.btn_create_account);
        emailEditText = findViewById(R.id.ed_email);
        passwordEditText = findViewById(R.id.ed_password);

        // Populate the compromised passwords list (this would typically be loaded from a database or external file)
        populateCompromisedPasswords();

        emailEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChoiceDialog();
            }
        });

        View.OnClickListener navigateToHome = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isInputValid() && isPasswordValid()) {
                    Intent intent = new Intent(MainActivity.this, Calculator.class);
                    startActivity(intent);
                } else {
                    if (++failedAttempts >= MAX_FAILED_ATTEMPTS) {
                        loginButton.setEnabled(false);
                        Toast.makeText(MainActivity.this, "Too many failed attempts. Please try again later.", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Input or password is not valid", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        };

        loginButton.setOnClickListener(navigateToHome);
        createAccountButton.setOnClickListener(navigateToHome);
    }

    private void showChoiceDialog() {
        final String[] options = {"Phone", "Email"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose Input Type");

        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (options[which].equals("Phone")) {
                    isPhoneInput = true;
                    emailEditText.setHint("Mobile number");
                    emailEditText.setInputType(InputType.TYPE_CLASS_PHONE);
                } else if (options[which].equals("Email")) {
                    isPhoneInput = false;
                    emailEditText.setHint("Email address");
                    emailEditText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                }
                emailEditText.setFocusableInTouchMode(true);
                emailEditText.requestFocus();
            }
        });

        builder.show();
    }

    private boolean isInputValid() {
        String input = emailEditText.getText().toString().trim();

        if (isPhoneInput) {
            if (input.startsWith("011") || input.startsWith("012") ||
                    input.startsWith("015") || input.startsWith("010")) {
                return true;
            } else {
                emailEditText.setError("Phone number must start with 011, 012, 015, or 010");
                return false;
            }
        } else {
            if (input.endsWith("@gmail.com")) {
                return true;
            } else {
                emailEditText.setError("Email must end with @gmail.com");
                return false;
            }
        }
    }

    private boolean isPasswordValid() {
        String password = passwordEditText.getText().toString();

        // Check length
        if (password.length() < 8 || password.length() > 64) {
            passwordEditText.setError("Password must be between 8 and 64 characters");
            return false;
        }

        // Check against compromised passwords
        if (compromisedPasswords.contains(password)) {
            passwordEditText.setError("Password is too common or compromised");
            return false;
        }

        // Check for repetitive or sequential characters
        if (isRepetitiveOrSequential(password)) {
            passwordEditText.setError("Password cannot contain repetitive or sequential characters");
            return false;
        }

        return true;
    }

    private boolean isRepetitiveOrSequential(String password) {
        for (int i = 0; i < password.length() - 1; i++) {
            char currentChar = password.charAt(i);
            char nextChar = password.charAt(i + 1);

            if (currentChar == nextChar || nextChar == currentChar + 1) {
                return true;
            }
        }
        return false;
    }

    private void populateCompromisedPasswords() {
        // Example of adding compromised passwords (this list should be much larger in practice)
        compromisedPasswords.add("password");
        compromisedPasswords.add("12345678");
        compromisedPasswords.add("qwerty");
        compromisedPasswords.add("abc123");
        // Add more compromised passwords as necessary
    }
}
