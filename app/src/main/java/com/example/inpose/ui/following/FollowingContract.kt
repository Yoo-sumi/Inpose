package com.example.inpose.ui.following

import com.example.inpose.data.Story
import com.example.inpose.data.User

interface FollowingContract {

    interface View {

        fun updateFollowAdapter(userList: List<User>)

        fun updateFeedAdapter(storyList: List<Story>)
    }

    interface Presenter {

        fun loadItems(page: Int)
    }
}