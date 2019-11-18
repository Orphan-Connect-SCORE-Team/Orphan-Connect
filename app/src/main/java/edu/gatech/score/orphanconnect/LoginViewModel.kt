package edu.gatech.score.orphanconnect

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import edu.gatech.score.orphanconnect.application.ScoreApplication
import edu.gatech.score.orphanconnect.repository.UserRepo
import javax.inject.Inject

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    @Inject
    lateinit var userRepo: UserRepo

    init {
        (application as ScoreApplication).appComponent.inject(this)
    }

    suspend fun login(email: String, password: String): Boolean {
        return userRepo.login(email, password)
    }
}
