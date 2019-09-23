package edu.gatech.score.orphanconnect.repository

import androidx.lifecycle.LiveData
import edu.gatech.score.orphanconnect.database.dao.OrphanDao
import edu.gatech.score.orphanconnect.database.domain.Orphan

class OrphanRepo(private val orphanDao: OrphanDao) {

    fun allOrphans(): LiveData<List<Orphan>> = orphanDao.getAllOrphans()

    fun getOrphan(orphanId: Int): LiveData<Orphan> = orphanDao.getOrphan(orphanId)

    suspend fun upsert(orphan: Orphan) = orphanDao.upsert(orphan)
}
