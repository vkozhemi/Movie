package com.example.movielist.application

import android.app.Application
import com.example.movielist.model.database.MovieRepository
import com.example.movielist.model.database.MovieRoomDatabase

class MovieApplication  : Application() {

    private val database by lazy { MovieRoomDatabase.getDatabase(this@MovieApplication) }

    val repository by lazy { MovieRepository(database.movieDao()) }
}