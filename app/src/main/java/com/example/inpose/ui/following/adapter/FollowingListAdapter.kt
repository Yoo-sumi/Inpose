package com.example.inpose.ui.following.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.inpose.databinding.ItemUserAllBinding
import com.example.inpose.databinding.ItemUserBinding

class FollowingListAdapter(
    private var followingList: List<FollowingItem> = emptyList(),
    private val onClickListener: (userId: Int?, preIndex: Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var focus = 0

    override fun getItemViewType(position: Int): Int {
        return followingList[position].type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = when (viewType) {
            0 -> {
                ItemUserAllBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            }
            else -> {
                ItemUserBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            }
        }

        val holder = when (binding) {
            is ItemUserAllBinding -> UserAllViewHolder(binding)
            else -> UserViewHolder(binding as ItemUserBinding)
        }

        binding.root.setOnClickListener {
            followingList[focus].focus = false
            followingList[holder.bindingAdapterPosition].focus = true
            notifyItemChanged(focus)
            followingList[holder.bindingAdapterPosition].data?.userId.let { userId ->
                onClickListener(
                    userId,
                    focus
                )
            }
            focus = holder.bindingAdapterPosition
            notifyItemChanged(focus)
        }
        return holder
    }

    override fun getItemCount(): Int = followingList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (followingList[position].type) {
            0 -> {
                (holder as UserAllViewHolder).bind(followingList[position].focus)
            }
            1 -> {
                (holder as UserViewHolder).bind(followingList[position])
            }
        }
    }

    fun setUserList(userList: List<FollowingItem>) {
        this.followingList = userList
    }

    fun getUserIdList() = followingList.mapNotNull { it.data?.userId }

    fun getFocus() = focus

    class UserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(followingItem: FollowingItem) {
            binding.followingItem = followingItem
        }
    }

    class UserAllViewHolder(private val binding: ItemUserAllBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(focus: Boolean) {
            binding.focus = focus
        }
    }
}