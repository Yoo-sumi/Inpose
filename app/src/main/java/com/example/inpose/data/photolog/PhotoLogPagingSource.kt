package com.example.inpose.data.photolog

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.inpose.data.NetworkService
import com.example.inpose.data.Story
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class PhotoLogPagingSource : PagingSource<Int, Story>(){

    private val STARTING_PAGE_INDEX = 0
    private val BASE_URL = "http://54.180.174.214:7711/api/"
    private val networkService = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(NetworkService::class.java)

    init {
        Log.d("MainActivity", "start2")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Story> {
        return try {
            val position = params.key ?: STARTING_PAGE_INDEX
            val response = networkService.getStoryList(position)
            val post = response.body() ?: emptyList()

            LoadResult.Page(
                data = post,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = null
            )

        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}