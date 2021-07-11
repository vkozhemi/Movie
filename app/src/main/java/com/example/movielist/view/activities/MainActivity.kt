package com.example.movielist.view.activities

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movielist.utils.Constants.API_KEY_VALUE
import com.example.movielist.utils.Constants.LOG_TAG
import com.example.movielist.R
import com.example.movielist.model.network.RetrofitServices
import com.example.movielist.view.adapter.MyMovieAdapter
import com.example.movielist.model.network.Common
import com.example.movielist.model.entities.Movie
import com.example.movielist.model.entities.TheatreMovies
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    lateinit var mService: RetrofitServices
    lateinit var mLinearLayoutManager: LinearLayoutManager
    lateinit var mMyMovieAdapter: MyMovieAdapter
    lateinit var mAlertDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(LOG_TAG, "onCreate")

        mService = Common.retrofitServices!!
        recyclerMovieList.setHasFixedSize(true)
        mLinearLayoutManager = LinearLayoutManager(this)
        recyclerMovieList.layoutManager = mLinearLayoutManager
        mAlertDialog = SpotsDialog.Builder().setCancelable(true).setContext(this).build()

        getAllMovieList()
    }

    private fun getAllMovieList() {
        mAlertDialog.show()
        Log.d(LOG_TAG, "getAllMovieList")

        mService.getMovieList(API_KEY_VALUE).enqueue(object : Callback<TheatreMovies> {
            override fun onResponse(call: Call<TheatreMovies>, response: Response<TheatreMovies>) {
                Log.i(LOG_TAG, "Response: $response")
                mMyMovieAdapter = MyMovieAdapter(baseContext, response.body()?.results as MutableList<Movie>)
                mMyMovieAdapter.notifyDataSetChanged()
                recyclerMovieList.adapter = mMyMovieAdapter
                mAlertDialog.dismiss()
            }

            override fun onFailure(call: Call<TheatreMovies>, t: Throwable) {
                Log.e(LOG_TAG, "Throwable: $t")
            }
        })
    }
}