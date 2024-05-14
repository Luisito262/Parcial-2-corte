package com.example.parcial2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    EditText id, nombre, precio, costo;

    Button guardar, consultar, actualizar, eliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //EditText
        id = (EditText) findViewById(R.id.edtidproducto);
        nombre = (EditText) findViewById(R.id.edtnomproducto);
        precio = (EditText) findViewById(R.id.edtprecioproducto);
        costo = (EditText) findViewById(R.id.edtcostoproducto);
        //Buttons
        guardar = (Button) findViewById(R.id.btnguardar);
        consultar = (Button) findViewById(R.id.btnconsultar);
        actualizar = (Button) findViewById(R.id.btnactualizar);
        eliminar = (Button) findViewById(R.id.btnborrar);

        //Actividad
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Productos po = new Productos(id.getText().toString(), nombre.getText().toString(),
                        precio.getText().toString(), costo.getText().toString());
                ProductoControl proc = new ProductoControl(getApplicationContext());
                proc.guardarProducto(po);
            }
        });
        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),ListadoProductos.class);
                startActivity(i);
            }
        });
        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Productos po = new Productos(id.getText().toString(), nombre.getText().toString(),
                        precio.getText().toString(), costo.getText().toString());
                ProductoControl proc = new ProductoControl(getApplicationContext());
                proc.actualizarProducto(po);
            }
        });
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductoControl proc = new ProductoControl(getApplicationContext());
                proc.eliminarProducto(id.getText().toString());

            }
        });
    }
}
