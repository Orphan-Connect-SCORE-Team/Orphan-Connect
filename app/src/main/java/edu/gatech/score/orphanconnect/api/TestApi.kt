package edu.gatech.score.orphanconnect.api

import com.example.score_coding_demo.Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class TestApi {

    val api: Api?
    val BASE_URL = "http://score.us-east-1.elasticbeanstalk.com/"

    init {
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        this.api = retrofit.create(Api::class.java)
    }

    //Note: JvmOverloads ensures kotlin optional parameters work in java files, remove when kotlin fixes this issue in the future
/*
    fun getUser(email: String) {
        try {
            val user = api!!.getUser(email)
            println("Login began")

            user.enqueue(object: Callback<User> {
                override fun onFailure(call: Call<User>, t: Throwable) {
                    println("Failure "  + t.message)
                }

                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        println("Succeeded")
                        //Handle login through response.body!!
                    } else {
                        println("Unsuccessful")
                        println(response.message())
                    }
                }
            })
        } catch (e: Exception) {
            println("Exception occurred: " + e.toString());
        }
    }
*/
    fun getUsers(): List<User>? {
        var retVal: List<User>? = null
        try {
            val users = api!!.getUsers()
            println("Getting users")

            users.enqueue(object : Callback<List<User>> {
                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    println("Failure " + t.message)
                }

                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    if (response.isSuccessful) {
                        println("Succeeded")
                        println("Total users: " + response.body()!!.count())
                        retVal = response.body()!!
                    } else {
                        println("Unsuccessful")
                        println(response.message())
                    }
                }
            })
        } catch (e: Exception) {
            println("getUsers exception occurred: $e")
        }

        return retVal
    }

    //Signup with name
    @JvmOverloads
    fun signup(email: String, password: String, first: String? = "", last: String? = ""): HTTPMessage? {
        try {
            val signup = api!!.signup(email, password, first, last)
            println("Signup began")
            signup.enqueue(object : Callback<HTTPMessage> {
                override fun onFailure(call: Call<HTTPMessage>, t: Throwable) {
                    println("Failure " + t.message)
                }

                override fun onResponse(call: Call<HTTPMessage>, response: Response<HTTPMessage>) {
                    if (response.isSuccessful) {
                        println("Succeeded")
                        println(response.body()!!.message)
                    } else {
                        println("Unsuccessful")
                        println(response.message())
                    }
                }
            })
        } catch (e: Exception) {
            println("Exception occurred: $e")
        }

        return null
    }

    //Create orphan
    @JvmOverloads
    fun createOrphan(firstName: String, lastName: String, age: Int, sex: String? = "",
                     description: String? = "", photoUrl: String? = "", refugeeCamp: String? = "",
                     village: String? = "", LGA: String? = "", country: String? = "",
                     motherName: String? = "", fatherName: String? = ""): HTTPMessage? {
        try {
            val orphan = api!!.createOrphan(firstName, lastName, age, sex, description, photoUrl, refugeeCamp, village, LGA, country, motherName, fatherName)
            println("Orphan Creation Began")
            orphan.enqueue(object : Callback<HTTPMessage> {
                override fun onFailure(call: Call<HTTPMessage>, t: Throwable) {
                    println("Failure " + t.message)
                }

                override fun onResponse(call: Call<HTTPMessage>, response: Response<HTTPMessage>) {
                    if (response.isSuccessful) {
                        println("Succeeded")
                        println(response.body()!!.message)
                    } else {
                        println("Unsuccessful")
                        println(response.message())
                    }
                }
            })
        } catch (e: Exception) {
            println("Exception occurred: $e")
        }

        return null
    }

    fun getOrphans(number: Int): List<Orphan>? {
        var retVal: List<Orphan>? = null
        try {
            val orphans = api!!.getOrphansRandom(number)
            println("Getting orphans")

            orphans.enqueue(object : Callback<List<Orphan>> {
                override fun onFailure(call: Call<List<Orphan>>, t: Throwable) {
                    println("Failure " + t.message)
                }

                override fun onResponse(call: Call<List<Orphan>>, response: Response<List<Orphan>>) {
                    if (response.isSuccessful) {
                        println("Succeeded")
                        println("Total orphans: " + response.body()!!.count())

                        //println("Name 1: " + response.body()!![0].firstName)
                        //println("Name 2: " + response.body()!![1].firstName)
                        retVal = response.body()!!
                    } else {
                        println("Unsuccessful")
                        println(response.message())
                    }
                }
            })
        } catch (e: Exception) {
            println("getOrphans exception occurred: $e")
        }

        return retVal
    }
}