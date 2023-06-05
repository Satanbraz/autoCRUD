package com.example.autocrud;

import androidx.appcompat.app.AppCompatActivity;

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

public class EliminarVehiculo extends AppCompatActivity {
    /**Se declaran las variables*/
    ListView lstRegs;
    ArrayList<String> listado;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_vehiculo);

        /**Se instancian las variables*/
        lstRegs = findViewById(R.id.lstRegistros);

        CargarVistas();

        /**Se genera metodo para eliminar un registro*/
        lstRegs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                new AlertDialog.Builder(EliminarVehiculo.this)
                    .setTitle("¿Quieres eliminar este registro?")
                        .setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                            int id = (int) lstRegs.getItemIdAtPosition(position)+1;
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String dato = new String();
                                /**Se genera la consulta en la Base de Datos*/
                                AutoBD autoBD = new AutoBD(EliminarVehiculo.this);
                                SQLiteDatabase SQLiteDB = autoBD.getReadableDatabase();
                                SQLiteDatabase SQLiteDBg = autoBD.getWritableDatabase();
                                try {
                                    Cursor c = SQLiteDB.rawQuery("select PATENTE FROM vehiculos where rowid = " + id, null);
                                    if (c.moveToFirst()) {
                                        dato = c.getString(0);
                                        //Toast.makeText(EliminarVehiculo.this, "PATENTE:" + dato, Toast.LENGTH_SHORT).show();
                                        SQLiteDBg.delete("vehiculos", "PATENTE = '" + dato + "'", null);
                                        SQLiteDBg.close();
                                        Toast.makeText(EliminarVehiculo.this, "Registro eliminado", Toast.LENGTH_SHORT).show();
                                        CargarVistas();
                                    }
                                }catch (Exception e){
                                    Log.d("error: ",e.getMessage());
                                    Toast.makeText(getBaseContext(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }SQLiteDB.close();
                                //Toast.makeText(EliminarVehiculo.this, "pos: "+ id, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create().show();
            }
        });
    }//Fin metodo onCreate


    /**Se genera metodo que guarda los datos en una cadena*/
    private void CargarVistas(){
        listado = arrayRegs();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listado);
        lstRegs.setAdapter(adapter);
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
            Toast.makeText(this, "No hay datos registrador" +
                    "\n o hay error en la muestra de datos", Toast.LENGTH_SHORT).show();
        }SQLiteDB.close();
        return datos;
    }//Fin metodo ArrayRegs

}