package com.example.inpose.ui.following

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.inpose.data.Feed
import com.example.inpose.data.User
import com.example.inpose.data.following.FollowingRepository
import com.example.inpose.databinding.FragmentFollowingBinding
import com.example.inpose.ui.following.adapter.FeedListAdapter
import com.example.inpose.ui.following.adapter.FollowingItem
import com.example.inpose.ui.following.adapter.FollowingListAdapter

class FollowingFragment : Fragment(), FollowingContract.View {

    private lateinit var binding: FragmentFollowingBinding
    private lateinit var followListAdapter: FollowingListAdapter
    private lateinit var feedListAdapter: FeedListAdapter
    private val presenter: FollowingPresenter by lazy {
        FollowingPresenter(
            view = this@FollowingFragment,
            followingRepository = FollowingRepository()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFollowingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        feedListAdapter = FeedListAdapter()
        binding.recyclerViewFollowingFeed.adapter = feedListAdapter

        followListAdapter = FollowingListAdapter {
            it?.let {
                presenter.getFeedList(it)
                return@FollowingListAdapter
            }
            presenter.getAllFeedList(followListAdapter.getUserIdList())
        }
        binding.recyclerViewFollowingUser.adapter = followListAdapter
        presenter.getFollowList()
    }

    override fun updateFollowAdapter(userList: List<User>) {
        val followingItem = listOf(FollowingItem(0, false, null)) + userList.map { FollowingItem(1, false, it) }
        followListAdapter.setUserList(followingItem)
        followListAdapter.notifyItemChanged(0, userList.size - 1)
    }

    override fun updateFeedAdapter(feedList: List<Feed>) {
        feedListAdapter = FeedListAdapter(feedList)
        binding.recyclerViewFollowingFeed.adapter = feedListAdapter
    }
}