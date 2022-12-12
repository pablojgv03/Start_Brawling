package com.example.start_brawling.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.start_brawling.classes.Brawlers_Class;
import com.example.start_brawling.HttpConnectBrawl;
import com.example.start_brawling.R;
import com.example.start_brawling.adapters.RecyclerAdapter_Brawlers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Brawlers_Act extends AppCompatActivity {
    //Declaración de variables
    private RecyclerView recyclerView;
    private RecyclerAdapter_Brawlers adapter;

    private androidx.appcompat.view.ActionMode menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brawlers);
        adapter = new RecyclerAdapter_Brawlers(this);

        new taskConnections().execute("GET", "/brawlers");

        recyclerView = (RecyclerView) findViewById(R.id.recView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        //Añado los elementos creados anteriormente a la vista
        //padre (RecyclerView) con sus respectivos métodos.
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }


    private class taskConnections extends AsyncTask<String,Void,String> {
        //aqui detecto que debemos ejecutar
        @Override
        protected String doInBackground(String... strings) {
            String result = null;
            switch (strings[0]){
                case "GET":
                    result = HttpConnectBrawl.getRequest(strings[1]);
                    break;
                case "POST":
                    result = Integer.toString(HttpConnectBrawl.postRequest(strings[1],strings[2]));
                    break;
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                //si el json no está vacio
                if(s != null){
                    Log.d("D","DATOS: "+s);
                    //declaracion e inicializacion de variables
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray jsonArray = jsonObject.getJSONArray("list");
                    String id = "";
                    String name = "";
                    String efoto = "";
                    String descripcion = "";
                    //por cada registro obtengo los datos que me interesan
                    for(int i=0; i<jsonArray.length(); i++){
                        id = jsonArray.getJSONObject(i).getString("id");
                        name = jsonArray.getJSONObject(i).getString("name");
                        efoto = jsonArray.getJSONObject(i).getString("imageUrl2");
                        descripcion = jsonArray.getJSONObject(i).getString("description");
                        Brawlers_Class brawler = new Brawlers_Class(id, name, efoto, descripcion);
                        //añado el objeto al adapatador
                        adapter.add(brawler);
                    }
                    //aviso al adapter que la informacion ha cambiado
                    adapter.notifyDataSetChanged();
                    Log.d("D","Array: "+adapter.listBrawlers.toString());
                }else{
                    //ha ocurrido un problema; Informo
                    Toast.makeText(Brawlers_Act.this, "Problema al cargar los datos", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}