package com.example.start_brawling.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.example.start_brawling.classes.Brawlers_Class;
import com.example.start_brawling.R;

import java.util.ArrayList;

public class RecyclerAdapter_BrawlersStats extends RecyclerView.Adapter<RecyclerAdapter_BrawlersStats.RecyclerHolder> {
    public ArrayList<Brawlers_Class> listBrawlers;
    Context context;
    private CircularProgressDrawable progressDrawable;

    public RecyclerAdapter_BrawlersStats(Context context) {
        this.context = context;
        listBrawlers = new ArrayList<Brawlers_Class>();
    }
    public void add(Brawlers_Class brawler){
        listBrawlers.add(brawler);
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_list_brawlers_stats,parent, false);
        RecyclerAdapter_BrawlersStats.RecyclerHolder recyclerHolder = new RecyclerAdapter_BrawlersStats.RecyclerHolder(view);

        return recyclerHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, int position) {
        progressDrawable = new CircularProgressDrawable(context);
        progressDrawable.setStrokeWidth(10f);
        progressDrawable.setStyle(CircularProgressDrawable.LARGE);
        progressDrawable.setCenterRadius(30f);
        progressDrawable.start();

        Brawlers_Class brawler = listBrawlers.get(position);
        holder.txtViewRate.setText(brawler.getWinRate());
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

    public class RecyclerHolder extends RecyclerView.ViewHolder {
        ImageView imgBrawler;
        TextView txtViewTitle;
        TextView  txtViewRate;

        //Todo 2.1.1 El constructor recibe como parámetro un tipo vista(itemView) que contiene la celda ya creada
        // a partir del layout correspondiente.
        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);

            imgBrawler  = (ImageView) itemView.findViewById(R.id.img_item_eventMap);
            txtViewTitle = (TextView)  itemView.findViewById(R.id.txt_item_name);
            txtViewRate  = (TextView)  itemView.findViewById(R.id.txt_win_rate);


        }
    }
}