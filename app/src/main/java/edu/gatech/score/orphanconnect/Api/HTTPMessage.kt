package com.example.score_coding_demo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class HTTPMessage (
        @SerializedName("message")
        @Expose
        val message: String
)