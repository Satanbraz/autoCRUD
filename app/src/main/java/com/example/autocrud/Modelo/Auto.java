package com.example.autocrud.Modelo;
public class Auto {

    private String marca;

    private String modelo;

    private String color;

    private String patente;

    private int anio;

    private int cilindrada;

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(int cilindrada) {
        this.cilindrada = cilindrada;
    }

    public Auto(){
    }

    public Auto(String marca, String modelo, String color, String patente, int anio, int cilindrada) {
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.patente = patente;
        this.anio = anio;
        this.cilindrada = cilindrada;
    }

    public Auto(String marca, String modelo, String color, int anio, int cilindrada) {
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.anio = anio;
        this.cilindrada = cilindrada;
    }

    @Override
    public String toString() {
        return "Auto{" +
                "marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", color='" + color + '\'' +
                ", patente='" + patente + '\'' +
                ", anio=" + anio +
                ", cilindrada=" + cilindrada +
                '}';
    }
}
