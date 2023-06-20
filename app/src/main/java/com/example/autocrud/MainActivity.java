package com.example.autocrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    /**variable para asignar tiempo de actividad de la Activity*/
    final static int splash_screen = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Para fullScreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        /**Se instancia el metodo para el tiempo de actividad*/
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getBaseContext(), SelectOption.class); //Se instancia la ventana destino
                startActivity(intent); //Se inicia el metodo de llamada
                finish();// Cierra el metodo
            }
        }, splash_screen); //Se hace la llamada a la variable de tiempo
    }
}