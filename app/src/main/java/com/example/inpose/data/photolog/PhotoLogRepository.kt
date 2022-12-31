package com.example.inpose.data.photolog

import com.example.inpose.data.Story

class PhotoLogRepository {

    private val photoLogRemoteSource = PhotoLogRemoteSource()

    suspend fun getStoryList(page: Int): List<Story> {
        return photoLogRemoteSource.getStoryList(page)
    }
}