package com.example.score_coding_demo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class User (
    @SerializedName("user_id")
    @Expose
    val id: Int,
    @SerializedName("email")
    @Expose
    val email: String,
    @SerializedName("password")
    @Expose
    val password: String,
    @SerializedName("first")
    @Expose
    val first: String,
    @SerializedName("last")
    @Expose
    val last: String
)