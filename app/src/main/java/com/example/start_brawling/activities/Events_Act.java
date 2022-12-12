package com.example.start_brawling.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.start_brawling.classes.Events_Class;
import com.example.start_brawling.HttpConnectBrawl;
import com.example.start_brawling.R;
import com.example.start_brawling.adapters.RecyclerAdapter_Events;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Events_Act extends AppCompatActivity {
    //Declaración de variables
    private RecyclerView recyclerView;
    private RecyclerAdapter_Events adapter;
    int item;
    private androidx.appcompat.view.ActionMode menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        Log.d("c","create");

        adapter = new RecyclerAdapter_Events(this);

        new Events_Act.taskConnections().execute("GET", "/events");

        recyclerView = (RecyclerView) findViewById(R.id.recView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        //Todo 3.2 Por último solo debemos añadir los elementos creados anteriormente a la vista
        //Todo padre (RecyclerView) con sus respectivos métodos.
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        adapter.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                item = recyclerView.getChildAdapterPosition(view);

                Intent i = new Intent(Events_Act.this, DetailEvent_Act.class);
                i.putExtra("id", adapter.listEvents.get(item).getId());

                startActivity(i);
                view.setSelected(true);
            }
        });

    }


    private class taskConnections extends AsyncTask<String,Void,String> {

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
                if(s != null){
                    Log.d("D","DATOS: "+s);

                    //Todo 3. La respuesta que nos devuelve es un texto en formato JSON. Para ello,
                    // en este caso, haremos uso de las clases que nos proporciona Android. Antes
                    // que nada, se deberá consultar la documentación para conocer el formato de
                    // la respuesta del servidor, y así saber cómo deserializar el mensaje.
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray jsonArray = jsonObject.getJSONArray("active");
                    String id = "";
                    String name = "";
                    String efoto = "";
                    String modo = "";
                    //aqui separo los eventos en activos e inactivos
                    String disponibility = "Active";
                    for(int i=0; i<jsonArray.length(); i++){
                        id = jsonArray.getJSONObject(i).getJSONObject("slot").getString("id");
                        name = jsonArray.getJSONObject(i).getJSONObject("slot").getString("name");
                        efoto = jsonArray.getJSONObject(i).getJSONObject("map").getString("imageUrl");
                        modo = jsonArray.getJSONObject(i).getJSONObject("map").getJSONObject("gameMode").getString("name");
                        Events_Class event = new Events_Class(id, name, efoto, modo, disponibility);
                        adapter.add(event);
                    }
                    jsonArray = jsonObject.getJSONArray("upcoming");
                    disponibility = "Inactive";
                    for(int i=0; i<jsonArray.length(); i++){
                        id = jsonArray.getJSONObject(i).getJSONObject("slot").getString("id");
                        name = jsonArray.getJSONObject(i).getJSONObject("slot").getString("name");
                        efoto = jsonArray.getJSONObject(i).getJSONObject("map").getString("imageUrl");
                        modo = jsonArray.getJSONObject(i).getJSONObject("map").getJSONObject("gameMode").getString("name");
                        Events_Class event = new Events_Class(id, name, efoto, modo, disponibility);
                        adapter.add(event);
                    }
                    //notifico
                    adapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(Events_Act.this, "Problema al cargar los datos", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}