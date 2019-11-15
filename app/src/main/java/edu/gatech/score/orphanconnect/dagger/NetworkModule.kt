package edu.gatech.score.orphanconnect.dagger

import android.app.Application
import dagger.Module
import dagger.Provides
import edu.gatech.score.orphanconnect.BuildConfig
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class NetworkModule(application: Application) {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
            Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
}
