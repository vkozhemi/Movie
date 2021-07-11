package com.example.movielist.model.network

import com.example.movielist.utils.Constants.BASE_URL

object Common {
    val retrofitServices : RetrofitServices?
        get() = RetrofitClient
            .getClient(BASE_URL)
            ?.create(RetrofitServices::class.java)
}