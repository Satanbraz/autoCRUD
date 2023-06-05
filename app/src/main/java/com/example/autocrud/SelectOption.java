package com.example.autocrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.autocrud.Controler.AutoBD;
import com.example.autocrud.Modelo.Auto;

public class SelectOption extends AppCompatActivity {

    /**Se declaran las variables*/
    Button create;
    Button read;
    Button update;
    Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_option);

        /**Se instancian las variables*/
        create = findViewById(R.id.btnReg);
        read = findViewById(R.id.btnRead);
        update = findViewById(R.id.btnUpd);
        delete = findViewById(R.id.btnDel);

        /**Creamos la Base de Datos de SQLite*/
        //AutoBD ADB = new AutoBD(this);
        //SQLiteDatabase SQLitedb = ADB.getWritableDatabase();
        //if (SQLitedb!= null){
        //    Toast.makeText(this, "La base de datos ha sido creada", Toast.LENGTH_SHORT).show();
        //}else{
        //    Toast.makeText(this, "La base de datos ya existe o hay un error en la creacion", Toast.LENGTH_SHORT).show();
        //}

        /**Se crean los eventos clickListener para cada boton de opciones*/
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext() , RegistroVehiculo.class);
                startActivity(intent);
                onPause();
            }
        });
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext() , MostrarRegistros.class);
                startActivity(intent);
                onPause();
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext() , ActualizarVehiculo.class);
                startActivity(intent);
                onPause();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext() , EliminarVehiculo.class);
                startActivity(intent);
                onPause();
            }
        });
    }




}