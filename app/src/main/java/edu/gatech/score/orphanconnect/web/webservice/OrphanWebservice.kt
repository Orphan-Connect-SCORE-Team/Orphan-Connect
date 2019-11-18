package edu.gatech.score.orphanconnect.web.webservice

import edu.gatech.score.orphanconnect.database.domain.Orphan
import edu.gatech.score.orphanconnect.web.responses.HTTPMessage
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface OrphanWebservice {

    @GET("/api/orphans/Random.php")
    fun getOrphansRandom(@Query("number") number: Int): Call<List<Orphan>>

    @POST("/api/orphans/CreateOrphan.php")
    fun createOrphan(
            @Query("firstName") firstName: String,
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
            @Query("fatherName"
            ) fatherName: String?): Call<HTTPMessage>
}
