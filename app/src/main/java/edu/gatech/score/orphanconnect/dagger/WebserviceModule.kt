package edu.gatech.score.orphanconnect.dagger

import android.app.Application
import dagger.Module
import dagger.Provides
import edu.gatech.score.orphanconnect.web.webservice.OrphanWebservice
import edu.gatech.score.orphanconnect.web.webservice.UserWebservice
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
class WebserviceModule(application: Application) {

    @Provides
    @Singleton
    fun provideUserWebservice(retrofit: Retrofit): UserWebservice =
            retrofit.create(UserWebservice::class.java)

    @Provides
    @Singleton
    fun provideOrphanWebservice(retrofit: Retrofit): OrphanWebservice =
            retrofit.create(OrphanWebservice::class.java)
}
