package com.example.movielist.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "all_movie_table")
data class Movie(
//    @SerializedName("adult")

    @PrimaryKey(autoGenerate = true)  var id : Int,
    var original_title : String? = null,
    var overview : String? = null,
    var poster_path : String? = null,
    var vote_average : Double,
    var favorite_movie : Int,

    var adult : Boolean,
    var backdrop_path : String? = null,
    var original_language : String? = null,
    var popularity : Double,
    var release_date : String? = null,
    var title : String? = null,
    var video : Boolean,
    var vote_count : Int
//    var genre_ids : MutableList<Int>? = null,
)