package com.example.inpose.ui.following

import com.example.inpose.data.model.Feed
import com.example.inpose.data.model.User

interface FollowingContract {

    interface View {

        fun updateFollowAdapter(userList: List<User>)

        fun updateFeedAdapter(feedList: List<Feed>)

        fun addFeedList(feedList: List<Feed>)
    }

    interface Presenter {

        fun getFollowList()

        fun getFeedList(userId: Int)

        fun getAllFeedList(userIdList: List<Int>, index: Int)
    }
}