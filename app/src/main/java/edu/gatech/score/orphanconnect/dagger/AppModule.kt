package edu.gatech.score.orphanconnect.dagger

import android.app.Application
import android.content.ContentProvider
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import dagger.Module
import javax.inject.Singleton
import dagger.Provides
import edu.gatech.score.orphanconnect.preferences.ScoreSharedPreferences
import edu.gatech.score.orphanconnect.preferences.ScoreSharedPreferencesImpl


@Module
class AppModule(private val application: Application) {

    @Provides
    @Singleton
    internal fun providesContext(): Context {
        return application
    }

    // TODO (2019-10-15 Amaan): Implement SharedPreferences
//    @Provides
//    @Singleton
//    fun provideScoreSharedPreferences(): ScoreSharedPreferences {
//        return ScoreSharedPreferencesImpl(application)
//    }

}
