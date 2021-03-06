package com.example.movielist.model.entities

import com.example.movielist.model.entities.Movie

data class TheatreMovies(
//    @SerializedName("page")
    var page : Int,
    var results : MutableList<Movie>? = null,
    var total_pages : Int,
    var total_results : Int,
)