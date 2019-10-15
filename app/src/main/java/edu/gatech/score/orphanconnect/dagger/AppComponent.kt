package edu.gatech.score.orphanconnect.dagger

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        DatabaseModule::class,
        RepositoryModule::class
    ]
)
interface AppComponent
