package com.example.facebook.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.facebook.R;
import com.example.facebook.databinding.ActivityCalculatorBinding;

public class Calculator extends AppCompatActivity {
    private ActivityCalculatorBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCalculatorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
       String email = intent.getStringExtra("EMAIL");

       binding.tvHello.setText("Welcome,\n"+email);



    }
}