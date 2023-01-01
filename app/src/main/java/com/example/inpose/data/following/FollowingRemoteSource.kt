package com.example.inpose.data.following

import com.example.inpose.data.NetworkModule
import com.example.inpose.data.model.Feed
import com.example.inpose.data.model.User

class FollowingRemoteSource {

    suspend fun getFollowList(page: Int): List<User> {
        return NetworkModule.networkService.getFollowList(page).body() ?: emptyList()
    }

    suspend fun getFeedList(page: Int, userId: Int): List<Feed> {
        return NetworkModule.networkService.getFeedList(page, userId).body() ?: emptyList()
    }
}