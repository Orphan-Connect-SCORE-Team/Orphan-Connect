package edu.gatech.score.orphanconnect.repository

import edu.gatech.score.orphanconnect.preferences.ScoreSharedPreferences
import edu.gatech.score.orphanconnect.web.responses.User
import edu.gatech.score.orphanconnect.web.webservice.UserWebservice
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepo(
        private val scoreSharedPreferences: ScoreSharedPreferences,
        private val userWebservice: UserWebservice
) {

    // Returns if it was successful
    suspend fun login(email: String, password: String): Boolean {
        var user: User? = null
        withContext(Dispatchers.IO) {
            try {
                println("Login began")

                val userResponse = userWebservice.getUser(email).execute()
                if (userResponse.isSuccessful) {
                    user = userResponse.body()
                    //TODO Implement the authentication here, check email, password, etc
                    //TODO Save user in SharedPreferences here
                    println("Email: ${user?.email}")
                    println("Password: ${user?.password}")
                    println("User is ${user?.first} ${user?.last}")
                } else {
                    println("User login failed: ${userResponse.message()}")
                }
            } catch (e: Exception) {
                println("Exception occurred: $e")
            }
        }
        return user?.password == password
    }
}
