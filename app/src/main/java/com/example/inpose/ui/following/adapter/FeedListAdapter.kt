package com.example.inpose.ui.following.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.inpose.R
import com.example.inpose.data.model.Feed
import com.example.inpose.databinding.ItemFeedBinding

class FeedListAdapter(
    private var feedList: List<Feed> = emptyList(),
) : RecyclerView.Adapter<FeedListAdapter.FeedViewHolder>() {

    private var index: Int = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val binding: ItemFeedBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_feed,
            parent,
            false
        )
        return FeedViewHolder(binding)
    }

    override fun getItemCount(): Int = feedList.size

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        holder.bind(feedList[position])
    }

    fun addFeedList(newFeedList: List<Feed>) {
        feedList = (feedList + newFeedList) as MutableList<Feed>
    }

    fun getIndex() = index++

    class FeedViewHolder(private val binding: ItemFeedBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(feed: Feed) {
            binding.feed = feed
            when (feed.type) {
                itemView.context.getString(R.string.feed_type_beauty) -> {
                    binding.imageViewFollowingFeedType.background = ResourcesCompat.getDrawable(
                        itemView.context.resources,
                        R.drawable.ic_feed_beauty,
                        null
                    )
                }
                itemView.context.getString(R.string.feed_type_photo) -> {
                    binding.imageViewFollowingFeedType.background = ResourcesCompat.getDrawable(
                        itemView.context.resources,
                        R.drawable.ic_feed_photographer,
                        null
                    )
                }
                itemView.context.getString(R.string.feed_type_model) -> {
                    binding.imageViewFollowingFeedType.background = ResourcesCompat.getDrawable(
                        itemView.context.resources,
                        R.drawable.ic_feed_model,
                        null
                    )
                }
                else -> {
                    binding.imageViewFollowingFeedType.background = null
                }
            }
        }
    }
}