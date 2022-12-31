package com.example.inpose.data.following

import com.example.inpose.data.Feed
import com.example.inpose.data.NetworkService
import com.example.inpose.data.User
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FollowingRemoteSource {

    private val BASE_URL = "http://54.180.174.214:7711/api/"
    private val networkService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NetworkService::class.java)

    suspend fun getFollowList(page: Int): List<User> {
        return networkService.getFollowList(page).body() ?: emptyList()
    }

    suspend fun getFeedList(page: Int, userId: Int): List<Feed> {
        return networkService.getFeedList(page, userId).body() ?: emptyList()
    }
}