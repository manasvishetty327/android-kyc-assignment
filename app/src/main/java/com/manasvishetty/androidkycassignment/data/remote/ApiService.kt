package com.manasvishetty.androidkycassignment.data.remote

import com.manasvishetty.androidkycassignment.data.model.IfscResponse
import com.manasvishetty.androidkycassignment.data.model.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("users")
    suspend fun getUsers(): UserResponse

    @GET("{ifsc}")
    suspend fun getBankDetails(
        @Path("ifsc") ifsc: String
    ): IfscResponse
}