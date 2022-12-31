package com.example.inpose.ui.photolog

import com.example.inpose.data.Story

interface PhotoLogContract {

    interface View {

        fun updateAdapter(storyList: List<Story>)
    }

    interface Presenter {

        fun loadItems(page: Int)
    }
}