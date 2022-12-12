package com.example.start_brawling.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.start_brawling.classes.Brawlers_Class;
import com.example.start_brawling.HttpConnectBrawl;
import com.example.start_brawling.R;
import com.example.start_brawling.adapters.RecyclerAdapter_BrawlersStats;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class DetailEvent_Act extends AppCompatActivity {
    //Declaración de variables
    private TextView txtNombre;
    private TextView txtMapa;
    private TextView txtModo;
    private TextView txtFecIni;
    private TextView txtFecFin;
    private ImageView imgMapa;
    private Context context;
    private CircularProgressDrawable progressDrawable;
    private String idIntent;
    private RecyclerView recyclerView;
    private RecyclerAdapter_BrawlersStats adapter;
    private Brawlers_Class brawler;
    private String brId = "";
    private String brStats = "";
    private boolean encontrado = false ;
    private int posicion = 0;
    private int cursor = 0;
    private String sSub = "";
    private ArrayList<Brawlers_Class> brawlersStats = new ArrayList<Brawlers_Class>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_event);
        context = this;

        adapter = new RecyclerAdapter_BrawlersStats(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        txtNombre = (TextView) findViewById(R.id.txt_item_name);
        txtMapa = (TextView) findViewById(R.id.txt_event_map);
        txtModo = (TextView) findViewById(R.id.txt_event_mode);
        txtFecIni = (TextView) findViewById(R.id.txt_startDate);
        txtFecFin = (TextView) findViewById(R.id.txt_endDate);
        imgMapa = (ImageView) findViewById(R.id.img_item_eventMap);

        Intent i = getIntent();

        idIntent = i.getStringExtra("id");

        new DetailEvent_Act.taskConnections().execute("GET", "/events");
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
                    //obtengo la primera parte del json para saber con cual es con el que estoy trabajando
                        sSub = s.substring(2, 4);
                        Log.d("D", "sSub: " + sSub);
                        //en caso de que sea el del endpoint de brawlers...
                        if(sSub.equals("id")) {
                            JSONObject jsonObject = new JSONObject(s);
                            String brName = "";
                            String brEFoto = "";
                            brName = jsonObject.getString("name");
                            Log.d("D", "nombre: " + brName);
                            brEFoto = jsonObject.getString("imageUrl2");
                            //obtengo el objeto de la primera posicion
                            brawler = brawlersStats.get(cursor);
                            cursor++;
                            //y le establezco un nombre y foto
                            brawler.setName(brName);
                            brawler.setEfoto(brEFoto);
                            //añado al adapter
                            adapter.add(brawler);
                            //notifico
                            adapter.notifyDataSetChanged();

                        }else{

                            JSONObject jsonObject = new JSONObject(s);
                            JSONArray jsonArray = jsonObject.getJSONArray("active");
                            String id = "";
                            String name = "";
                            String map = "";
                            String mode = "";
                            String initDate = "";
                            String endDate = "";
                            String efoto = "";

                            Log.d("D", "objetoid" + s);
                            for (int i = 0; i < jsonArray.length() && !encontrado; i++) {
                                Log.d("D", "objeto" + name);
                                id = jsonArray.getJSONObject(i).getJSONObject("slot").getString("id");
                                //no tengo opcion de hacer peticiones a la api enviandole el id en el edopoint de eventos
                                //por eso recorro el array del json hasta encontrar el que tenga el mismo id que el seleccionado
                                if (id.equals(idIntent)) {
                                    encontrado = true;
                                    //guardo en que posicion se encontraba para despues obtener las estadisticas de los brawlers de ese evento
                                    posicion = i;
                                    //obtengo e inserto los datos
                                    name = jsonArray.getJSONObject(i).getJSONObject("slot").getString("name");
                                    txtNombre.setText(name);
                                    map = jsonArray.getJSONObject(i).getJSONObject("map").getString("name");
                                    txtMapa.setText(map);
                                    mode = jsonArray.getJSONObject(i).getJSONObject("map").getJSONObject("gameMode").getString("name");
                                    txtModo.setText(mode);
                                    initDate = jsonArray.getJSONObject(i).getString("startTime");

                                    initDate = initDate.substring(0, 10);

                                    txtFecIni.setText(initDate);
                                    endDate = jsonArray.getJSONObject(i).getString("endTime");


                                    endDate = endDate.substring(0, 10);
                                    txtFecFin.setText(endDate);
                                    efoto = jsonArray.getJSONObject(i).getJSONObject("map").getString("imageUrl");
                                    progressDrawable = new CircularProgressDrawable(context);
                                    progressDrawable.setStrokeWidth(10f);
                                    progressDrawable.setStyle(CircularProgressDrawable.LARGE);
                                    progressDrawable.setCenterRadius(30f);
                                    progressDrawable.start();
                                    Glide.with(context)
                                            .load(efoto)
                                            .placeholder(progressDrawable)
                                            .error(R.mipmap.ic_launcher)
                                            .into(imgMapa);
                                }
                            }
                            //Obtengo los brawlers con su información sobre este evento
                            JSONArray statsJsonArray = jsonArray.getJSONObject(posicion).getJSONObject("map").getJSONArray("stats");
                            for (int j = 0; j < statsJsonArray.length(); j++) {
                                //creo un objeto Brawler_class con la informacion relevante que tengo actualmente sobre el
                                brId = statsJsonArray.getJSONObject(j).getString("brawler");
                                brStats = statsJsonArray.getJSONObject(j).getString("winRate");
                                brawler = new Brawlers_Class(brId, brStats);
                                Log.d("N", "detailEvennnt"+brStats);
                                Log.d("N", "detailEvennnt"+brId);
                                //vuelvo a ejecutar el taskconnections con el endpoint de brawlers y el id del cual me interesa
                                new DetailEvent_Act.taskConnections().execute("GET", "/brawlers/" + brId);
                                //lo guardo en el arrayList para trabajar con ellos mas tarde
                                brawlersStats.add(brawler);
                            }
                        }
                }else{
                    Toast.makeText(DetailEvent_Act.this, "Problema al cargar los datos", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}