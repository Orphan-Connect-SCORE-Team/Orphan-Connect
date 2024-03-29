package edu.gatech.score.orphanconnect.dagger

import dagger.Component
import edu.gatech.score.orphanconnect.LoginViewModel
import edu.gatech.score.orphanconnect.application.ScoreApplication
import edu.gatech.score.orphanconnect.OrphanViewModel
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        DatabaseModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        WebserviceModule::class
    ]
)
interface AppComponent {

    // Application
    fun inject(application: ScoreApplication)

    // ViewModels
    fun inject(orphanViewModel: OrphanViewModel)
    fun inject(orphanViewModel: LoginViewModel)
}
