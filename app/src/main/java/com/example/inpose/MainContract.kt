package com.example.inpose

import android.content.Context
import androidx.paging.PagingData
import com.example.inpose.data.Story

interface MainContract {

    interface View {

        fun updateAdapter(storyList: List<Story>)
    }

    interface Presenter {

        fun loadItems(page: Int)
    }
}