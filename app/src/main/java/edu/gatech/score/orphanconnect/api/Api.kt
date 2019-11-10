package edu.gatech.score.orphanconnect.api

import retrofit2.Call
import retrofit2.http.*
import edu.gatech.score.orphanconnect.api.HTTPMessage

import edu.gatech.score.orphanconnect.api.User
import edu.gatech.score.orphanconnect.api.Orphan


interface Api {
    @GET("/api/users/Read.php")
    fun getUser(@Query("email") email: String): Call<User>
    @GET("/api/orphans/Random.php")
    fun getOrphansRandom(@Query("number") number: Int): Call<List<Orphan>>
    @POST("/api/users/GetUsers.php")
    fun getUsers(): Call<List<User>>
    @POST("/api/users/CreateUser.php")
    fun signUp(@Query("email") email: String,
               @Query("password") password: String,
               @Query("first") first: String,
               @Query("last") last: String): Call<HTTPMessage>
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