package com.example.firma.tablas;

public class tablas {
    //creamos el nombre de la tabla
    public static final String tablaFirma= "Firma";
    //creamos los campos
    public static final String id = "id";
    public static final String firmas = "firma";
    public static final String nombre = "nombre";

    public static final String CreateTableRegistros= "CREATE TABLE Firma( id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, firma BLOB, nombre TEXT)";
    public static final String DropTableRegistros = "DROP TABLE IF EXISTS Firma";
    public static final String NameDataBase = "DB_Firmasbl.db";



}
