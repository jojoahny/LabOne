package com.example.labone

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Apiinterface {
        @GET("users") // Replace "endpoint" with your actual endpoint
        fun getUsers():Call<UsersResponse>
        @GET("users")
        fun getUsersById(@Query("username") username: String):Call<UsersResponse>
}