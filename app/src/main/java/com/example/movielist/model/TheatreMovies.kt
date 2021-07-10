package com.example.movielist.model

import com.google.gson.annotations.SerializedName

data class TheatreMovies(
//    @SerializedName("page")
    var page : Int,
    var results : MutableList<Movie>? = null,
    var total_pages : Int,
    var total_results : Int,
)