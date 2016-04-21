package com.example.maccombo.movieapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by MacCombo on 22/1/16.
 */
public class OverviewActivity extends Activity{

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.overview);

        // Obtenemos el contenidos de la variable extras
        Bundle extra = getIntent().getExtras();
        // Si no hay variable extra no ejecutamos las siguientes sentencias
        if (extra == null) return;
        // Leemos los contenidos de las variables extra
        String url_overview_poster = extra.getString("url_poster");
        String descripcion_overview = extra.getString("descripcion");

        ImageView myPoster = (ImageView) findViewById(R.id.icono_overview);

        Picasso.with(this).load(url_overview_poster).into(myPoster);
        ((TextView) findViewById(R.id.LblOverview)).setText(descripcion_overview);

    }

    @Override
    // Método de la actividad que se invoca cuando Èsta finaliza
    public void finish() {
        super.finish();
    }

}
