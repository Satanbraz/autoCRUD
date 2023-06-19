package com.example.autocrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.autocrud.Controler.AutoBD;
import com.example.autocrud.Modelo.Auto;

import java.util.ArrayList;

public class EliminarVehiculo extends AppCompatActivity{
    /**Se declaran las variables*/
    ArrayList<Auto> autoArrayList;
    RecyclerView recyclerView;
    AdapterLista adapterLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_vehiculo);

        autoArrayList = new ArrayList<>();
        recyclerView = findViewById(R.id.lstRegistros);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        CargarVistas();
        AdapterLista adapterLista = new AdapterLista(autoArrayList);
        recyclerView.setAdapter(adapterLista);

    }//Fin metodo onCreate


    /**Se genera metodo que guarda los datos en una cadena*/
    private void CargarVistas(){

        Auto auto;

        /**Se genera la consulta en la Base de Datos*/
        AutoBD autoBD = new AutoBD(this);
        SQLiteDatabase SQLiteDB = autoBD.getReadableDatabase();
        try {
            Cursor c = SQLiteDB.rawQuery("select * FROM vehiculos", null);
            if (c.moveToFirst()) {
                do {
                    auto = new Auto();

                    auto.setMarca(c.getString(0));
                    auto.setModelo(c.getString(1));
                    auto.setPatente(c.getString(2));
                    auto.setColor(c.getString(3));
                    auto.setAnio(c.getInt(4));
                    auto.setCilindrada(c.getInt(5));

                    autoArrayList.add(auto);
                } while (c.moveToNext());
            }else{
                Toast.makeText(this, "No hay registros para eliminar", Toast.LENGTH_SHORT).show();
                finish();
            }
        } catch (Exception e) {
            Toast.makeText(this, " "+e.getMessage(), Toast.LENGTH_SHORT).show();
            Log.d("Error: ", e.getMessage());
        }SQLiteDB.close();
    }

    }