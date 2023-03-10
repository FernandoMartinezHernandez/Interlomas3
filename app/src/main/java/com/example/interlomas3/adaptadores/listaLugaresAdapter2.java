package com.example.interlomas3.adaptadores;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.interlomas3.R;
import com.example.interlomas3.entidades.contactos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class listaLugaresAdapter2 extends RecyclerView.Adapter<listaLugaresAdapter2.LugaresViewHolder> {

    ArrayList<contactos> listaLugares;
    ArrayList<contactos> listaOriginal;


    public listaLugaresAdapter2(ArrayList<contactos> listaLugares){
        this.listaLugares = listaLugares;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaLugares);

    }

    @NonNull
    @Override
    public LugaresViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_lugar, null, false);
        return new LugaresViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LugaresViewHolder holder, int position) {
        holder.viewLugar.setText(listaLugares.get(position).getLugar());
        holder.viewZona.setText(listaLugares.get(position).getZona());
        holder.viewPrecio.setText(listaLugares.get(position).getPrecio());
        if((position % 2) == 0){
            holder.viewLugar.setBackgroundColor(Color.rgb(126, 191, 217));
            holder.viewPrecio.setBackgroundColor(Color.rgb(126, 191, 217));


        } else{
            holder.viewLugar.setBackgroundColor(Color.rgb(206, 216, 219));
            holder.viewPrecio.setBackgroundColor(Color.rgb(206, 216, 219));

        }
    }

    public void filtrado(String txtBuscar){
        int longitud = txtBuscar.length();
        if(longitud ==0){
            listaLugares.clear();
            listaLugares.addAll(listaOriginal);
        }else{
            List<contactos> collection = listaOriginal.stream()
                    .filter(i -> i.getLugar().toLowerCase().contains(txtBuscar.toLowerCase()))
                    .collect(Collectors.toList());

            listaLugares.clear();
            listaLugares.addAll(collection);
        }
        notifyDataSetChanged();
    }

    public void filtradoEsp(String txtBuscar){
        int longitud = txtBuscar.length();
        if(longitud ==0){
            listaLugares.clear();
            listaLugares.addAll(listaOriginal);
        }else{
            List<contactos> collection = listaOriginal.stream()
                    .filter(i -> i.getLugar().toLowerCase().contains(txtBuscar.toLowerCase()))
                    .collect(Collectors.toList());

            listaLugares.clear();
            listaLugares.addAll(collection);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
       return listaLugares.size();
    }

    public class LugaresViewHolder extends RecyclerView.ViewHolder {
        TextView viewLugar, viewPrecio, viewZona;

        public LugaresViewHolder(@NonNull View itemView) {
            super(itemView);
            viewLugar = itemView.findViewById(R.id.viewLugar);
            viewZona= itemView.findViewById(R.id.viewZona);
            viewPrecio = itemView.findViewById(R.id.viewPrecio);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }
}
