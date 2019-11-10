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
            OrphanDao.upsert(Orphan("2", "Joe", "Chen",23, "male", description = "I love soccer!", refugeeCamp = "Southern Refugee Camp", village = "Southern Village"))
            OrphanDao.upsert(Orphan("3", "Dylan", "Reese",20, "male", refugeeCamp = "Western Refugee Camp"))
            OrphanDao.upsert(Orphan("4", "Amaan", "Marfatia",20, "male", description = "I like basketball!", village = "Southern Village"))
            OrphanDao.upsert(Orphan("5", "Jiajie", "Lin",21, "male", description = "I like video games!"))
            OrphanDao.upsert(Orphan("6", "Joshua", "Mellott",23, "male", refugeeCamp = "Western Refugee Camp"))
        }
    }
}


