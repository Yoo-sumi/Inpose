package com.example.inpose.ui.following

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.inpose.data.model.Feed
import com.example.inpose.data.model.User
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
        initAdapter()
        initScrollListener()
    }

    override fun updateFollowAdapter(userList: List<User>) {
        val followingItem =
            listOf(FollowingItem(0, true, null)) + userList.map { FollowingItem(1, false, it) }
        followListAdapter.setUserList(followingItem)
        followListAdapter.notifyItemChanged(0, userList.size - 1)
        presenter.getAllFeedList(followListAdapter.getUserIdList(), 0)
    }

    override fun updateFeedAdapter(feedList: List<Feed>) {
        feedListAdapter = FeedListAdapter(feedList)
        binding.recyclerViewFollowingFeed.adapter = feedListAdapter
    }

    override fun addFeedList(feedList: List<Feed>) {
        val itemCount = feedListAdapter.itemCount
        feedListAdapter.addFeedList(feedList)
        feedListAdapter.notifyItemChanged(itemCount - 1, itemCount - 1 + feedList.size)
    }

    private fun initAdapter() {
        feedListAdapter = FeedListAdapter()
        binding.recyclerViewFollowingFeed.adapter = feedListAdapter

        followListAdapter = FollowingListAdapter { userId, preIndex ->
            userId?.let {
                presenter.getFeedList(it)
                return@FollowingListAdapter
            }
            if (preIndex != 0) {
                feedListAdapter = FeedListAdapter()
                binding.recyclerViewFollowingFeed.adapter = feedListAdapter
            }
            presenter.getAllFeedList(followListAdapter.getUserIdList(), 0)
        }
        binding.recyclerViewFollowingUser.adapter = followListAdapter
        presenter.getFollowList()
    }

    private fun initScrollListener() {
        binding.recyclerViewFollowingFeed.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if ((recyclerView.layoutManager as LinearLayoutManager?)?.findLastCompletelyVisibleItemPosition() == feedListAdapter.itemCount - 1 && (followListAdapter.getFocus() == 0)) {
                    moreItems(feedListAdapter.getIndex())
                }
            }
        })
    }

    private fun moreItems(index: Int) {
        presenter.getAllFeedList(followListAdapter.getUserIdList(), index)
    }
}