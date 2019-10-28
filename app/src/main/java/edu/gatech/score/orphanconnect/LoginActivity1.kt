package edu.gatech.score.orphanconnect

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Button

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import com.example.score_coding_demo.User
import com.example.score_coding_demo.TestApi

class LoginActivity1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val buttonLogin = findViewById<Button>(R.id.button_login)

        buttonLogin.setOnClickListener(View.OnClickListener {
            this.userLogin("hello@gmail.com", "pass")
        })

        val buttonSignUp = findViewById<Button>(R.id.sign_up)


        buttonSignUp.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@LoginActivity1, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        })
    }

    private fun userLogin(email: String, password: String) {
        val api = TestApi()

        try {
            val user = api.api!!.getUser(email)
            System.out.println("Login began")

            user.enqueue(object: Callback<User> {
                override fun onFailure(call: Call<User>, t: Throwable) {
                    System.out.println("Failure "  + t.message)
                }

                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        System.out.println("Succeeded")

                        //TODO Implement the authentication here, check email, password, etc
                        //TODO Save user in SharedPreferences here
                        System.out.println(response.body()!!.email)
                        System.out.println(response.body()!!.password)

                        //Start main activity if login successful
                        val intent = Intent(this@LoginActivity1, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        System.out.println("Unsuccessful")
                        System.out.println(response.message())
                    }
                }
            })
        } catch (e: Exception) {
            System.out.println("Exception occurred: " + e.toString());
        }
    }

    fun onClick(view: View) {
        val intent = Intent(this@LoginActivity1, ForgetPassword::class.java)
        startActivity(intent)
        finish()
    }
}
