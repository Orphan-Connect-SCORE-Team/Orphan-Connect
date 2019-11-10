package edu.gatech.score.orphanconnect.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import edu.gatech.score.orphanconnect.database.dao.OrphanDao
import edu.gatech.score.orphanconnect.database.domain.Orphan
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Database(entities = [Orphan::class], version = 1)
abstract class ScoreDatabase : RoomDatabase() {

    abstract fun orphanDao(): OrphanDao

    companion object {
        @Volatile
        private var INSTANCE: ScoreDatabase? = null

        fun getDatabase(context: Context): ScoreDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = databaseBuilder(
                        context.applicationContext,
                        ScoreDatabase::class.java,
                        "database"
                ).addCallback(OprhanDatabaseCallback(GlobalScope)).build()
                INSTANCE = instance
                return instance
            }
        }
    }

    private class OprhanDatabaseCallback(
            private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.orphanDao())
                }
            }
        }

        suspend fun populateDatabase(OrphanDao: OrphanDao) {
            OrphanDao.deleteAll()

            // Add sample orphans
            OrphanDao.upsert(Orphan("1", "Maddie", "Wiggins",22, "female"))
            OrphanDao.upsert(Orphan("2", "First1", "Last1",1, "male", description = "I like to eat pizza", refugeeCamp = "Refugee Camp 1", village = "Village 1"))
            OrphanDao.upsert(Orphan("3", "First2", "Last2",2, "female", refugeeCamp = "Refugee Camp 2"))
            OrphanDao.upsert(Orphan("4", "First3", "Last3",3, "male", description = "I like stuff", village = "Village 1"))
            OrphanDao.upsert(Orphan("5", "First4", "Last4",100, "female", description = "I like games and a whole lot of other things that can make this text overfloow sooooooooooooo much a lot I like games and a whole lot of other things that can make this text overfloow sooooooooooooo much a lot I like games and a whole lot of other things that can make this text overfloow sooooooooooooo much a lot "))
            OrphanDao.upsert(Orphan("6", "First5", "Last5",7, "female", refugeeCamp = "Refugee Camp 2"))
        }
    }
}


