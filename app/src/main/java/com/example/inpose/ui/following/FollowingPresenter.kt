package com.example.inpose.ui.following

import com.example.inpose.data.model.User
import com.example.inpose.data.following.FollowingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class FollowingPresenter(
    private val view: FollowingContract.View,
    private val followingRepository: FollowingRepository,
) : FollowingContract.Presenter {

    private val scope: CoroutineScope = MainScope()

    override fun getFollowList() {
        scope.launch {
            var page = 0
            val followList = mutableListOf<User>()
            while (true) {
                val nextFollowList = followingRepository.getFollowList(page++)
                if (nextFollowList.isEmpty()) break
                followList.addAll(nextFollowList)
            }
            view.updateFollowAdapter(followList)
        }
    }

    override fun getFeedList(userId: Int) {
        scope.launch {
            val followList = followingRepository.getFeedList(0, userId)
            view.updateFeedAdapter(followList)
        }
    }

    override fun getAllFeedList(userIdList: List<Int>, index: Int) {
        scope.launch {
            val followList = followingRepository.getFeedList(0, userIdList[index])
            view.addFeedList(followList)
        }
    }
}