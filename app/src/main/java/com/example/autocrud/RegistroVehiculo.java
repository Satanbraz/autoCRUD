package com.example.autocrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.autocrud.Controler.AutoBD;
import com.example.autocrud.Controler.Controlador;
import com.example.autocrud.Modelo.Auto;

public class RegistroVehiculo extends AppCompatActivity {

    /**Se declaran las variables*/
    EditText editMarca;
    EditText editModelo;
    EditText editPatente;
    EditText editColor;
    EditText editAnio;
    EditText editCilind;
    Button Reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_vehiculo);

        /**Se instancian las variables*/
        editMarca = findViewById(R.id.edtMarca);
        editModelo = findViewById(R.id.edtModelo);
        editPatente = findViewById(R.id.edtPatente);
        editColor = findViewById(R.id.edtColor);
        editAnio = findViewById(R.id.edtAnio);
        editCilind = findViewById(R.id.edtCilindrada);
        Reg = findViewById(R.id.btnRegistrar);

        /**Evento de Bonton Registro*/
        //Se setean los datos al controlador para la validacion de campos
        Reg.setOnClickListener(new View.OnClickListener() {
            Controlador con = new Controlador();
            Auto car = new Auto();

            @Override
            public void onClick(View v) {
                try{
                    //Se obtienen las variables de la clase Auto
                    car.setMarca(editMarca.getText().toString());
                    car.setModelo(editModelo.getText().toString());
                    car.setPatente(editPatente.getText().toString());
                    car.setColor(editColor.getText().toString());
                    String sAnio = editAnio.getText().toString();
                    int iAnio = 0;
                    if (!sAnio.equals("")) {
                        iAnio = Integer.parseInt(sAnio);
                        car.setAnio(iAnio);
                    }
                    String sCilind = editCilind.getText().toString();
                    int iCilind = 0;
                    if (!sCilind.equals("")) {
                        iCilind = Integer.parseInt(sCilind);
                        car.setCilindrada(iCilind);
                    }
                    con.ValidarIngresoDatos(car);
                    validacion(car);
                }catch (RuntimeException e){
                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });//Fin evento onClick
    }//Fin metodo onCreate


    /**Se genera una validacion delos editText*/
    public void validacion(Auto auto) {
        String Marca = editMarca.getText().toString().trim(); //Se toma el dato del campo de texto Marca
        String Modelo = editModelo.getText().toString().trim(); //Se toma el dato del campo de texto Modelo
        String Patente = editPatente.getText().toString().trim(); //Se toma el dato del campo de texto Patente
        String Color = editColor.getText().toString().trim(); //Se toma el dato del campo de texto Color
        String Anio = editAnio.getText().toString().trim(); //Se toma el dato de l campo de texto Anio
        String Cilindrada = editCilind.getText().toString().trim(); //Se toma el dato del campo de texto cilindrada

        /**Validacion campo de texto Marca
         * Preguntar si el campo de texto Marca esta vacio*/
        if (Marca.isEmpty()) {
            editMarca.setError("Debe de Marca del Vehiculo");
            return;
        }//Validar que la cantidad de caracteres de la Marca ingresada sea mayor a 2
        else if (Marca.length() < 2) {
            editMarca.setError("Debe de ingresar una Marca correcta");
            return;
        }
        //Fin validacion campo de texto Marca-------------------------------------------------------

        /**Validacion campo de texto Modelo
         * Preguntar si el campo de texto Modelo esta vacio*/
        if (Modelo.isEmpty()) {
            editModelo.setError("Debe de ingresar Modelo del vehiculo");
        } //Validar que la cantidad de caracteres del modelo sea mayor a 2
        else if (Modelo.length() < 1) {
            editModelo.setError("Debe de ingresar un Modelo correcto");
            return;
        }
        //Fin validacion campo de texto Modelo------------------------------------------------------

        /**Validacion campo de texto Patente
         * Preguntar si el campo de texto Patente esta vacio*/
        if (Patente.isEmpty()) {
            editPatente.setError("Debe de ingresar Patente del vehiculo");
        } //Validar que la cantidad de caracteres de la Patente sea mayor a 6
        else if (Patente.length() < 6) {
            editPatente.setError("Debe de ingresar una Patente correcta");
            return;
        }
        //Fin validacion campo de texto Patente-------------------------------------------------

        /**Validacion campo de texto Color
         * Preguntar si el campo de texto Color esta vacio*/
        if (Color.isEmpty()) {
            editColor.setError("Debe de ingresar el Color del vehiculo");
        } //Validar que la cantidad de caracteres del Color sea mayor a 3
        else if (Color.length() < 3) {
            editColor.setError("Debe de ingresar un Color correcto");
            return;
        }
        //Fin validacion campo de texto Color------------------------------------------------------


        /**Validacion campo de texto Anio
         * Preguntar si el campo texto Anio esta vacio*/
        if (Anio.isEmpty()) {
            editAnio.setError("Debe de ingresar el Año del vehiculo");
            return;
        }//Validar que el Año no supere el limite
        else if (Integer.parseInt(Anio) < 1886 || Integer.parseInt(Anio) > 2024) {
            editAnio.setError("Debe de ingresar un Año dentro del rango: 1886-2024");
            return;
        }
        //Fin validacion campo texto Anio-----------------------------------------------------------

        /**Validacion campo de texto Cilindrada
         * Preguntar si el campo texto Cilindrada esta vacio*/
        if (Cilindrada.isEmpty()) {
            editCilind.setError("Debe de ingresar el Cilindraje del vehiculo");
            return;
        }//Validar que la cilindrada no supere el limite
        else if (Integer.parseInt(Cilindrada) < 600 || Integer.parseInt(Cilindrada) > 5600) {
            editCilind.setError("Debe de ingresar un valor dentro del rango: 600cc-5600cc");
            return;
        }
        //Fin validacion campo texto Cilindrada-----------------------------------------------------------
        GuardarRegistro(auto);
    }//Fin metodo validacion de formulario----------------------------------------------------------


    /**Se genera metodo para guardar los registro nuevos*/
    public void GuardarRegistro(Auto auto){
        AutoBD autoBD = new AutoBD(this);
        SQLiteDatabase SQLiteDB = autoBD.getWritableDatabase();
        try{
            ContentValues c = new ContentValues();
            c.put("MARCA",auto.getMarca());
            c.put("MODELO",auto.getModelo());
            c.put("PATENTE",auto.getPatente());
            c.put("COLOR",auto.getColor());
            c.put("ANIO",auto.getAnio());
            c.put("CILINDRAJE",auto.getCilindrada());
            SQLiteDB.insert("vehiculos",null,c);
            SQLiteDB.close();
            finish();
            Toast.makeText(this, "Registro Guardado", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(this, "Error: "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }//Fin metodo guardar registro
}