package edu.gatech.score.orphanconnect.dagger

import dagger.Module
import dagger.Provides
import edu.gatech.score.orphanconnect.database.dao.OrphanDao
import edu.gatech.score.orphanconnect.repository.OrphanRepo
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideOrphanRepo(orphanDao: OrphanDao): OrphanRepo {
        return OrphanRepo(orphanDao)
    }
}
