package com.example.movielist

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movielist.`interface`.RetrofitServices
import com.example.movielist.adapter.MyMovieAdapter
import com.example.movielist.common.Common
import com.example.movielist.model.Movie
import com.example.movielist.model.TheatreMovies
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

    private val API_KEY = "4e5763a5269b4e3862f5a2eed7b9fb16"

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

        mService.getMovieList(API_KEY).enqueue(object : Callback<TheatreMovies> {
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