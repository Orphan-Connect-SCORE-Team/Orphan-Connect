package edu.gatech.score.orphanconnect.dagger

import dagger.Module
import dagger.Provides
import edu.gatech.score.orphanconnect.database.dao.OrphanDao
import edu.gatech.score.orphanconnect.preferences.ScoreSharedPreferences
import edu.gatech.score.orphanconnect.repository.OrphanRepo
import edu.gatech.score.orphanconnect.repository.UserRepo
import edu.gatech.score.orphanconnect.web.webservice.OrphanWebservice
import edu.gatech.score.orphanconnect.web.webservice.UserWebservice
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideOrphanRepo(
            orphanDao: OrphanDao,
            orphanWebservice: OrphanWebservice
    ): OrphanRepo = OrphanRepo(
            orphanDao,
            orphanWebservice
    )

    @Provides
    @Singleton
    fun provideUserRepo(
            scoreSharedPreferences: ScoreSharedPreferences,
            userWebservice: UserWebservice
    ): UserRepo = UserRepo(
            scoreSharedPreferences,
            userWebservice
    )
}
