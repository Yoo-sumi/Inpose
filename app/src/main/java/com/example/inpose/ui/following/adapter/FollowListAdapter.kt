package com.example.inpose.ui.following.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.inpose.R
import com.example.inpose.data.User
import com.example.inpose.databinding.ItemUserBinding

class FollowListAdapter : RecyclerView.Adapter<FollowListAdapter.StoryViewHolder>() {

    private var userList = mutableListOf<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val binding: ItemUserBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_user,
            parent,
            false
        )
        return StoryViewHolder(binding)
    }

    override fun getItemCount(): Int = userList.size

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    fun addUserList(newUserList: List<User>) {
        userList = (userList + newUserList) as MutableList<User>
    }

    class StoryViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            binding.user = user
        }
    }
}