package com.fintech.myapplication

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Currently PostRepository is only used in ViewModels.
 * PostDetailsViewModel is not injected using @HiltViewModel so can't install in ViewModelComponent.
 */
@ExperimentalCoroutinesApi
@InstallIn(ActivityRetainedComponent::class)
@Module
abstract class PostRepositoryModule {
    @ActivityRetainedScoped
    @Binds
    abstract fun bindPostRepository(repository: DefaultPostRepository): PostRepository
}
