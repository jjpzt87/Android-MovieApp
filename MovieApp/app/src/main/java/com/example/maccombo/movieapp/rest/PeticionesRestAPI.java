package com.example.maccombo.movieapp.rest;


import com.example.maccombo.movieapp.rest.model.Movies;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by MacCombo on 21/1/16.
 */
public interface PeticionesRestAPI {

    //RETROFIT
    @GET("/movie/top_rated?api_key=6800591ce90c99f489fa7b9f2a9e00f4")
    public void ListaRatedAsync(@Query("page") String numeroPagina, Callback<Movies> cb);


    @GET("/discover/movie?api_key=6800591ce90c99f489fa7b9f2a9e00f4")
    public void ListaPopularAsync(@Query("page") String numeroPagina, @Query("sort_by") String sortingFilter, Callback<Movies> cb);

    //RXJAVA
    @GET("/movie/top_rated?api_key=6800591ce90c99f489fa7b9f2a9e00f4")
    public Observable<Movies> ListaRatedRX(@Query("page") String numeroPagina);

    @GET("/discover/movie?api_key=6800591ce90c99f489fa7b9f2a9e00f4")
    public Observable<Movies> ListaPopularRX(@Query("page") String numeroPagina, @Query("sort_by") String sortingFilter);
}
