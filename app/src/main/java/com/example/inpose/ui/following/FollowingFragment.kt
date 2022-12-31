package com.example.inpose.ui.following

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.inpose.ui.photolog.adapter.PhotoLogListAdapter
import com.example.inpose.data.Story
import com.example.inpose.data.User
import com.example.inpose.data.following.FollowingRepository
import com.example.inpose.databinding.FragmentFollowingBinding
import com.example.inpose.ui.following.adapter.FollowListAdapter

class FollowingFragment : Fragment(), FollowingContract.View {

    private lateinit var binding: FragmentFollowingBinding
    private lateinit var followListAdapter: FollowListAdapter
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
        followListAdapter = FollowListAdapter()
        binding.recyclerViewFollowingUser.adapter = followListAdapter
        presenter.loadItems(followListAdapter.itemCount)
    }

    override fun updateFollowAdapter(userList: List<User>) {
        val itemCount = followListAdapter.itemCount
        followListAdapter.addUserList(userList)
        followListAdapter.notifyItemChanged(itemCount - 1, itemCount - 1 + 20)
        initScrollListener()
    }

    override fun updateFeedAdapter(storyList: List<Story>) {
        TODO("Not yet implemented")
    }

    private fun initScrollListener(){
        binding.recyclerViewFollowingUser.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if ((recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition() == followListAdapter.itemCount - 5){
                    moreUserItems()
                }
            }
        })
    }

    private fun moreUserItems() {
        presenter.loadItems(followListAdapter.itemCount / 20)
    }
}