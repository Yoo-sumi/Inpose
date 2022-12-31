package com.example.inpose

import android.util.Log
import com.example.inpose.data.photolog.PhotoLogRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainPresenter(
    private val view: MainContract.View,
    private val photoLogRepository: PhotoLogRepository,
) : MainContract.Presenter {

    private val scope: CoroutineScope = MainScope()

    override fun loadItems(page: Int) {
        scope.launch {
            val storyList = photoLogRepository.getStory(page)
            view.updateAdapter(storyList)
        }
    }
}