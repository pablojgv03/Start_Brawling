package com.example.start_brawling.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import com.example.start_brawling.R;
import com.example.start_brawling.classes.User_Class;
import com.example.start_brawling.classes.userDB_Class;
import com.soepic.sefancytoast.FancyToast;

public class log_reg_Act extends AppCompatActivity implements View.OnClickListener{
    //Declaración de variables
    EditText user, pass;
    Button btnLog, btnReg;
    ImageButton btnGoogle, btnFaceb;
    userDB_Class db;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = (EditText) findViewById(R.id.userNameET);
        pass = (EditText) findViewById(R.id.passwordET);
        btnLog = (Button) findViewById(R.id.logButton);
        btnReg = (Button) findViewById(R.id.regButton);
        btnGoogle = (ImageButton) findViewById(R.id.imgBtnGoogle);
        btnFaceb = (ImageButton) findViewById(R.id.imgBtnfceb);

        btnLog.setOnClickListener(this);
        btnReg.setOnClickListener(this);
        btnGoogle.setOnClickListener(this);
        btnFaceb.setOnClickListener(this);

        db = new userDB_Class(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.logButton:
                //obtengo el nombre y contraseña
                String u=user.getText().toString();
                String p=pass.getText().toString();
                //en caso de que esten vacíos informo del error
                if(u.equals("") || p.equals("")){
                    showToast("Error. Campos vacios", false);
                }else{
                    //en caso de que tengan informacion la compruebo
                    if(db.login(u,p)==1){
                        User_Class ux = db.getUserByName(u,p);
                        showToast("Bienvenido", true);
                        Intent i=new Intent(log_reg_Act.this, Home_Act.class );
                        i.putExtra("Id", ux.getId());
                        startActivity(i);
                    }else{
                        //no son correctos
                        showToast("Usuario y/o Contraseña incorrecto(s)", false);

                    }
                }
                break;
            case R.id.regButton:
                Intent i2=new Intent(log_reg_Act.this, Register_Act.class );
                startActivity(i2);
                break;
            case R.id.imgBtnGoogle:
                Uri urig = Uri.parse("https://www.google.com/search?gs_ssp=eJzj4tVP1zc0TIk3MrYwi7c0YPTiTipKLM9RKC5JLCoGAG0mCFg&q=brawl+stars&oq=brawlS&aqs=chrome.1.69i57j46i10i131i433i512j46i10i512j0i10i512j0i512j0i10i512j69i60l2.8170j0j7&sourceid=chrome&ie=UTF-8");
                Intent i3 = new Intent(Intent.ACTION_VIEW, urig);
                startActivity(i3);
                break;
            case R.id.imgBtnfceb:
                Uri urif = Uri.parse("https://es-es.facebook.com/brawlstarsspanish/");
                Intent i4 = new Intent(Intent.ACTION_VIEW, urif);
                startActivity(i4);
                break;
        }
    }
    //Libreria externa
    private void showToast(String msg, boolean error) {
        new FancyToast()
                .with(this)// contexto
                .setGravity(Gravity.BOTTOM, 0, 100)// gravity of FancyToast
                .setText(msg)// set text for FancyToast
                .cornerRadius(16)// corner radius of FancyToast view
                .hideIcon(error)// show/hide icon
                .show();// finally show the FancyToast
    }
}