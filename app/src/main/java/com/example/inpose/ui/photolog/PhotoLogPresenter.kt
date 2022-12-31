package com.example.inpose.ui.photolog

import com.example.inpose.data.photolog.PhotoLogRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class PhotoLogPresenter(
    private val view: PhotoLogContract.View,
    private val photoLogRepository: PhotoLogRepository,
) : PhotoLogContract.Presenter {

    private val scope: CoroutineScope = MainScope()

    override fun loadItems(page: Int) {
        scope.launch {
            val storyList = photoLogRepository.getStoryList(page)
            view.updateAdapter(storyList)
        }
    }
}