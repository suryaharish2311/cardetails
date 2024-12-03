package com.fintech.myapplication

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class CarListDatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(application: Application) = CarPostsDatabase.getInstance(application)

    @Singleton
    @Provides
    fun providePostsDao(database: CarPostsDatabase) = database.getPostsDao()
}
