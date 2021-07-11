package com.example.movielist.model.network

import android.util.Log
import com.example.movielist.utils.Constants.LOG_TAG
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var retrofit : Retrofit? = null

    fun getClient(baseUrl: String) : Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        Log.d(LOG_TAG, "retrofit = $retrofit")
        return retrofit!!
    }
}