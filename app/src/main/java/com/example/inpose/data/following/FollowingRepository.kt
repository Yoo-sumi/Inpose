package com.example.inpose.data.following

import com.example.inpose.data.Feed
import com.example.inpose.data.User

class FollowingRepository {

    private val followingRemoteSource = FollowingRemoteSource()

    suspend fun getFollowList(page: Int): List<User> {
        return followingRemoteSource.getFollowList(page)
    }

    suspend fun getFeedList(page: Int, userId: Int): List<Feed> {
        return followingRemoteSource.getFeedList(page, userId)
    }
}