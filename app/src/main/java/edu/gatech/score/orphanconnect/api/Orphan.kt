package edu.gatech.score.orphanconnect.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Orphan (
    @SerializedName("firstName")
    @Expose
    val firstName: String,
    @SerializedName("lastName")
    @Expose
    val lastName: String,
    @SerializedName("age")
    @Expose
    val age: Int,
    @SerializedName("sex")
    @Expose
    val sex: String,
    @SerializedName("description")
    @Expose
    val description: String,
    @SerializedName("photoUrl")
    @Expose
    val photoUrl: String,
    @SerializedName("refugeeCamp")
    @Expose
    val refugeeCamp: String,
    @SerializedName("village")
    @Expose
    val village: String,
    @SerializedName("LGA")
    @Expose
    val LGA: String,
    @SerializedName("country")
    @Expose
    val country: String,
    @SerializedName("motherName")
    @Expose
    val motherName: String,
    @SerializedName("fatherName")
    @Expose
    val fatherName: String,
    @SerializedName("id")
    @Expose
    val id: Int
)