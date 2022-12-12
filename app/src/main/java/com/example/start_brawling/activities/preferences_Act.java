package com.example.start_brawling.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.start_brawling.R;

public class preferences_Act extends AppCompatActivity{
    //Declaración de variables

    Button editButton, deleteButton, showButton, exitButton;
    TextView nombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_management);
    }
}