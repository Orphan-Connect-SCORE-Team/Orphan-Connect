package edu.gatech.score.orphanconnect

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {
    private var inputEmail: EditText? = null
    private var inputPassword: EditText? = null
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        inputEmail = findViewById(R.id.input_email)
        inputPassword = findViewById(R.id.input_password)

        val buttonLogin = findViewById<Button>(R.id.button_login)

        buttonLogin.setOnClickListener {
            val email = inputEmail!!.text.toString()
            val password = inputPassword!!.text.toString()

            if (email == "" || password == "") run {
                Toast.makeText(this@LoginActivity, "You need to input your email and password to login.", Toast.LENGTH_SHORT).show()
            } else if (password.length < 6) run {
                Toast.makeText(this@LoginActivity, "password length should >= 6", Toast.LENGTH_SHORT).show()
            } else run {
                this.userLogin(email, password)
            }
        }

        val buttonSignUp = findViewById<Button>(R.id.sign_up)


        buttonSignUp.setOnClickListener {

            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun userLogin(email: String, password: String) {
        GlobalScope.launch {
            if (viewModel.login(email, password)) {
                withContext(Dispatchers.Main) {
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
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
