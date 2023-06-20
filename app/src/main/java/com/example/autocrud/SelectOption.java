package com.example.autocrud;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

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
    }//Fin metodo onCreate
}