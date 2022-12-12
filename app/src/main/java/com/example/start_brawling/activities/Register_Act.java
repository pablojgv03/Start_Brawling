package com.example.start_brawling.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.start_brawling.R;
import com.example.start_brawling.classes.User_Class;
import com.example.start_brawling.classes.userDB_Class;
import com.soepic.sefancytoast.FancyToast;


public class Register_Act extends AppCompatActivity implements View.OnClickListener{
    //Declaración de variables
    EditText us, pas, nom, ap;
    Button reg,can;
    userDB_Class bd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        us = (EditText) findViewById(R.id.userNameET_register);
        pas = (EditText) findViewById(R.id.passwordET_register);
        nom = (EditText) findViewById(R.id.nameET_register);
        ap = (EditText) findViewById(R.id.surnameET_register);
        reg = (Button) findViewById(R.id.regbutton_register);
        can = (Button) findViewById(R.id.cancelbutton_register);

        reg.setOnClickListener(this);
        can.setOnClickListener(this);

        bd = new userDB_Class(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.regbutton_register:
                User_Class u= new User_Class();
                u.setUserName(us.getText().toString());
                u.setPassword(pas.getText().toString());
                u.setName(nom.getText().toString());
                u.setSurname(ap.getText().toString());
                //compruebon que se han introducido todos los datos
                if(!u.isNull()){
                    showToast("Error. Campos vacios", false);
                }else{
                    if (bd.insertUser(u)){
                        showToast("Usuario registrado", true);
                        Intent i = new Intent(Register_Act.this, log_reg_Act.class );
                        startActivity(i);
                        finish();
                    }else{
                        showToast("Ya existe un usuario con ese nombre",false);

                    }
                }
                break;
            case R.id.cancelbutton_register:
                Intent i2 =new Intent(Register_Act.this, log_reg_Act.class );
                startActivity(i2);
                finish();
                break;
        }
    }
    private void showToast(String msg, boolean error){
        new FancyToast()
                .with(this)// contexto
                .setGravity(Gravity.BOTTOM,0,100)// gravity of FancyToast
                .setText(msg)// set text for FancyToast
                .cornerRadius(16)// corner radius of FancyToast view
                .hideIcon(error)// show/hide icon
                .show();// finally show the FancyToast
    }

}