package com.example.score_coding_demo

import retrofit2.Call
import retrofit2.http.*

interface Api {
    @GET("Login.php")
    fun getUser(@Query("email") email: String): Call<User>
    @POST("Signup.php")
    fun signup(@Query("email") email: String,
               @Query("password") password: String): Call<User>
}