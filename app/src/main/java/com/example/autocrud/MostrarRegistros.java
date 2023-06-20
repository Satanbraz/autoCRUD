package com.example.autocrud;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.autocrud.Controler.AutoBD;
import com.example.autocrud.Modelo.Auto;

import java.util.ArrayList;

public class MostrarRegistros extends AppCompatActivity {
    /**Se declaran las variables*/
    ArrayList<Auto> autoArrayList;
    RecyclerView recyclerView;
    AdapterLista adapterLista;
    EditText etBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_registros);

        //Se crea una lista para guardar los registros de vehiculos
        autoArrayList = new ArrayList<>();
        recyclerView = findViewById(R.id.lstRegistros);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        llenarAuto();
        // Obtener referencia al RecyclerView y configurarlo con el adaptador

        etBuscar = findViewById(R.id.edtBuscar);

        etBuscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                filtrar(s.toString());
            }
        });


        // Deshabilitar el onClick del CardView
        adapterLista.setCardViewClickable(false);
    }//Fin metodo onCreate

    public void filtrar(String texto){
        ArrayList<Auto> filtroAuto = new ArrayList<>();
        for(Auto auto : autoArrayList){
            if (auto.getMarca().toLowerCase().contains(texto.toLowerCase())||
                    auto.getModelo().toLowerCase().contains(texto.toLowerCase())||
                    auto.getPatente().toLowerCase().contains(texto.toLowerCase())||
                    auto.getColor().toLowerCase().contains(texto.toLowerCase())){
                filtroAuto.add(auto);
            }
        }
        adapterLista.filtrarAuto(filtroAuto);
    }


    //Metodo para llenar RecyclerView con los datos de la BD
    private void llenarAuto(){
        autoArrayList.clear();

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

                    //Se rellena array con los datos encontrados
                    autoArrayList.add(auto);
                } while (c.moveToNext());
            }else{
                //Arrojar mensaje en caso de no tener registros
                Toast.makeText(this, "No hay registros", Toast.LENGTH_SHORT).show();
                finish();
            }
        } catch (Exception e) {
            //Arrojar mensaje de error
            Toast.makeText(this, " "+e.getMessage(), Toast.LENGTH_SHORT).show();
            Log.d("Error: ", e.getMessage());
        }
        adapterLista = new AdapterLista(autoArrayList);
        recyclerView.setAdapter(adapterLista);
        SQLiteDB.close();
    }
}