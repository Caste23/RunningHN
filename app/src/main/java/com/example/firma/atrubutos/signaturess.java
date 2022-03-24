package com.example.firma.atrubutos;

import android.graphics.Bitmap;

public class signaturess {
    //Decaracion de variables del tipo Private
    private String id;
    private String descripcion;
    private Bitmap FirmaDigital;

    //Hacemos referencia a un constructor vacio
    public signaturess(){

    }
    //Hacemos referencia a un constructor lleno, para los metd
    public signaturess(String id, String descripcion, Bitmap FirmaDigital){
        this.id = id;
        this.descripcion = descripcion;
        this.FirmaDigital = FirmaDigital;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Bitmap getFirmaDigital() {
        return FirmaDigital;
    }

    public void setFirmaDigital(Bitmap firmaDigital) {
        FirmaDigital = firmaDigital;
    }
}
