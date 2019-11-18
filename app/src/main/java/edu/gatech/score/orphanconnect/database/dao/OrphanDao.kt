package edu.gatech.score.orphanconnect.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import edu.gatech.score.orphanconnect.database.domain.Orphan

@Dao
interface OrphanDao {

    @Query("SELECT * from orphan")
    fun getAllOrphans(): LiveData<List<Orphan>>

    @Query("SELECT * from orphan WHERE id=:orphanId")
    fun getOrphan(orphanId: Int): LiveData<Orphan>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(orphan: Orphan)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(orphans: List<Orphan>)

    @Query("DELETE FROM orphan")
    suspend fun deleteAll()

    @Transaction
    suspend fun replaceAll(orphans: List<Orphan>) {
        deleteAll()
        upsertAll(orphans)
    }
}
