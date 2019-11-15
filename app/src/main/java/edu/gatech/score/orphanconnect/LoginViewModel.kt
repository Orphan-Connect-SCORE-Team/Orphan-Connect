package edu.gatech.score.orphanconnect

import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import edu.gatech.score.orphanconnect.api.TestApi
import edu.gatech.score.orphanconnect.application.ScoreApplication
import edu.gatech.score.orphanconnect.database.dao.OrphanDao
import edu.gatech.score.orphanconnect.database.domain.Orphan
import edu.gatech.score.orphanconnect.repository.OrphanRepo
import edu.gatech.score.orphanconnect.repository.UserRepo
import edu.gatech.score.orphanconnect.web.responses.User
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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
