package edu.gatech.score.orphanconnect

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import edu.gatech.score.orphanconnect.application.ScoreApplication
import edu.gatech.score.orphanconnect.database.dao.OrphanDao
import edu.gatech.score.orphanconnect.database.domain.Orphan
import edu.gatech.score.orphanconnect.repository.OrphanRepo
import kotlinx.coroutines.launch
import javax.inject.Inject

class OrphanViewModel(application: Application) : AndroidViewModel(application) {

    @Inject
    lateinit var orphanRepo: OrphanRepo

    val allOrphans: LiveData<List<Orphan>>

    init {
        (application as ScoreApplication).appComponent.inject(this)

        allOrphans = orphanRepo.allOrphans()
    }
}