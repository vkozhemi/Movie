package com.example.movielist.model.database

import androidx.room.*
import com.example.movielist.model.entities.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieList(movie: List<Movie>)

    @Query("SELECT * FROM all_movie_table ORDER BY ID")
    fun getAllMovieList(): Flow<List<Movie>>

    @Update
    suspend fun updateMovie(movie: Movie)

    @Query("SELECT * FROM all_movie_table WHERE favorite_movie = 1")
    fun getFavoriteMovieList(): Flow<List<Movie>>
}