package edu.gatech.score.orphanconnect.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import edu.gatech.score.orphanconnect.database.domain.Orphan

@Dao
interface OrphanDao {

    @Query("SELECT * from orphan")
    fun getAllOrphans(): LiveData<List<Orphan>>

    @Query("SELECT * from orphan WHERE id=:orphanId")
    fun getOrphan(orphanId: Int): LiveData<Orphan>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(orphan: Orphan)

    @Query("DELETE FROM orphan")
    suspend fun deleteAll()
}
