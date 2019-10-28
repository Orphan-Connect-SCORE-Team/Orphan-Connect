package edu.gatech.score.orphanconnect.dagger

import dagger.Component
import edu.gatech.score.orphanconnect.application.ScoreApplication
import edu.gatech.score.orphanconnect.database.OrphanViewModel
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        DatabaseModule::class,
        RepositoryModule::class
    ]
)
interface AppComponent {

    // Application
    fun inject(application: ScoreApplication)

    // ViewModels
    fun inject(orphanViewModel: OrphanViewModel)
}
