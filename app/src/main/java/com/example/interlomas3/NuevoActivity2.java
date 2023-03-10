package com.example.interlomas3;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.interlomas3.adaptadores.listaLugaresAdapter2;
import com.example.interlomas3.db.DbLugares;
import com.example.interlomas3.entidades.contactos;

import java.util.ArrayList;

public class NuevoActivity2 extends AppCompatActivity implements SearchView.OnQueryTextListener {

    EditText txtLugar, txtPrecio, txtZona;
    SearchView txtBuscar;

    String zone;
    RecyclerView listaLug;
    ArrayList<contactos> listaArrayLugares;
    listaLugaresAdapter2 adapter;

    ScaleGestureDetector scaleDetector;

    Spinner spinZon;


    TextView viewLugar, viewPrecio, viewZona;

    //Button btnInsert;


    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);
        listaLug = findViewById(R.id.listaLugares);

        txtBuscar = findViewById(R.id.txtBuscar);

        viewZona = findViewById(R.id.viewZona);

        listaLug.setLayoutManager(new LinearLayoutManager(this));

        DbLugares dbLugares = new DbLugares(NuevoActivity2.this);
        listaArrayLugares = new ArrayList<>();
        LinearLayoutManager llm = new LinearLayoutManager(this);

        Bundle extras = getIntent().getExtras();
        zone = extras.getString("ZONA");


        adapter = new listaLugaresAdapter2(dbLugares.mostrarLug2(zone));
        listaLug.setAdapter(adapter);

        txtBuscar.setOnQueryTextListener(this);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, llm.getOrientation());
        listaLug.addItemDecoration(dividerItemDecoration);


        spinZon = (Spinner) findViewById(R.id.spinZonas);
        ArrayAdapter spinAdapter = ArrayAdapter.createFromResource(this, R.array.Zonas, R.layout.my_selected_item);
        spinAdapter.setDropDownViewResource(R.layout.my_dropdown_item);
        spinZon.setAdapter(spinAdapter);

        spinZon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                adapter.filtrado(adapterView.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                adapter.filtrado("");

            }
        });


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