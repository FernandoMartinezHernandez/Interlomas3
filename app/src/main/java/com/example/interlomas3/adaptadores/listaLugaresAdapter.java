package com.example.interlomas3.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.interlomas3.R;
import com.example.interlomas3.VerActivity;
import com.example.interlomas3.entidades.contactos;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_lugar, null, false);
        return new LugaresViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LugaresViewHolder holder, int position) {
        holder.viewLugar.setText(listaLugares.get(position).getLugar());
        holder.viewZona.setText(listaLugares.get(position).getZona());
        holder.viewPrecio.setText(listaLugares.get(position).getPrecio());
    }

    public void filtrado(String txtBuscar){
        int longitud = txtBuscar.length();
        if(longitud ==0){
            listaLugares.clear();
            listaLugares.addAll(listaOriginal);

        }else{
            List<contactos> collection = listaLugares.stream()
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

        public LugaresViewHolder(@NonNull View itemView) {
            super(itemView);
            viewLugar = itemView.findViewById(R.id.viewLugar);
            viewZona= itemView.findViewById(R.id.viewZona);
            viewPrecio = itemView.findViewById(R.id.viewPrecio);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerActivity.class);
                    intent.putExtra("ID", listaLugares.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
