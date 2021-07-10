package com.example.movielist.model

import com.google.gson.annotations.SerializedName

data class Movie(
//    @SerializedName("adult")
    var adult : Boolean,
    var backdrop_path : String? = null,
    var genre_ids : MutableList<Int>? = null,
    var id : Int,
    var original_language : String? = null,
    var original_title : String? = null,
    var overview : String? = null,
    var popularity : Double,
    var poster_path : String? = null,
    var release_date : String? = null,
    var title : String? = null,
    var video : Boolean,
    var vote_average : Double,
    var vote_count : Int,
)