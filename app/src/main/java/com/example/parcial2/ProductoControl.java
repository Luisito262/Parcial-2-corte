package com.example.parcial2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.widget.Toast;

public class ProductoControl {
    private BaseDatos bd;
    private Context c;

    public ProductoControl (Context c){
        this.bd = new BaseDatos(c,defBd.nameDB, 1);
        this.c = c;
    }

    public void guardarProducto (Productos po){
        try {
            SQLiteDatabase sql = bd.getWritableDatabase();
            ContentValues valores = new ContentValues();
            valores.put(defBd.col_id, po.getId());
            valores.put(defBd.col_nombre, po.getNombre());
            valores.put(defBd.col_precio, po.getPrecio());
            valores.put(defBd.col_costo, po.getCosto());
            if (!buscarProducto(po)){
                long id = sql.insert(defBd.tabla_prod,null,valores);
                Toast.makeText(c,"El registro ha sido existoso", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(c,"El id que coloco ya existe en el sistema, vuelva intentar",Toast.LENGTH_LONG).show();
            }
        }
        catch (Exception ex){
            Toast.makeText(c, "Tuvo un error en la operacion", Toast.LENGTH_LONG).show();
        }
    }

    public void actualizarProducto(Productos po){
        try{
            SQLiteDatabase sql = bd.getWritableDatabase();
            String arg[] = {po.getId()};
            ContentValues values = new ContentValues();
            values.put(defBd.col_nombre, po.getNombre());
            values.put(defBd.col_precio, po.getPrecio());
            values.put(defBd.col_costo, po.getCosto());
            sql.update(defBd.tabla_prod, values,"id=?",arg);
            Toast.makeText(this.c, "Se ha Actualizado de manera exitosa", Toast.LENGTH_LONG).show();
        }
        catch (Exception e){
            Toast.makeText(c, "Tuvo un error en la operacion " + e.getMessage() , Toast.LENGTH_SHORT).show();
        }
    }

    public void eliminarProducto(String id){
        try{
            SQLiteDatabase sql = bd.getWritableDatabase();
            String[] arg = new String[] {id};
            sql.delete(defBd.tabla_prod,"id=?", arg);
            Toast.makeText(this.c, "Se ha eliminado correctamente", Toast.LENGTH_LONG).show();
        }
        catch (Exception ex){
            Toast.makeText(c, "Tuvo un error en la operacion " + ex.getMessage() , Toast.LENGTH_SHORT).show();
        }
    }

    public boolean buscarProducto(Productos po){
        String[] arg = new String[] {po.getId()};

        SQLiteDatabase sql = bd.getReadableDatabase();
        Cursor c = sql.query(defBd.tabla_prod, null,"id=?", arg,null,null,null);
        if (c.getCount()>0){
            bd.close();
            return true;
        }
        else{
            return false;
        }
    }

    public Cursor allProducts() {
        Cursor cur;
        try {
            SQLiteDatabase sql = bd.getReadableDatabase();
            cur = sql.rawQuery("select id as _id, nombre, precio, costo from producto order by " + defBd.col_nombre, null);
            return cur;

        } catch (Exception e) {
            Toast.makeText(c, "Error en la operación " + e.getMessage(), Toast.LENGTH_SHORT).show();
            return null;
        }

    }

}
