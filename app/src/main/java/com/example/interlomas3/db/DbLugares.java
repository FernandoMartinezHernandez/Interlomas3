package com.example.interlomas3.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.interlomas3.R;
import com.example.interlomas3.entidades.contactos;

import java.util.ArrayList;

public class DbLugares extends DbHelper {

    Context context;

    int imgs []={R.drawable.servicio_local, R.drawable.huixquilucan,R.drawable.naucalpan,R.drawable.acolman, R.drawable.atizapan,
    R.drawable.coacalco, R.drawable.chicoloapan,R.drawable.chimalhuacan, R.drawable.cuautitlan, R.drawable.romeror, R.drawable.ecatepec,
    R.drawable.ixtapaluca, R.drawable.jardines,R.drawable.paz,R.drawable.neza, R.drawable.nicolasr, R.drawable.tecamac,R.drawable.texcoco,
    R.drawable.tepoztlan, R.drawable.tlanepan, R.drawable.tultep, R.drawable.tultlit, R.drawable.valledech, R.drawable.alvaroobr,
    R.drawable.azcapot, R.drawable.benitoj, R.drawable.cuajimal,R.drawable.coyoacan, R.drawable.cuauhte, R.drawable.gustavo,
    R.drawable.iztacalco,R.drawable.iztapalapa, R.drawable.magdalenacon,R.drawable.miguelhid,R.drawable.milpaalta,R.drawable.tlahuac,
    R.drawable.tlalpan,R.drawable.venustianocarr, R.drawable.xochimilco,R.drawable.metro, R.drawable.centralescam, R.drawable.aerop,
    R.drawable.foraneos, R.drawable.hospital,R.drawable.museos, R.drawable.hotel,R.drawable.embajada};

    int conta =0;

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


   /* public ArrayList<contactos> mostrarLug(){
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

    }*/

    public ArrayList<contactos> mostrarLug2(String zone){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<contactos> listaLugares = new ArrayList<>();

        contactos lugares = null;
        Cursor cursorLug=null;

        cursorLug = db.rawQuery("SELECT * FROM "+ TABLE_LUGARES + " WHERE zona =" + "'"+zone+"'", null);

        //SELECT * FROM  t_lugares where zona='NAUCALPAN'

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

    public ArrayList<contactos> mostrarZona(){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<contactos> listaLugares = new ArrayList<>();

        contactos lugares = null;
        Cursor cursorLug=null;

        cursorLug = db.rawQuery("SELECT DISTINCT zona as zona FROM "+ TABLE_LUGARES, null);

        if(cursorLug.moveToFirst()){
            do{

                lugares = new contactos();
                //lugares.setId(cursorLug.getInt(1));
                // lugares.setLugar(cursorLug.getString(1));
                // lugares.setPrecio(cursorLug.getString(2));
                lugares.setZona(cursorLug.getString(0));
                lugares.setFoto(imgs[conta]);

                conta +=1;


                //lugares.setFoto(R.drawable.servicio_local);

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

    public contactos verZona(String zona){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        contactos lugares = null;
        Cursor cursorLug=null;

        cursorLug = db.rawQuery("SELECT * FROM "+ TABLE_LUGARES + " WHERE zona =" + zona + " LIMIT 16 ", null);

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
