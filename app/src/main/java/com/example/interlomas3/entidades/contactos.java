package com.example.interlomas3.entidades;

public class contactos {
    private int id;
    private String lugar;
    private String zona;
    private String precio;



    private int foto;


    public int getFoto() {return foto;}

    public void setFoto(int foto) {this.foto = foto;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}
