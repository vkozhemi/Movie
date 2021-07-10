package com.example.movielist.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.movielist.LOG_TAG
import com.example.movielist.R
import com.example.movielist.model.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_layout.view.*

class MyMovieAdapter(private val context: Context, private val movieList: MutableList<Movie>)
    : RecyclerView.Adapter<MyMovieAdapter.MyViewHolder>(){

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val poster_path: ImageView = itemView.backdrop_path
        val original_title: TextView = itemView.original_title
        val overview: TextView = itemView.overview
        val vote_average: TextView = itemView.vote_average
        val like: Button = itemView.like
        val share: Button = itemView.share

        fun bind(listItem: Movie) {
            like.setOnClickListener {
                Toast.makeText(it.context, "Clicked on ${itemView.like.text}", Toast.LENGTH_LONG).show()
                Log.d(LOG_TAG, "like: $like")
            }
            share.setOnClickListener {
                Toast.makeText(it.context, "Clicked on ${itemView.share.text}", Toast.LENGTH_LONG).show()
                Log.d(LOG_TAG, "share: $share")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyMovieAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyMovieAdapter.MyViewHolder, position: Int) {
        Log.d(LOG_TAG, "onBindViewHolder")

        var listItem: Movie = movieList[position]
        holder.bind(listItem)
        Log.d(LOG_TAG, "listItem: $listItem")

        val path = "https://image.tmdb.org/t/p/w500" + movieList[position].poster_path
        Log.d(LOG_TAG, "path: $path")

        Picasso.get().load(path).into(holder.poster_path)

        holder.original_title.text = movieList[position].original_title
        holder.overview.text = movieList[position].overview
        holder.vote_average.text = movieList[position].vote_average.toString()
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}