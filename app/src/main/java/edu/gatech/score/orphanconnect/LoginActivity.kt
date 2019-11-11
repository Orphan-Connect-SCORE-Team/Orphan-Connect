package edu.gatech.score.orphanconnect

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import edu.gatech.score.orphanconnect.api.TestApi
import edu.gatech.score.orphanconnect.api.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private var inputEmail: EditText? = null
    private var inputPassword: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        inputEmail = findViewById(R.id.input_email)
        inputPassword = findViewById(R.id.input_password)

        val buttonLogin = findViewById<Button>(R.id.button_login)

        buttonLogin.setOnClickListener(View.OnClickListener {
            val email = inputEmail!!.getText().toString()
            val password = inputPassword!!.getText().toString()

            if (email == "" || password == "") run {
                Toast.makeText(this@LoginActivity, "You need to input your email and password to login.", Toast.LENGTH_SHORT).show()
            } else if (password.length < 6) run {
                Toast.makeText(this@LoginActivity, "password length should >= 6", Toast.LENGTH_SHORT).show()
            } else run {
                this.userLogin(email, password)
            }
        })

        val buttonSignUp = findViewById<Button>(R.id.sign_up)


        buttonSignUp.setOnClickListener(View.OnClickListener {

            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        })
    }

    private fun userLogin(email: String, password: String) {
        val api = TestApi()

        try {
            val user = api.api!!.getUser(email)
            System.out.println("Login began")

            user.enqueue(object : Callback<User> {
                override fun onFailure(call: Call<User>, t: Throwable) {
                    System.out.println("Failure " + t.message)
                }

                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        System.out.println("Succeeded")

                        //TODO Implement the authentication here, check email, password, etc
                        //TODO Save user in SharedPreferences here
                        System.out.println(response.body()!!.email)
                        System.out.println(response.body()!!.password)

                        //Start main activity if login successful
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
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

        this.userLogin("obreese@gatech.edu", "password")
/*
        val intent = Intent(this@LoginActivity, ForgetPassword::class.java)
        startActivity(intent)
        finish()*/
    }
}
