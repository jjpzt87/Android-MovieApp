package com.example.maccombo.movieapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by MacCombo on 21/1/16.
 */
public class AdaptadorOpciones extends ArrayAdapter<Opcion> {

    Activity contexto;
    private Opcion[] datos;

    // Contructor del adaptador
    AdaptadorOpciones(Activity contexto, Opcion[] datos) {
        // Llamamos al constructor de la clase superior
        super(contexto, R.layout.opcion, datos);
        // Guardamos estas variables de la aplicación principal
        this.datos = datos;
        this.contexto = contexto;
    }

    // Método que dibuja la Vista de cada Opción
    // Se invoca cada vez que haya que mostrar un elemento de la lista.
    public View getView(int position, View convertView, ViewGroup parent)
    {
        // Vista que Android indica como reutilizable (memoria que reutilizamos)
        View item = convertView;
        // Esta variable se usa para almacenar un objeto dentro
        // de la Vista que dibuja la opción
        VistaTag vistaTag;

        // Si Android indica que no hay una Vista reutilizable para la opción,
        // la definimos, inflamos el diseño que se define en
        //  opcion.xml
        if(item == null)
        {
            // Usamos un Inflater para inflar el diseño
            // Ahora tenemos una Vista que se asocia al elemento
            LayoutInflater inflater = contexto.getLayoutInflater();
            // Definimos en la vista de vuelta el tipo de diseño
            item = inflater.inflate(R.layout.opcion, null);

            // Definimos el objeto que vamos a almacenar en el nuevo elemento
            vistaTag = new VistaTag();
            // Obtenemos los punteros a las etiquetas y al icono
            vistaTag.titulo = (TextView)item.findViewById(R.id.LblTitulo);
            vistaTag.subtitulo = (TextView)item.findViewById(R.id.LblSubTitulo);
            vistaTag.icono = (ImageView) item.findViewById(R.id.icono);
            // Guardamos el objeto en el elemento
            item.setTag(vistaTag);
        }
        else
        {
            // Si estamos reutilizando una Vista, recuperamos el objeto interno
            vistaTag = (VistaTag)item.getTag();
        }

        // Cargamos los datos de las opciones de la matriz de datos
        vistaTag.titulo.setText(datos[position].getTitulo());
        vistaTag.subtitulo.setText(datos[position].getSubtitulo());
        Picasso.with(this.contexto).load(datos[position].getIcono()).resize(200,300).into(vistaTag.icono);
        //vistaTag.icono.setImageResource(datos[position].getIcono());
        // Devolvemos la Vista (nueva o reutilizada) que dibuja la opción
        return(item);
    }
}
