
package com.fintech.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class PostDetailsViewModel
    @AssistedInject
    constructor(
        postRepository: PostRepository,
        @Assisted postId: Int,
    ) : ViewModel() {
        val post = postRepository.getPostById(postId).asLiveData()

        @AssistedFactory
        interface PostDetailsViewModelFactory {
            fun create(postId: Int): PostDetailsViewModel
        }

        @Suppress("UNCHECKED_CAST")
        companion object {
            fun provideFactory(
                assistedFactory: PostDetailsViewModelFactory,
                postId: Int,
            ): ViewModelProvider.Factory =
                object : ViewModelProvider.Factory {
                    override fun <T : ViewModel> create(modelClass: Class<T>): T = assistedFactory.create(postId) as T
                }
        }
    }
