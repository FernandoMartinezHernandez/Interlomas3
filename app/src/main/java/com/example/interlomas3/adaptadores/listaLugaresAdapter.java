package com.example.interlomas3.adaptadores;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.interlomas3.NuevoActivity2;
import com.example.interlomas3.R;
import com.example.interlomas3.entidades.contactos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class listaLugaresAdapter extends RecyclerView.Adapter<listaLugaresAdapter.LugaresViewHolder> {

    ArrayList<contactos> listaLugares;
    ArrayList<contactos> listaOriginal;


    public listaLugaresAdapter(ArrayList<contactos> listaLugares){
        this.listaLugares = listaLugares;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaLugares);

    }

    @NonNull
    @Override
    public LugaresViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_lugar2, null, false);
        return new LugaresViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull LugaresViewHolder holder, int position) {

        holder.viewZona.setText(listaLugares.get(position).getZona());
        holder.foto.setImageResource(listaLugares.get(position).getFoto());

        if((position % 2) == 0){
            holder.viewZona.setBackgroundColor(Color.rgb(4,138,191));

        } else{
            holder.viewZona.setBackgroundColor(Color.rgb(220,220,220));

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
                    .filter(i -> i.getZona().toLowerCase().contains(txtBuscar.toLowerCase()))
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
        ImageView foto;

        public LugaresViewHolder(@NonNull View itemView) {
            super(itemView);
            viewLugar = itemView.findViewById(R.id.viewLugar);
            viewZona= itemView.findViewById(R.id.viewZona);
            viewPrecio = itemView.findViewById(R.id.viewPrecio);
            foto = itemView.findViewById(R.id.imageView);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, NuevoActivity2.class);
                    intent.putExtra("ZONA", listaLugares.get(getAdapterPosition()).getZona());
                    context.startActivity(intent);
                }
            });
        }
    }
}
