package edu.gatech.score.orphanconnect.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class HTTPMessage (
        @SerializedName("message")
        @Expose
        val message: String
)