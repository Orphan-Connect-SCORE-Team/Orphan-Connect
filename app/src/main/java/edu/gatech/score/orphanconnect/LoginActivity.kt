package edu.gatech.score.orphanconnect

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import edu.gatech.score.orphanconnect.api.TestApi
import edu.gatech.score.orphanconnect.api.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    companion object {
        const val TAG = "LoginActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val buttonLogin = findViewById<Button>(R.id.button_login)

        buttonLogin.setOnClickListener {
            this.userLogin("hello@gmail.com", "pass")
        }

        val buttonSignUp = findViewById<Button>(R.id.sign_up)


        buttonSignUp.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun userLogin(email: String, password: String) {
        val api = TestApi()

        try {
            val user = api.api!!.getUser(email)
            Log.i(TAG, "Login began")

            user.enqueue(object : Callback<User> {
                override fun onFailure(call: Call<User>, t: Throwable) {
                    Log.e(TAG, "Login failure: ${t.message}")
                }

                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        Log.i(TAG, "Login succeeded")

                        //TODO Implement the authentication here, check email, password, etc
                        //TODO Save user in SharedPreferences here
                        println(response.body()!!.email)
                        println(response.body()!!.password)

                        //Start main activity if login successful
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Log.e(TAG, "Unsuccessful: ${response.message()}")
                    }
                }
            })
        } catch (e: Exception) {
            Log.e(TAG, "Exception occurred: $e")
        }
    }

    fun onClick(view: View) {
        val intent = Intent(this@LoginActivity, ForgetPassword::class.java)
        startActivity(intent)
        finish()
    }
}
