package com.example.inpose

import android.content.Context
import android.util.Log
import androidx.paging.map
import com.example.inpose.data.photolog.PhotoLogRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainPresenter(
    private val view: MainContract.View,
    private val photoLogRepository: PhotoLogRepository,
) : MainContract.Presenter {

    private val scope: CoroutineScope = MainScope()

    override fun loadItems() {
        scope.launch {
            val storyList = photoLogRepository.getStory(0)
            Log.d("MainActicity", "${storyList}")
            view.updateAdapter(storyList)
        }
    }
}