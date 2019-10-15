package edu.gatech.score.orphanconnect.dagger

import android.app.Application
import dagger.Module
import dagger.Provides
import edu.gatech.score.orphanconnect.database.ScoreDatabase
import edu.gatech.score.orphanconnect.database.dao.OrphanDao
import javax.inject.Singleton


@Module
class DatabaseModule(application: Application) {
    private val db: ScoreDatabase = ScoreDatabase.getDatabase(application)

    @Provides
    @Singleton
    fun provideDatabase(): ScoreDatabase {
        return db
    }

    @Provides
    @Singleton
    fun provideOrphanDao(): OrphanDao {
        return db.orphanDao()
    }
}
