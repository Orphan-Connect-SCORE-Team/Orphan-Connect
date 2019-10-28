package com.example.score_coding_demo

import retrofit2.Call
import retrofit2.http.*
import com.example.score_coding_demo.HTTPMessage;

interface Api {
    @GET("/api/users/Read.php")
    fun getUser(@Query("email") email: String): Call<User>
    @POST("/api/users/GetUsers.php")
    fun getUsers(): Call<List<User>>
    @POST("/api/users/CreateUser.php")
    fun signup(@Query("email") email: String,
               @Query("password") password: String,
               @Query("first") first: String?,
               @Query("last") last: String?): Call<HTTPMessage>
    @POST("/api/orphans/CreateOrphan.php")
    fun createOrphan(@Query("firstName") firstName: String,
                     @Query("lastName") lastName: String,
                     @Query("age") age: Int,
                     @Query("sex") sex: String?,
                     @Query("description") description: String?,
                     @Query("photoUrl") photoUrl: String?,
                     @Query("refugeeCamp") refugeeCamp: String?,
                     @Query("village") village: String?,
                     @Query("LGA") LGA: String?,
                     @Query("country") country: String?,
                     @Query("motherName") motherName: String?,
                     @Query("fatherName") fatherName: String?): Call<HTTPMessage>
    @POST("/api/photos/CreatePhoto.php")
    fun createPhoto(@Query("link") link: String,
                    @Query("id") id: Int,
                    @Query("type") type: String): Call<HTTPMessage>
}