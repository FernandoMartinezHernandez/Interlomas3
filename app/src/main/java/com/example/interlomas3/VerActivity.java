package com.example.interlomas3;

import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.interlomas3.db.DbLugares;
import com.example.interlomas3.entidades.contactos;

public class VerActivity extends AppCompatActivity {

    EditText txtLugar, txtZona, txtPrecio;
    contactos contacto;

    int id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        txtLugar=findViewById(R.id.txtLugar);
        txtZona=findViewById(R.id.txtZona);
        txtPrecio=findViewById(R.id.txtPrecio);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);

            }else{
                id = extras.getInt("ID");
            }
        }else{
            id = (int) savedInstanceState.getSerializable("ID");
        }

        DbLugares dbLugares = new DbLugares(VerActivity.this);
        contacto = dbLugares.verContactos(id);

        if(contacto != null){
            txtLugar.setText(contacto.getLugar());
            txtZona.setText(contacto.getZona());
            txtPrecio.setText(contacto.getPrecio());

            txtLugar.setInputType(InputType.TYPE_NULL);
            txtZona.setInputType(InputType.TYPE_NULL);

            txtPrecio.setInputType(InputType.TYPE_NULL);




        }


    }
}