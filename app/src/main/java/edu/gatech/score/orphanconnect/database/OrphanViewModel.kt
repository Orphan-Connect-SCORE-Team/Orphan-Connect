package edu.gatech.score.orphanconnect.database

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

// Class extends AndroidViewModel and requires application as a parameter.
class OrphanViewModel(application: Application) : AndroidViewModel(application) {

    // The ViewModel maintains a reference to the repository to get data.
    @Inject
    private lateinit var orphanRepo: OrphanRepo
    // LiveData gives us updated orphans when they change.
    val allOrphans: LiveData<List<Orphan>>

    init {
        (application as ScoreApplication).appComponent.inject(this)
        // Gets reference to OrphanDao from ScoreDatabase to construct
        // the correct OrphanRepo

        allOrphans = orphanRepo.allOrphans()
    }

    fun insert(word: Orphan) = viewModelScope.launch {
        orphanRepo.insert(word)
    }
}