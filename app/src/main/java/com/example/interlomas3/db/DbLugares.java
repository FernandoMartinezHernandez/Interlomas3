package com.example.interlomas3.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.interlomas3.entidades.contactos;

import java.util.ArrayList;

public class DbLugares extends DbHelper {

    Context context;

    public DbLugares(@Nullable Context context) {
        super(context);
        this.context=context;
    }

    public long insertarLugar(String lugar, String precio, String zona){
       long id =0;

        try {
           DbHelper dbHelper = new DbHelper(context);
           SQLiteDatabase db = dbHelper.getWritableDatabase();

           ContentValues values = new ContentValues();
           values.put("nombreLugar", lugar);
           values.put("precio", precio);
           values.put("zona", zona);

           id = db.insert(TABLE_LUGARES, null, values);



       }catch (Exception ex){
           ex.toString();
       }
        return id;
    }


    public ArrayList<contactos> mostrarLug(){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<contactos> listaLugares = new ArrayList<>();

        contactos lugares = null;
        Cursor cursorLug=null;

        cursorLug = db.rawQuery("SELECT * FROM "+ TABLE_LUGARES, null);

        if(cursorLug.moveToFirst()){
            do{
                lugares = new contactos();
                lugares.setId(cursorLug.getInt(0));
                lugares.setLugar(cursorLug.getString(1));
                lugares.setPrecio(cursorLug.getString(2));
                lugares.setZona(cursorLug.getString(3));
                listaLugares.add(lugares);
            } while (cursorLug.moveToNext());
        }
        cursorLug.close();;

        return listaLugares;

    }

    public contactos verContactos(int id){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        contactos lugares = null;
        Cursor cursorLug=null;

        cursorLug = db.rawQuery("SELECT * FROM "+ TABLE_LUGARES + " WHERE id =" + id + " LIMIT 1 ", null);

        if(cursorLug.moveToFirst()){

            lugares = new contactos();
            lugares.setId(cursorLug.getInt(0));
            lugares.setLugar(cursorLug.getString(1));
            lugares.setPrecio(cursorLug.getString(2));
            lugares.setZona(cursorLug.getString(3));

        }
        cursorLug.close();;

        return lugares;

    }

}
