package com.fintech.myapplication

import retrofit2.Response
import retrofit2.http.GET

interface CarAPIService {
    @GET("/DummyCarApi/api/posts/")
    suspend fun getPosts(): Response<List<Post>>

    companion object {
        const val CARLIST_API_URL = "https://suryacarcarelist.free.mockoapp.net"
    }
}
