package com.example.score_coding_demo

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Response

class TestApi {

    private val api: Api?
    final val BASE_URL = "http://score.us-east-1.elasticbeanstalk.com/"

    init {
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        this.api = retrofit.create(Api::class.java)
    }

    //Note: JvmOverloads ensures kotlin optional parameters work in java files, remove when kotlin fixes this issue in the future

    //Signup with name
    @JvmOverloads fun signup(email: String, password: String, first: String? = "", last: String? = ""): HTTPMessage? {
        try {
            val signup = api!!.signup(email, password, first, last)
            System.out.println("Signup began")
            signup.enqueue(object: Callback<HTTPMessage> {
                override fun onFailure(call: Call<HTTPMessage>, t: Throwable) {
                    System.out.println("Failure "  + t.message)
                }

                override fun onResponse(call: Call<HTTPMessage>, response: Response<HTTPMessage>) {
                    if (response.isSuccessful) {
                        System.out.println("Succeeded")
                        System.out.println(response.body()!!.message)
                    } else {
                        System.out.println("Unsuccessful")
                        System.out.println(response.message())
                    }
                }
            })
        } catch (e: Exception) {
            System.out.println("Exception occurred: " + e.toString());
        }

        return null
    }

    //Create orphan
    @JvmOverloads fun createOrphan(firstName: String, lastName: String, age: Int, sex: String? = "",
                                   description: String? = "", photoUrl: String? = "", refugeeCamp: String? = "",
                                   village: String? = "", LGA: String? = "", country: String? = "",
                                   motherName: String? = "", fatherName: String? = ""): HTTPMessage? {
        try {
            val orphan = api!!.createOrphan(firstName, lastName, age, sex, description, photoUrl, refugeeCamp, village, LGA, country, motherName, fatherName)
            System.out.println("Orphan Creation Began")
            orphan.enqueue(object: Callback<HTTPMessage> {
                override fun onFailure(call: Call<HTTPMessage>, t: Throwable) {
                    System.out.println("Failure "  + t.message)
                }

                override fun onResponse(call: Call<HTTPMessage>, response: Response<HTTPMessage>) {
                    if (response.isSuccessful) {
                        System.out.println("Succeeded")
                        System.out.println(response.body()!!.message)
                    } else {
                        System.out.println("Unsuccessful")
                        System.out.println(response.message())
                    }
                }
            })
        } catch (e: Exception) {
            System.out.println("Exception occurred: " + e.toString());
        }

        return null
    }
}