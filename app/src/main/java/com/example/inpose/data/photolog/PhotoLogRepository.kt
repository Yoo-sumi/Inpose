package com.example.inpose.data.photolog

import com.example.inpose.data.NetworkService
import com.example.inpose.data.Story
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class PhotoLogRepository {

    private val BASE_URL = "http://54.180.174.214:7711/api/"
    private val networkService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NetworkService::class.java)

    suspend fun getStory(page: Int): List<Story> {
        return networkService.getStoryList(page).body() ?: emptyList()
    }
}