package edu.gatech.score.orphanconnect.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import edu.gatech.score.orphanconnect.database.dao.OrphanDao
import edu.gatech.score.orphanconnect.database.domain.Orphan

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = [Orphan::class], version = 1)
abstract class ScoreDatabase : RoomDatabase() {

    abstract fun orphanDao(): OrphanDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
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
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
