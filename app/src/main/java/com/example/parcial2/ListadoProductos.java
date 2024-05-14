package com.example.parcial2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.Toast;

public class ListadoProductos extends AppCompatActivity {


    ListView listado;
    ProductoCursorAdapter pocur;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_productos);
        listado = (ListView) findViewById(R.id.lstlistado);
        ProductoControl proc = new ProductoControl(this);
        Cursor cur = proc.allProducts();
        if (cur.getCount() > 0) {
            pocur = new ProductoCursorAdapter(this, cur, false);
            listado.setAdapter(pocur);
            listado.setTextFilterEnabled(true);
            pocur.setFilterQueryProvider(new FilterQueryProvider() {
                @Override
                public Cursor runQuery(CharSequence constraint) {
                    return null;
                }
            });
        }
        else
            Toast.makeText(getApplicationContext(), "No hay datos",Toast.LENGTH_SHORT).show();
    }

}


