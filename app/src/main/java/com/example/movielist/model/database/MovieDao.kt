package com.example.movielist.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.movielist.model.entities.Movie
import androidx.room.Query;
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieList(movie: List<Movie>)

    @Query("SELECT * FROM all_movie_table ORDER BY ID")
    fun getAllMovieList(): Flow<List<Movie>>

    @Query("SELECT * FROM all_movie_table WHERE favorite_movie = 1")
    fun getFavoriteMovieList(): Flow<List<Movie>>

}