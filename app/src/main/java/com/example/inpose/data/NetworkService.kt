package com.example.inpose.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

    @GET("test/story")
    suspend fun getStoryList(
        @Query("page") page: Int,
    ): Response<List<Story>>

    @GET("test/follow/user")
    suspend fun getFollowList(
        @Query("page") page: Int,
    ): Response<List<User>>

    @GET("test/feed")
    suspend fun getFeedList(
        @Query("page") page: Int,
        @Query("targetId") targetId: Int
    ): Response<List<Feed>>
}