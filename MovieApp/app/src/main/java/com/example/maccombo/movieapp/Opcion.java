package com.example.maccombo.movieapp;

import com.squareup.picasso.Picasso;

/**
 * Created by MacCombo on 21/1/16.
 */
public class Opcion {

    // Cada opci�n tiene un t�tulo, un subt�tulo y un icono
    private String titulo;
    private String subtitulo;
    //private int icono;
    private String icono;
    //public Picasso icono;

    //public Opcion(String titulo, String subtitulo, Picasso icono){
    public Opcion(String titulo, String subtitulo, String icono){
    //public Opcion(String titulo, String subtitulo, int icono){
        this.setTitulo(titulo);
        this.setSubtitulo(subtitulo);
        this.setIcono(icono);
    }

    // Definimos los getters y setters de la clase
    public String getTitulo() {return titulo;}
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public String getSubtitulo() {
        return subtitulo;
    }
    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }


    public String getIcono() {return icono;}
    //public int getIcono() {return icono;}
    //public Picasso getIcono() {return icono;}
    public void setIcono(String icono) {this.icono = icono;}
    //public void setIcono(int icono) {this.icono = icono;}
    //public void setIcono(Picasso icono) {this.icono = icono;}
}
