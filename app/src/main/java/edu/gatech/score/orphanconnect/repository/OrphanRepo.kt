package edu.gatech.score.orphanconnect.repository

import androidx.lifecycle.LiveData
import edu.gatech.score.orphanconnect.database.dao.OrphanDao
import edu.gatech.score.orphanconnect.database.domain.Orphan
import edu.gatech.score.orphanconnect.web.webservice.OrphanWebservice
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class OrphanRepo(
        private val orphanDao: OrphanDao,
        private val orphanWebservice: OrphanWebservice
) {

    fun allOrphans(): LiveData<List<Orphan>> = orphanDao.getAllOrphans()

    fun getOrphan(orphanId: Int): LiveData<Orphan> = orphanDao.getOrphan(orphanId)

    suspend fun upsert(orphan: Orphan) = orphanDao.upsert(orphan)

    suspend fun updateOrphansFromWeb() {
        withContext(Dispatchers.IO) {
            val orphansResponse = orphanWebservice.getOrphansRandom(50).execute()
            if (orphansResponse.isSuccessful) {
                val newOrphans = orphansResponse.body()
                if (!newOrphans.isNullOrEmpty()) {
                    orphanDao.replaceAll(newOrphans)
                }
            }
        }
    }
}
