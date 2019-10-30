package edu.gatech.score.orphanconnect

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.score_coding_demo.HTTPMessage

import com.example.score_coding_demo.TestApi
import com.example.score_coding_demo.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignUpActivity : AppCompatActivity() {


    private var firstnameField: EditText? = null
    private var lastnameField: EditText? = null
    private var emailField: EditText? = null
    private var passwordField: EditText? = null
    private var confirmPasswordField: EditText? = null
    private val users: List<User>? = null
    var checkEmailRepeated : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        firstnameField = findViewById(R.id.firstname_SU)
        lastnameField = findViewById(R.id.lastname_SU)
        emailField = findViewById(R.id.email_SU)
        passwordField = findViewById(R.id.password_SU)
        confirmPasswordField = findViewById(R.id.confirm_password_SU)

        val buttonSignUp = findViewById<Button>(R.id.sign_up)


        buttonSignUp.setOnClickListener(View.OnClickListener {

            val firstname = firstnameField!!.text.toString()
            val lastname = lastnameField!!.text.toString()
            val email = emailField!!.text.toString()
            val password = passwordField!!.text.toString()
            val confirmPassword = confirmPasswordField!!.text.toString()
            if (email == "" || password == "" || firstname == "" || firstname == "" || confirmPassword == "") {
                Toast.makeText(this@SignUpActivity, "You need to enter your basic account information.",
                        Toast.LENGTH_SHORT).show()
            } else if (password != confirmPassword) {
                Toast.makeText(this@SignUpActivity, "The two passwords you entered don't match.",
                        Toast.LENGTH_SHORT).show()
            } else if (!email.contains("@")) {
                Toast.makeText(this@SignUpActivity, "The email format doesn't right, it should contain @.",
                        Toast.LENGTH_SHORT).show()
            } else if (password.length < 6 || confirmPassword.length < 6) {
                Toast.makeText(this@SignUpActivity, "The length of passwords should longer than 6 characters.",
                        Toast.LENGTH_SHORT).show()
            } else {
                signup(email,password,firstname,lastname)

            }
        })
    }

    fun signup(email: String, password: String, first: String, last: String) {
        val api = TestApi()
        try {

            val signup = api.api!!.signUp(email, password, first, last)
            System.out.println("Signup began")
            signup.enqueue(object: Callback<HTTPMessage> {
                override fun onFailure(call: Call<HTTPMessage>, t: Throwable) {
                    System.out.println("Failure "  + t.message)
                }

                override fun onResponse(call: Call<HTTPMessage>, response: Response<HTTPMessage>) {
                    if (response.isSuccessful) {
                        checkEmailRepeated = true
                        System.out.println("Succeeded")
                        System.out.println(response.body()!!.message)
                        val intent = Intent(this@SignUpActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()

                    } else {
                        System.out.println("Unsuccessful")
                        System.out.println(response.message())
                        Toast.makeText(this@SignUpActivity, "The e-mail has already been signed up",
                            Toast.LENGTH_SHORT).show()

                    }
                }
            })
        } catch (e: Exception) {
            System.out.println("Exception occurred: " + e.toString());
        }
    }


    fun onClick(view: View) {

        val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }


}

