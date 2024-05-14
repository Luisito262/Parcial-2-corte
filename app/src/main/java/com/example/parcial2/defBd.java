package com.example.parcial2;

public class defBd {

    public static final String nameDB = "productos";
    public static final String tabla_prod = "producto";
    public static final String col_id = "id";
    public static final String col_nombre = "nombre";
    public static final String col_precio = "precio";
    public static final String col_costo = "costo";

    public static final String crear_tabla = "CREATE TABLE IF NOT EXISTS "
            + defBd.tabla_prod + "(" + defBd.col_id + " text primary key," +
            defBd.col_nombre + " text," +
            defBd.col_precio + " text," +
            defBd.col_costo + " text);";
}

