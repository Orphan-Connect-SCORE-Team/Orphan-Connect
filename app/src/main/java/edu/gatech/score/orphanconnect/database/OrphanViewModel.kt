package edu.gatech.score.orphanconnect.database

// Class extends AndroidViewModel and requires application as a parameter.
class OrphanViewModel(application: Application) : AndroidViewModel(application) {

    // The ViewModel maintains a reference to the repository to get data.
    private val repository: OrphanRepo
    // LiveData gives us updated orphans when they change.
    val allOrphans: LiveData<List<Orphan>>

    init {
        // Gets reference to OrphanDao from ScoreDatabase to construct
        // the correct OrphanRepo
        val OrphanDao = ScoreDatabase.getDatabase(application).OrphanDao()
        repository = OrphanRepo(OrphanDao)
        allOrphans = repository.allOrphans
    }

    fun insert(word: Orphan) = viewModelScope.launch {
        repository.insert(word)
    }
}