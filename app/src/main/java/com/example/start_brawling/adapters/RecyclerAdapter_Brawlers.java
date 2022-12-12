package com.example.start_brawling.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.start_brawling.classes.Brawlers_Class;
import com.example.start_brawling.R;

import java.util.ArrayList;

public class RecyclerAdapter_Brawlers extends RecyclerView.Adapter<RecyclerAdapter_Brawlers.RecyclerHolder> {
    public ArrayList<Brawlers_Class> listBrawlers;
    Context context;
    private CircularProgressDrawable progressDrawable;
    public RecyclerAdapter_Brawlers(Context context) {
        this.context = context;
        listBrawlers = new ArrayList<Brawlers_Class>();
    }
    public void add(Brawlers_Class brawler){
        listBrawlers.add(brawler);
        this.notifyDataSetChanged();
    }

    //Todo 2.3 Este método se encarga de crear la estructura de componentes de cada celda de la
    // lista a partir del layout creado (en este caso custom_item_list.xml)
    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Todo 2.3.1 El layoutInflater podría verse como un elemento que se encarga de coger la
        // vista de la celda y anidarla en la estructura jerárquica del padre (parent) en este caso
        // responde a la pregunta. "Esta celda ¿En qué elemento gráfico de tipo lista va a
        // mostrarse? Una vez hecho eso se pasa al viewHolder para que enlace los elementos del
        // layaut con los tipos de datos de cada clase para que puedan ser manipulados en tiempo
        // de ejecución
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_list_brawler,parent, false);
        RecyclerHolder recyclerHolder = new RecyclerHolder(view);

        return recyclerHolder;
    }

    //Todo 2.4 Esta vista se encarga de enlazar la información con cada celda. Este método es
    // llamado una vez se ha creado la vista de cada celda de la lista. y lo único que hay que
    // hacer es extraer la información del elemento en la lista y asígnarselo a cada elemento
    // gráfico de la celda.
    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, int position) {
        //Todo 2.1 Configuración del CircularProgressDrawable
        progressDrawable = new CircularProgressDrawable(context);
        progressDrawable.setStrokeWidth(10f);
        progressDrawable.setStyle(CircularProgressDrawable.LARGE);
        progressDrawable.setCenterRadius(30f);
        progressDrawable.start();

        Brawlers_Class brawler = listBrawlers.get(position);
        holder.txtViewDesc.setText(brawler.getDescripcion());
        holder.txtViewTitle.setText(brawler.getName());
        Glide.with(context)
                .load(brawler.getEfoto())
                .placeholder(progressDrawable)
                .error(R.mipmap.ic_launcher)
                .into(holder.imgBrawler);
    }

    @Override
    public int getItemCount() {
        return listBrawlers.size();
    }


    //TODO 2.1 Primero se crea la clase que hereda de ViewHolder. Esta clase tiene como finalidad
    // recrear los elementos de la vista del layout de cada elemento de la lista acorde al modelo
    // de datos creado (en este caso la clase Pelicula)
    public class RecyclerHolder extends RecyclerView.ViewHolder {
        ImageView imgBrawler;
        TextView txtViewTitle;
        TextView  txtViewDesc;

        //Todo 2.1.1 El constructor recibe como parámetro un tipo vista(itemView) que contiene la celda ya creada
        // a partir del layout correspondiente.
        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);

            imgBrawler  = (ImageView) itemView.findViewById(R.id.img_item_eventMap);
            txtViewTitle = (TextView)  itemView.findViewById(R.id.txt_item_name);
            txtViewDesc  = (TextView)  itemView.findViewById(R.id.txt_event_map);


        }
    }
}
