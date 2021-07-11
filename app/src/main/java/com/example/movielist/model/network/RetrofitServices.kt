package com.example.movielist.model.network

import com.example.movielist.utils.Constants.API_ENDPOINT
import com.example.movielist.utils.Constants.API_KEY
import com.example.movielist.model.entities.TheatreMovies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitServices {
    @GET(API_ENDPOINT)
    fun getMovieList(@Query(API_KEY) api_key : String) : Call<TheatreMovies>
}
