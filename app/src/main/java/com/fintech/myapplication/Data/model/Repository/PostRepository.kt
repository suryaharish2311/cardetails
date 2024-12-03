package com.fintech.myapplication

import androidx.annotation.MainThread
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import retrofit2.Response
import javax.inject.Inject

interface PostRepository {
    fun getAllPosts(): Flow<Resource<List<Post>>>

    fun getPostById(postId: Int): Flow<Post>
}

/**
 * Singleton repository for fetching data from remote and storing it in database
 * for offline capability. This is Single source of data.
 */
@ExperimentalCoroutinesApi
class DefaultPostRepository
    @Inject
    constructor(
        private val postsDao: PostsDao,
        private val carAPIService: CarAPIService,
    ) : PostRepository {
        /**
         * Fetched the posts from network and stored it in database. At the end, data from persistence
         * storage is fetched and emitted.
         */
        override fun getAllPosts(): Flow<Resource<List<Post>>> =
            object : NetworkBoundRepository<List<Post>, List<Post>>() {
                override suspend fun saveRemoteData(response: List<Post>) = postsDao.addPosts(response)

                override fun fetchFromLocal(): Flow<List<Post>> = postsDao.getAllPosts()

                override suspend fun fetchFromRemote(): Response<List<Post>> = carAPIService.getPosts()
            }.asFlow()

        /**
         * Retrieves a post with specified [postId].
         * @param postId Unique id of a [Post].
         * @return [Post] data fetched from the database.
         */
        @MainThread
        override fun getPostById(postId: Int): Flow<Post> = postsDao.getPostById(postId).distinctUntilChanged()
    }
