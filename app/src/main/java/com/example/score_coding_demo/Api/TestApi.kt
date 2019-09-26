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

    fun getUser(email: String): User?{
        try {
            val request = api!!.getUser(email)
            request.enqueue(object: Callback<User> {
                override fun onFailure(call: Call<User>, t: Throwable) {
                    System.out.println("Failure "  + t.message)
                }

                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        System.out.println("Success " + response.body()!!.password)
                    } else {
                        System.out.println("Empty response")
                    }
                }
            })
        } catch (e: Exception) {
            System.out.println("Exception occurred " + e.toString())
        }

        return null
    }

    fun signup(email: String, password: String): User? {
        try {
            var userExists = false
            val request = api!!.getUser(email)
            request.enqueue(object: Callback<User> {
                override fun onFailure(call: Call<User>, t: Throwable) {
                    System.out.println("Failure "  + t.message)
                }

                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        userExists = true
                    }
                }
            })

            if (!userExists) {
                val signup = api!!.signup(email, password)
                signup.enqueue(object: Callback<User> {
                    override fun onFailure(call: Call<User>, t: Throwable) {
                        System.out.println("Failure "  + t.message)
                    }

                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        if (response.isSuccessful) {
                            System.out.println("Succeeded")
                        }
                    }
                })
            }
        } catch (e: Exception) {
            System.out.println("Exception occurred " + e.toString())
        }

        return null
    }
}