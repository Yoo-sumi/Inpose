package com.example.inpose.ui.following

import com.example.inpose.data.Feed
import com.example.inpose.data.User

interface FollowingContract {

    interface View {

        fun updateFollowAdapter(userList: List<User>)

        fun updateFeedAdapter(feedList: List<Feed>)
    }

    interface Presenter {

        fun getFollowList()

        fun getFeedList(userId: Int)

        fun getAllFeedList(userIdList: List<Int>)
    }
}