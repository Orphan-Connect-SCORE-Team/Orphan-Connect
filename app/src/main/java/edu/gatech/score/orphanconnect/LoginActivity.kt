package edu.gatech.score.orphanconnect

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        button_login.setOnClickListener {
            val email = input_email.text.toString()
            val password = input_password.text.toString()

            if (email == "" || password == "") run {
                Toast.makeText(this@LoginActivity, "You need to input your email and password to login.", Toast.LENGTH_SHORT).show()
            } else if (password.length < 6) run {
                Toast.makeText(this@LoginActivity, "password length should >= 6", Toast.LENGTH_SHORT).show()
            } else run {
                this.userLogin(email, password)
            }
        }

        sign_up.setOnClickListener {
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
