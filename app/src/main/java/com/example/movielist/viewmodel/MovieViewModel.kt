package com.example.movielist.viewmodel

import com.example.movielist.model.database.MovieRepository
import com.example.movielist.model.entities.Movie
import kotlinx.coroutines.launch
import androidx.lifecycle.*


class MovieViewModel(private val repository: MovieRepository) : ViewModel() {

    fun insert(movies: List<Movie>) = viewModelScope.launch {
        repository.insertMovieData(movies)
    }

    val allMovieList: LiveData<List<Movie>> = repository.allMovieList.asLiveData()

    fun update(movie: Movie) = viewModelScope.launch {
        repository.updateMovie(movie)
    }
    val favoriteMovie: LiveData<List<Movie>> = repository.favoriteMovieList.asLiveData()
}

class MovieViewModelFactory(private val repository: MovieRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MovieViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}