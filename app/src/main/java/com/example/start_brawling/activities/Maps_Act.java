package com.example.start_brawling.activities;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.start_brawling.HttpConnectBrawl;
import com.example.start_brawling.classes.Maps_Class;
import com.example.start_brawling.R;
import com.example.start_brawling.adapters.RecyclerAdapter_Maps;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Maps_Act extends AppCompatActivity {
    //Declaración de variables
    private RecyclerView recyclerView;
    private RecyclerAdapter_Maps adapter;
    private ConstraintLayout layoutmap;

    private androidx.appcompat.view.ActionMode menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        adapter = new RecyclerAdapter_Maps(this);

        new Maps_Act.taskConnections().execute("GET", "/maps");
        layoutmap = (ConstraintLayout) findViewById(R.id.layoutmap);

        recyclerView = (RecyclerView) findViewById(R.id.recView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        loadPreferences();
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
                    //obtengo la informacion necesaria del json
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray jsonArray = jsonObject.getJSONArray("list");
                    String id = "";
                    String name = "";
                    String efoto = "";
                    String modo = "";
                    for(int i=0; i<jsonArray.length(); i++){
                        id = jsonArray.getJSONObject(i).getString("id");
                        name = jsonArray.getJSONObject(i).getString("name");
                        efoto = jsonArray.getJSONObject(i).getString("imageUrl");
                        modo = jsonArray.getJSONObject(i).getJSONObject("gameMode").getString("name");
                        Maps_Class maps = new Maps_Class(id, name, efoto, modo);
                        adapter.add(maps);
                    }
                    //notifico
                    adapter.notifyDataSetChanged();
                    Log.d("D","Array: "+adapter.listMaps.toString());
                }else{
                    Toast.makeText(Maps_Act.this, "Problema al cargar los datos", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    public void loadPreferences(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Maps_Act.this);
        boolean modoOn = sharedPreferences.getBoolean("switch",false);
        if(modoOn == true){

            layoutmap.setBackgroundColor(Color.rgb(250,187,174));
        }else{
            layoutmap.setBackgroundColor(Color.WHITE);
        }
    }
}