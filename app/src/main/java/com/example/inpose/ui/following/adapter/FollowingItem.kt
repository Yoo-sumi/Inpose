package com.example.inpose.ui.following.adapter

import com.example.inpose.data.User

data class FollowingItem(
    var type: Int,
    var focus: Boolean,
    val data: User?
)
