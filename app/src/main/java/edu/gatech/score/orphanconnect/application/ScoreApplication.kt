package edu.gatech.score.orphanconnect.application

import android.app.Application
import edu.gatech.score.orphanconnect.dagger.*

class ScoreApplication : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = initializeDagger(this)
    }

    private fun initializeDagger(application: ScoreApplication): AppComponent {
        return DaggerAppComponent
                .builder()
                .appModule(AppModule(application))
                .databaseModule(DatabaseModule(application))
                .repositoryModule(RepositoryModule())
                .build()
    }
}
