package com.example.autocrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.autocrud.Controler.AutoBD;
import com.example.autocrud.Controler.Controlador;
import com.example.autocrud.Modelo.Auto;

import java.nio.file.Path;
import java.util.ArrayList;

public class ActualizarVehiculo extends AppCompatActivity {

    /**
     * Se declaran las variables
     */
    EditText editMarca;
    EditText editModelo;
    EditText editColor;
    EditText editAnio;
    EditText editCilind;
    Button btnAct;
    Spinner spinnerPat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_vehiculo);

        /**Se instancian las variables*/
        editMarca = findViewById(R.id.edtMarca);
        editModelo = findViewById(R.id.edtModelo);
        editColor = findViewById(R.id.edtColor);
        editAnio = findViewById(R.id.edtAnio);
        editCilind = findViewById(R.id.edtCilindrada);
        btnAct = findViewById(R.id.btnActualizar);
        spinnerPat = findViewById(R.id.spnPatente);

        CargarVistas();

        /**Funcion para obtener el valor seleccionado del Spinner*/
        spinnerPat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                String Pat = parent.getItemAtPosition(pos).toString();
                int PatID = (int) parent.getSelectedItemId();
                //Toast.makeText(ActualizarVehiculo.this, "Patente: "+PatID, Toast.LENGTH_SHORT).show();
                if (PatID != 0) {
                    /**Se genera la consulta en la Base de Datos*/
                    AutoBD autoBD = new AutoBD(getBaseContext());
                    SQLiteDatabase SQLiteDB = autoBD.getReadableDatabase();
                    try {
                        Cursor c = SQLiteDB.rawQuery("select * FROM vehiculos where PATENTE = ?", new String[]{Pat}, null);
                        if (c.moveToFirst()) {
                            editMarca.setText(c.getString(0));//Se lista dato MARCA
                            editModelo.setText(c.getString(1));//Se lista dato MODELO
                            editColor.setText(c.getString(3));//Se lista dato COLOR
                            editAnio.setText(String.valueOf(c.getInt(4)));//Se lista dato ANIO
                            editCilind.setText(String.valueOf(c.getInt(5)));//Se lista dato CILINDRADA
                        }
                    } catch (Exception e) {
                        Toast.makeText(getBaseContext(), "No hay datos registrados \n o hay error en la muestra de datos", Toast.LENGTH_SHORT).show();
                    }
                    SQLiteDB.close();
                } else {
                    editMarca.setText(null);
                    editModelo.setText(null);
                    editColor.setText(null);
                    editAnio.setText(null);
                    editCilind.setText(null);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /**Evento de Boton Actualizar*/
        //Se setean los datos al controlador para la validacion de campos
        btnAct.setOnClickListener(new View.OnClickListener() {
            Controlador con = new Controlador();
            Auto car = new Auto();

            @Override
            public void onClick(View v) {
                int PatID = (int) spinnerPat.getSelectedItemId();
                if (PatID > 0) {
                    try {
                        //Se obtienen las variables de la clase Auto
                        car.setMarca(editMarca.getText().toString());
                        car.setModelo(editModelo.getText().toString());
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
                        con.ValidarActualizacionDatos(car);
                        validacion(car);
                    } catch (RuntimeException e) {
                        Toast.makeText(getBaseContext(), "BD: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ActualizarVehiculo.this, "Debe de seleccionar un registro", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }//Fin metodo onCreate


    /**Se genera una validacion de los editText*/
    public void validacion(Auto auto) {
        String Marca = editMarca.getText().toString().trim(); //Se toma el dato del campo de texto Marca
        String Modelo = editModelo.getText().toString().trim(); //Se toma el dato del campo de texto Modelo
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
        else if (Modelo.length() < 2) {
            editModelo.setError("Debe de ingresar un Modelo correcto");
            return;
        }
        //Fin validacion campo de texto Modelo------------------------------------------------------

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
        ActualizarRegistro(auto);

    }//Fin metodo validacion de formulario----------------------------------------------------------


    /**Se crean losmetodos para agregar los datos al spinner*/
    private void CargarVistas() {
        ArrayList<String> listado = arrayRegs();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listado);
        spinnerPat.setAdapter(adapter);
    }
    private ArrayList<String> arrayRegs() {
        ArrayList<String> datos = new ArrayList<String>();//Se genera un array con los registros
        datos.add("Elija pantente");
        /**Se genera la consulta en la Base de Datos*/
        AutoBD autoBD = new AutoBD(this);
        SQLiteDatabase SQLiteDB = autoBD.getReadableDatabase();
        try {
            Cursor c = SQLiteDB.rawQuery("select PATENTE FROM vehiculos", null);
            if (c.moveToFirst()) {
                do {
                    String dato = c.getString(0);//Se lista dato PATENTE
                    datos.add(dato);
                } while (c.moveToNext());
            }
        } catch (Exception e) {
            Toast.makeText(this, "No hay datos registrados" +
                    "\n o hay error en la muestra de datos", Toast.LENGTH_SHORT).show();
        }
        SQLiteDB.close();
        return datos;
    }//Fin metodo cargar vistas


    /**Se genera metodo para registrar los datos actualizados a la base de datos*/
    public void ActualizarRegistro(Auto auto) {
        AutoBD autoBD = new AutoBD(this);
        SQLiteDatabase SQLiteDB = autoBD.getWritableDatabase();
        try {
            ContentValues c = new ContentValues();
            c.put("MARCA", auto.getMarca());
            c.put("MODELO", auto.getModelo());
            c.put("COLOR", auto.getColor());
            c.put("ANIO", auto.getAnio());
            c.put("CILINDRAJE", auto.getCilindrada());
            //Log.d("Datos",""+c);
            SQLiteDB.update("vehiculos", c, "PATENTE = ?", new String[]{spinnerPat.getSelectedItem().toString()});
            SQLiteDB.close();
            Toast.makeText(this, "Registro Actualizado", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            /**
             *Log.d("Error",e.getMessage()+
             *           "\n Marca: "+auto.getMarca()+
             *           "\n Modelo: "+auto.getModelo()+
             *           "\n Color: "+auto.getColor()+
             *           "\n Año: "+auto.getAnio()+
             *           "\n Cilindrada: "+ auto.getCilindrada());
             */
        }

    }//Fin metodo actualizar registro

}