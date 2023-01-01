package com.example.inpose.data.photolog

import com.example.inpose.data.NetworkModule
import com.example.inpose.data.model.Story

class PhotoLogRemoteSource {

    suspend fun getStoryList(page: Int): List<Story> {
        return NetworkModule.networkService.getStoryList(page).body() ?: emptyList()
    }
}