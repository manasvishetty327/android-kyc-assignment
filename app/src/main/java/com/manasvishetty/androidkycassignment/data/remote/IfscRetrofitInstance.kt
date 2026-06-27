package com.manasvishetty.androidkycassignment.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object IfscRetrofitInstance {

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://ifsc.razorpay.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}