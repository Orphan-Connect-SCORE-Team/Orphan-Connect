package edu.gatech.score.orphanconnect.web.webservice

import edu.gatech.score.orphanconnect.web.responses.HTTPMessage
import edu.gatech.score.orphanconnect.web.responses.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface UserWebservice {
    @GET("/api/users/Read.php")
    fun getUser(@Query("email") email: String): Call<User>

    @POST("/api/users/GetUsers.php")
    fun getUsers(): Call<List<User>>

    @POST("/api/users/CreateUser.php")
    fun signUp(
            @Query("email") email: String,
            @Query("password") password: String,
            @Query("first") first: String,
            @Query("last") last: String
    ): Call<HTTPMessage>

    @POST("/api/photos/CreatePhoto.php")
    fun createPhoto(
            @Query("link") link: String,
            @Query("id") id: Int,
            @Query("type") type: String
    ): Call<HTTPMessage>
}
