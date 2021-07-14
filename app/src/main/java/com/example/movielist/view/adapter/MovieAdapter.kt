package com.example.movielist.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.movielist.databinding.ItemLayoutBinding
import com.example.movielist.model.entities.Movie
import com.example.movielist.utils.Constants.IMAGE_BASE_URL
import com.example.movielist.utils.Constants.LOG_TAG
import com.example.movielist.view.fragments.FavoriteMovieFragment
import com.example.movielist.view.fragments.MovieFragment
import com.squareup.picasso.Picasso

class MovieAdapter(private val fragment : Fragment) : RecyclerView.Adapter<MovieAdapter.ViewHolder>(){

    private var movieList: List<Movie> = listOf()

    fun movieList(list: List<Movie>) {
        movieList = list
        notifyDataSetChanged()
    }

    class ViewHolder(view: ItemLayoutBinding) : RecyclerView.ViewHolder(view.root) {
        val poster_path: ImageView = view.backdropPath
        val original_title: TextView = view.originalTitle
        val overview: TextView = view.overview
        val vote_average: TextView = view.voteAverage
        val like: Button = view.like
        val share: Button = view.share
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding : ItemLayoutBinding = ItemLayoutBinding.inflate(
            LayoutInflater.from(fragment.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d(LOG_TAG, "onBindViewHolder")

        val listItem: Movie = movieList[position]

        val path = IMAGE_BASE_URL + movieList[position].poster_path
        Log.d(LOG_TAG, "path: $path")

        Picasso.get().load(path).into(holder.poster_path)
        holder.original_title.text = movieList[position].original_title
        holder.overview.text = movieList[position].overview
        holder.vote_average.text = movieList[position].vote_average.toString()

        holder.like.setOnClickListener {
            Toast.makeText(it.context, "Clicked on ${holder.like.text}", Toast.LENGTH_LONG).show()
            Log.d(LOG_TAG, "LIKE!")
            if (fragment is MovieFragment) {
                fragment.updateMovie()
                Log.d(LOG_TAG, "MovieFragment!")
            } else if (fragment is FavoriteMovieFragment) {
                Log.d(LOG_TAG, "FavoriteMovieFragment!")
            }
        }

        holder.share.setOnClickListener {
            Toast.makeText(it.context, "Clicked on ${holder.share.text}", Toast.LENGTH_LONG).show()
            Log.d(LOG_TAG, "SHARE!")
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

}