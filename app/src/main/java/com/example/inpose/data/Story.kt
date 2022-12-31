package com.example.inpose.data

data class Story(
    val storyId: Int,
    val image: List<String>,
    val title: String,
    val content: String,
    val recommendCount: Int,
    val commentCount: Int,
    val createdAt: String,
    val userId: Int,
    val nickname: String,
    val type: String,
    val profileImage: List<String>
)