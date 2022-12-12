package com.example.start_brawling.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.preference.PreferenceManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.start_brawling.R;

public class Home_Act extends AppCompatActivity implements View.OnClickListener{
    //Declaración de variables
    Button btnbrawler, btnMap, btnEvent;
    ImageButton btnPref;
    private ConstraintLayout layoutinicio;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnbrawler = (Button) findViewById(R.id.brawlersButton_home);
        btnMap = (Button) findViewById(R.id.mapButton_home);
        btnEvent = (Button) findViewById(R.id.eventsButton_home);
        btnPref = (ImageButton) findViewById(R.id.prefbtn_home);
        layoutinicio = (ConstraintLayout) findViewById(R.id.layoutHome);
        btnbrawler.setOnClickListener(this);
        btnMap.setOnClickListener(this);
        btnEvent.setOnClickListener(this);
        btnPref.setOnClickListener(this);
        loadPreferences();
    }
//ejecuto un intent diferente dependiendo del boton presionado
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.mapButton_home:
                Intent i=new Intent(Home_Act.this, Maps_Act.class );
                startActivity(i);
                break;
            case R.id.brawlersButton_home:
                Intent i2=new Intent(Home_Act.this, Brawlers_Act.class );
                startActivity(i2);
                break;
            case R.id.eventsButton_home:
                Intent i3=new Intent(Home_Act.this, Events_Act.class );
                startActivity(i3);
                break;
            case R.id.prefbtn_home:
                Intent i4=new Intent(Home_Act.this, preferences_Act.class );
                startActivity(i4);
                break;
        }
    }
    public void loadPreferences(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Home_Act.this);
        boolean modoOn = sharedPreferences.getBoolean("switch",false);
        if(modoOn == true){

            layoutinicio.setBackgroundColor(Color.rgb(250,187,174));
        }else{
            layoutinicio.setBackgroundColor(Color.WHITE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadPreferences();
    }
}