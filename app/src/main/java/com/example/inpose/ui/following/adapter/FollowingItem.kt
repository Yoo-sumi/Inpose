package com.example.inpose.ui.following.adapter

import com.example.inpose.data.model.User

data class FollowingItem(
    var type: Int,
    var focus: Boolean,
    val data: User?
)
