package com.example.interlomas3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.interlomas3.adaptadores.listaLugaresAdapter;
import com.example.interlomas3.db.DbHelper;
import com.example.interlomas3.db.DbLugares;
import com.example.interlomas3.entidades.contactos;

import java.util.ArrayList;

public class NuevoActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    EditText txtLugar, txtPrecio, txtZona;
    SearchView txtBuscar;
    RecyclerView listaLug;
    ArrayList<contactos> listaArrayLugares;
    listaLugaresAdapter adapter;
    //Button btnInsert;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);
        listaLug = findViewById(R.id.listaLugares);

        txtBuscar = findViewById(R.id.txtBuscar);

        listaLug.setLayoutManager(new LinearLayoutManager(this));

        DbLugares dbLugares = new DbLugares(NuevoActivity.this);
        listaArrayLugares = new ArrayList<>();

        adapter = new listaLugaresAdapter(dbLugares.mostrarLug());
        listaLug.setAdapter(adapter);

        txtBuscar.setOnQueryTextListener(this);



       /* btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbLugares dblugares = new DbLugares(NuevoActivity.this);
               long id = dblugares.insertarLugar(txtLugar.getText().toString(),txtPrecio.getText().toString(),txtZona.getText().toString());

               if(id>0){
                   Toast.makeText(NuevoActivity.this, "Registro Guardado", Toast.LENGTH_LONG);
                   limpiar();
               }else{
                   Toast.makeText(NuevoActivity.this, "Registro NO guardado", Toast.LENGTH_LONG);
               }
            }
        });*/


    }

    private void limpiar(){
        txtZona.setText("");
        txtPrecio.setText("");
        txtLugar.setText("");
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adapter.filtrado(s);

        return false;
    }
}