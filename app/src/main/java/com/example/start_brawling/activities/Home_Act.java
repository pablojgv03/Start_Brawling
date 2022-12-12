package com.example.start_brawling.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.start_brawling.R;

public class Home_Act extends AppCompatActivity implements View.OnClickListener{
    //Declaración de variables
    Button btnbrawler, btnMap, btnEvent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnbrawler = (Button) findViewById(R.id.brawlersButton_home);
        btnMap = (Button) findViewById(R.id.mapButton_home);
        btnEvent = (Button) findViewById(R.id.eventsButton_home);
        btnbrawler.setOnClickListener(this);
        btnMap.setOnClickListener(this);
        btnEvent.setOnClickListener(this);

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
        }
    }
}