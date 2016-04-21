package com.example.maccombo.movieapp;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.maccombo.movieapp.rest.PeticionesRestAPI;
import com.example.maccombo.movieapp.rest.adapters.MovieRestAdapter;
import com.example.maccombo.movieapp.rest.model.Movies;
import com.example.maccombo.movieapp.rest.model.Result;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;



public class MainActivity extends ListActivity {

    private Opcion[] datos = new Opcion[20];

    String[] titlesArray = new String[20];
    String[] releaseArray = new String[20];
    String[] imagePathArray = new String[20];
    String[] overviewArray = new String[20];

    int PageRated_tmdb = 1;
    int PagePopular_tmdb = 1;

    boolean PageRatedActivated;

    Button myButtomRated;
    Button myButtomPopular;

    String image_base_url = "http://image.tmdb.org/t/p/w500";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myButtomRated = (Button)findViewById(R.id.bTopRated);
        myButtomPopular = (Button)findViewById(R.id.bPopular);
        PageRatedActivated = true;
        PageRated_tmdb = 1;

        //Aqui engancho con la interface y creo la lista
        final ProgressDialog pd = ProgressDialog.show(this, "Cargando", "Espera...", true, true);

        //Create  service that allows making requests.
        PeticionesRestAPI RestAPIService =
                MovieRestAdapter.getInstance().create(PeticionesRestAPI.class);


        RestAPIService.ListaRatedAsync(Integer.toString(PageRated_tmdb), new Callback<Movies>() {
            @Override
            public void success(Movies movies, Response response) {
                pd.dismiss();
                consumeApiData(movies);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("ERROR! retrofit", "h");
            }

        });

    } // end onCreate


    private void consumeApiData(Movies movies) {

        int j = 0;

        for (Result r : movies.getResults()) {

            titlesArray[j] = r.getOriginalTitle();
            releaseArray[j] = r.getReleaseDate();
            imagePathArray[j]= r.getPosterPath();
            overviewArray[j] = r.getOverview();

            /*
            Log.e("Pagina Create: ", Integer.toString(PageRated_tmdb));
            Log.e("Pelicula Rated: ", Integer.toString(j));
            Log.e("Rf---->>>", titlesArray[j]);
            Log.e("Rf1---->>>", releaseArray[j]);
            Log.e("Rf2---->>",imagePathArray[j]);
            */
            j++;
        }
        // Definimos 20 Opciones del listado en la matriz
        for(int i=0; i<20; i++){

            datos[i] = new Opcion(titlesArray[i], releaseArray[i], image_base_url + imagePathArray[i]);
        }
        // Creamos el Adaptador que se usa para mostrar las opciones del listado
        ArrayAdapter<Opcion> adaptador = new AdaptadorOpciones(this, datos);
        // Asignamos el adaptador al ListActivity para que sepa cómo dibujar el listado de opciones
        setListAdapter(adaptador);

    }


    // Definimos el evento onClick de una opción del listado
    public void onListItemClick(ListView parent, View view, int position, long id)
    {
        // Usamos el parametro "position" para saber la posición de la opción sobre la que el usuario ha hecho clic
        Toast.makeText(getBaseContext(), "Has hecho clic en la '" + datos[position].getTitulo() + "'", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this,OverviewActivity.class);

        i.putExtra("url_poster", image_base_url + imagePathArray[position]);
        i.putExtra("descripcion",overviewArray[position]);

        startActivity(i);
    }


    /////////////////////////////////////////////RATED//////////////////////////////////////////////
    public void botonRated(View vista) {
        //hago llamada a retrofit para peliculas TOP_RATED

        myButtomRated.setBackgroundColor(0xFF22DADA);
        myButtomPopular.setBackgroundColor(0xFF000000);
        PageRatedActivated = true;
        PageRated_tmdb = 1;

        //Aqui engancho con la interface y creo la lista
        final ProgressDialog pd = ProgressDialog.show(this, "Cargando", "Espera...", true, true);

        //Create  service that allows making requests.
        PeticionesRestAPI RestAPIService =
                MovieRestAdapter.getInstance().create(PeticionesRestAPI.class);

        RestAPIService.ListaRatedAsync(Integer.toString(PageRated_tmdb), new Callback<Movies>() {
            //RestAPIService.ListaRatedAsync(new Callback<Movies>() {
            @Override
            public void success(Movies movies, Response response) {
                pd.dismiss();
                consumeApiData(movies);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("ERROR! retrofit", "h");
            }

        });

    }


    ////////////////////////////////////////////////POPULAR/////////////////////////////////////////
    public void botonPopular(View vista) {
        //hago llamada a retrofit para peliculas Popular


        myButtomPopular.setBackgroundColor(0xFF22DADA);
        myButtomRated.setBackgroundColor(0xFF000000);
        PageRatedActivated = false;
        PagePopular_tmdb = 1;

        //Aqui engancho con la interface y creo la lista
        final ProgressDialog pd = ProgressDialog.show(this, "Cargando", "Espera...", true, true);

        //Create  service that allows making requests.
        PeticionesRestAPI RestAPIService =
                MovieRestAdapter.getInstance().create(PeticionesRestAPI.class);

        RestAPIService.ListaPopularAsync(Integer.toString(PagePopular_tmdb), "popularity.desc", new Callback<Movies>() {
            @Override
            public void success(Movies movies, Response response) {
                pd.dismiss();
                int i = 0;
                consumeApiData(movies);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("ERROR! retrofit", "h");
            }

        });

    }

    public void botonPreview(View vista) {
        //hago llamada a retrofit para peliculas TOP_RATED o Popular
        //decrementando pagina

        //Aqui engancho con la interface y creo la lista
        final ProgressDialog pd = ProgressDialog.show(this, "Cargando", "Espera...", true, true);

        //Create  service that allows making requests.
        PeticionesRestAPI RestAPIService =
                MovieRestAdapter.getInstance().create(PeticionesRestAPI.class);

        if(PageRatedActivated ){

            if(PageRated_tmdb > 1){
                PageRated_tmdb--;

                RestAPIService.ListaRatedAsync(Integer.toString(PageRated_tmdb), new Callback<Movies>() {
                    //RestAPIService.ListaRatedAsync(new Callback<Movies>() {
                    @Override
                    public void success(Movies movies, Response response) {
                        pd.dismiss();
                        consumeApiData(movies);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e("ERROR! retrofit", "h");
                    }
                });
            }else{
                pd.dismiss();
            }

        }else{
            if(PagePopular_tmdb > 1){
                PagePopular_tmdb--;

                RestAPIService.ListaPopularAsync(Integer.toString(PagePopular_tmdb), "popularity.desc", new Callback<Movies>() {
                    @Override
                    public void success(Movies movies, Response response) {
                        pd.dismiss();
                        consumeApiData(movies);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e("ERROR! retrofit", "h");
                    }

                });
            }else{
                pd.dismiss();
            }
        }
    }

    public void botonNext(View vista) {
        //hago llamada a retrofit para peliculas TOP_RATED o Popular
        //Inmentando pagina

        //Aqui engancho con la interface y creo la lista
        final ProgressDialog pd = ProgressDialog.show(this, "Cargando", "Espera...", true, true);

        //Create  service that allows making requests.
        PeticionesRestAPI RestAPIService =
                MovieRestAdapter.getInstance().create(PeticionesRestAPI.class);

        if(PageRatedActivated ){

            PageRated_tmdb++;

            RestAPIService.ListaRatedAsync(Integer.toString(PageRated_tmdb), new Callback<Movies>() {
                //RestAPIService.ListaRatedAsync(new Callback<Movies>() {
                @Override
                public void success(Movies movies, Response response) {
                    pd.dismiss();
                    consumeApiData(movies);
                }

                @Override
                public void failure(RetrofitError error) {
                    Log.e("ERROR! retrofit", "h");
                }

            });
        }else{

            PagePopular_tmdb++;

            RestAPIService.ListaPopularAsync(Integer.toString(PagePopular_tmdb), "popularity.desc", new Callback<Movies>() {
                @Override
                public void success(Movies movies, Response response) {
                    pd.dismiss();
                    consumeApiData(movies);
                }

                @Override
                public void failure(RetrofitError error) {
                    Log.e("ERROR! retrofit", "h");
                }
            });

        }
    }
}

