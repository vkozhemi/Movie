package com.example.movielist.common

import com.example.movielist.`interface`.RetrofitServices
import com.example.movielist.retrofit.RetrofitClient

object Common {
    val BASE_URL : String = "https://api.themoviedb.org/3/"
    val retrofitServices : RetrofitServices?
        get() = RetrofitClient
            .getClient(BASE_URL)
            ?.create(RetrofitServices::class.java)
}