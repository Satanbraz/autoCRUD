package com.example.autocrud.Controler;

import com.example.autocrud.Modelo.Auto;
    /**
     * Método que recibe como parámetro un objeto auto
     * Los métodos reciben parámetros objetos extendidos de auto
     * Realiza válidaciones y genera excepciones
     **/
    public class Controlador {

        public void ValidarIngresoDatos(Auto auto) {
            if (auto.getMarca().isEmpty()) {
                throw new RuntimeException("Ingrese Marca del vehiculo");
            } else if (auto.getMarca().length() < 2) {
                throw new RuntimeException("La marca debe tener un largo superior a 2 caracteres");
            }//Fin validacion MARCA

            if (auto.getModelo().isEmpty()) {
                throw new RuntimeException("Ingrese Modelo del vehículo");
            } else if (auto.getModelo().length() < 1) {
                throw new RuntimeException("El modelo debe tener un largo superior a 3 caracteres");
            }//Fin validacion MODELO

            if (auto.getColor().isEmpty()) {
                throw new RuntimeException("Ingrese el color del vehiculo");
                //Dato recopilado indicando que el color que menos letras tiene es 'oro' (3)
            } else if (auto.getColor().length() < 3) {
                throw new RuntimeException("El color debe tener un largo superior a 3 caracteres");
            }//Fin validacion COLOR

            if (auto.getPatente().isEmpty()) {
                throw new RuntimeException("Debe ingresar la pantente del vehiculo");
            } else if (auto.getPatente().length() < 6||auto.getPatente().length()>6) {
                throw new RuntimeException("La patente debe de ser igual a 6 caracteres (cl)");
            }//Fin validacion PATENTE

            if (auto.getAnio() == 0) {
                throw new RuntimeException("Ingrese año del vehiculo");
                //Dato basado en el año de inscripcion del primer vehiculo
            } else if (auto.getAnio() < 1886 || auto.getAnio() > 2024) {
                throw new RuntimeException("El rango de años es desde 1886 hasta 2024");
            }//Fin validacion ANIO

            if (auto.getCilindrada() == 0) {
                throw new RuntimeException("Ingrese cilindrada del vehiculo");
            } else if (auto.getCilindrada() < 600 || auto.getCilindrada() > 5600) {
                throw new RuntimeException("El Cilindraje debe de estar entre 600cc y 5600cc");
            }//Fin validacion CILINDRADA
        }//Fin metodo ValidarIngresoDatos

        public void ValidarActualizacionDatos(Auto auto) {
            if (auto.getMarca().isEmpty()) {
                throw new RuntimeException("Ingrese Marca del vehiculo");
            } else if (auto.getMarca().length() < 2) {
                throw new RuntimeException("La marca debe tener un largo superior a 2 caracteres");
            }//Fin validacion MARCA

            if (auto.getModelo().isEmpty()) {
                throw new RuntimeException("Ingrese Modelo del vehículo");
            } else if (auto.getModelo().length() < 1) {
                throw new RuntimeException("El modelo debe tener un largo superior a 3 caracteres");
            }//Fin validacion MODELO

            if (auto.getColor().isEmpty()) {
                throw new RuntimeException("Ingrese el color del vehiculo");
                //Dato recopilado indicando que el color que menos letras tiene es 'oro' (3)
            } else if (auto.getColor().length() < 3) {
                throw new RuntimeException("El color debe tener un largo superior a 3 caracteres");
            }//Fin validacion COLOR

            if (auto.getAnio() == 0) {
                throw new RuntimeException("Ingrese año del vehiculo");
                //Dato basado en el año de inscripcion del primer vehiculo
            } else if (auto.getAnio() < 1886 || auto.getAnio() > 2024) {
                throw new RuntimeException("El rango de años es desde 1886 hasta 2024");
            }//Fin validacion ANIO

            if (auto.getCilindrada() == 0) {
                throw new RuntimeException("Ingrese cilindrada del vehiculo");
            } else if (auto.getCilindrada() < 600 || auto.getCilindrada() > 5600) {
                throw new RuntimeException("El Cilindraje debe de estar entre 600cc y 5600cc");
            }//Fin validacion CILINDRADA
        }//Fin metodo ValidarIngresoDatos
}
