package com.example.movielist.`interface`

import com.example.movielist.model.TheatreMovies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitServices {
    @GET("discover/movie?primary_release_date.gte=2014-09-15&primary_release_date.lte=2014-10-22")
    fun getMovieList(@Query("api_key") api_key : String) : Call<TheatreMovies>
}
