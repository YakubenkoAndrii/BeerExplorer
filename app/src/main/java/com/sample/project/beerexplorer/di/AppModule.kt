package com.sample.project.beerexplorer.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.sample.project.core.data.network.model.Dispatcher
import com.sample.project.core.data.preferences.PreferencesImpl
import com.sample.project.core.domain.preferences.Preferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import com.sample.project.core.data.network.model.Dispatchers.IO
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideSharedPreferences(app: Application): SharedPreferences {
        return app.getSharedPreferences("shared_prefs", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun providePreferences(sharedPreferences: SharedPreferences): Preferences {
        return PreferencesImpl(sharedPreferences)
    }

    @Module
    @InstallIn(SingletonComponent::class)
    object DispatchersModule {
        @Provides
        @Dispatcher(IO)
        fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO
    }
}
