package com.example.movielist.model.database

import androidx.annotation.WorkerThread
import com.example.movielist.model.entities.Movie
import kotlinx.coroutines.flow.Flow

class MovieRepository(private val movieDao: MovieDao) {
    @WorkerThread
    suspend fun insertMovieData(movies: List<Movie>) {
        movieDao.insertMovieList(movies)
    }

    val allMovieList: Flow<List<Movie>> = movieDao.getAllMovieList()

    val favoriteMovieList: Flow<List<Movie>> = movieDao.getFavoriteMovieList()
}