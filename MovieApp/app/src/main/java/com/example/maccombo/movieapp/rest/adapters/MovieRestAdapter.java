package com.example.maccombo.movieapp.rest.adapters;

import retrofit.RestAdapter;

/**
 * Created by MacCombo on 21/1/16.
 */
public class MovieRestAdapter {

    private static RestAdapter restAdapter;

    public static RestAdapter getInstance(){
        if(restAdapter == null)
            restAdapter = new RestAdapter
                    .Builder()
                    .setEndpoint("http://api.themoviedb.org/3")
                    .build();
        return restAdapter;
    }
}
