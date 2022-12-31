package com.example.inpose.ui.following

import com.example.inpose.data.following.FollowingRepository
import com.example.inpose.data.photolog.PhotoLogRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class FollowingPresenter(
    private val view: FollowingContract.View,
    private val followingRepository: FollowingRepository,
) : FollowingContract.Presenter {

    private val scope: CoroutineScope = MainScope()

    override fun loadItems(page: Int) {
        scope.launch {
            val followList = followingRepository.getFollowList(page)
            view.updateFollowAdapter(followList)
        }
    }
}