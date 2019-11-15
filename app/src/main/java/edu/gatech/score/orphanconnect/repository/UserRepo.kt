package edu.gatech.score.orphanconnect.repository

import edu.gatech.score.orphanconnect.web.webservice.UserWebservice
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepo(
        private val userWebservice: UserWebservice
) {

    // Returns if it was successful
    suspend fun login(email: String, password: String): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                println("Login began")

                val userResponse = userWebservice.getUser(email).execute()
                if (userResponse.isSuccessful) {
                    val user = userResponse.body()
                    //TODO Implement the authentication here, check email, password, etc
                    //TODO Save user in SharedPreferences here
                    println("Email: ${user?.email}")
                    println("Password: ${user?.password}")
                    user != null
                } else {
                    println("User login failed: ${userResponse.message()}")
                }
            } catch (e: Exception) {
                println("Exception occurred: $e")
            }
            false
        }
    }
}
