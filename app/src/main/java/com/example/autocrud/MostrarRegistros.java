package com.example.autocrud;

import androidx.annotation.ArrayRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.autocrud.Controler.AutoBD;

import java.util.ArrayList;

public class MostrarRegistros extends AppCompatActivity {
    /**Se declaran las variables*/
    ListView lstRegs;
    ArrayList<String> listado;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_registros);

        /**Se instancian las variables*/
        lstRegs = findViewById(R.id.lstRegistros);

        CargarVistas();
    }//Fin metodo onCreate

    /**Se genera metodo que guarda los datos en una cadena*/
    private void CargarVistas(){
        listado = arrayRegs();
        if (listado.isEmpty()){
            Toast.makeText(this, "No hay registros", Toast.LENGTH_SHORT).show();
             finish();
        }else{
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listado);
            lstRegs.setAdapter(adapter);
        }
    }//Fin metodo cargar vistas

    /**Se genera metodo para cargar datos a la lista desde la base de datos*/
    private ArrayList<String> arrayRegs() {
        ArrayList<String> datos = new ArrayList<String>();//Se genera un array con los registros

        /**Se genera la consulta en la Base de Datos*/
        AutoBD autoBD = new AutoBD(this);
        SQLiteDatabase SQLiteDB = autoBD.getReadableDatabase();
        try {
            Cursor c = SQLiteDB.rawQuery("select * FROM vehiculos", null);
            if (c.moveToFirst()) {
                do {
                    String dato = "Marca: "+c.getString(0) + "\n"+//Se lista dato MARCA
                            "Modelo: "+c.getString(1) + "\n" +//Se lista dato MODELO
                            "Patente: "+c.getString(2).toUpperCase() + "\n" +//Se lista dato PATENTE
                            "Color: "+c.getString(3) + "\n" +//Se lista dato COLOR
                            "Año: "+c.getInt(4) + "\n" +//Se lista dato ANIO
                            "Calindraje: "+c.getInt(5)+"cc";//Se lista dato CILINDRADA
                    datos.add(dato);
                } while (c.moveToNext());
            }
        } catch (Exception e) {
            Toast.makeText(this, "No hay datos registrados" +
                    "\n o hay error en la muestra de datos", Toast.LENGTH_SHORT).show();
        }SQLiteDB.close();
        return datos;
    }//Fin metodo ArrayRegs


}