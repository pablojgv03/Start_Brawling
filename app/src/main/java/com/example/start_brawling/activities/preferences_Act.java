package com.example.start_brawling.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.example.start_brawling.R;
import com.example.start_brawling.fragments.SettingsFragment;

public class preferences_Act extends AppCompatActivity {
    //Declaración de variables
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_preferences, new SettingsFragment())   //Todo 2.1 Es en esta línea donde se reemplaza el contenedor por una instancia de la clase SettingFragment
                .commit();                                                  //Todo --> cuya clase construye las preferencias como se ha comentado en el punto 1.

        //Todo 3. Esto es opcional, pero se puede activar el icono de "Volver"(flecha atrás) para
        // ello debemos de acceder al action bar creado por Android por defecto.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true); //Todo 3.1 si existe (no es nulo) mostramos el botón hacia atrás.
        }


    }

    //Todo 3.2 Para que tenga efecto al pulsar debemos implementar qué debe hacer cuando el usuario
    // lo pulse.
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:  //Todo 3.3. Este sería el acceso al recurso del botón volver
                onBackPressed();     //Todo 3.4 Este sería el método que tiene Android para volver hacia la ventana anterior.
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}